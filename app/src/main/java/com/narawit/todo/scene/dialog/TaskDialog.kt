package com.narawit.todo.scene.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.narawit.todo.R
import com.narawit.todo.databinding.BottomSheetDialogTaskBinding
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.utils.Constants.DEFAULT_FORMAT_T
import com.narawit.todo.utils.TASK
import com.narawit.todo.utils.toDate
import com.narawit.todo.utils.toString
import com.narawit.todo.utils.visible

class TaskDialog(
    private val data: TaskEntity?,
    private var event: ((TaskEntity, TASK) -> Unit)? = null
) :
    BottomSheetDialogFragment() {
    private val binding get() = _binding!!
    private var _binding: BottomSheetDialogTaskBinding? = null

    private val validateTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.btnAdd.isEnabled = !binding.etDescription.text.isNullOrEmpty()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetDialogTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.etDescription.addTextChangedListener(validateTextWatcher)
        binding.etDescription.requestFocus()
        data?.let {
            binding.tvTitle.setText(if (it.completed) R.string.done_task else R.string.edit_task)
            binding.tvTitle.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                if (it.completed) R.drawable.ic_check else R.drawable.ic_progress,
                0
            )
            binding.etDescription.setText(data.description)
            binding.etDescription.visible(!data.completed)
            binding.tvDescription.text = "${getString(R.string.description)} : ${data.description}"
            binding.tvCreate.text = "${getString(R.string.create_at)} : ${
                data.updatedAt?.replace("Z", "+0000")?.toDate(DEFAULT_FORMAT_T)
                    ?.toString("d MMM YYYY HH:mm")
            }"
            binding.tvUpdate.text = "${getString(R.string.update_at)} : ${
                data.updatedAt?.replace("Z", "+0000")?.toDate(DEFAULT_FORMAT_T)
                    ?.toString("d MMM YYYY HH:mm")
            }"
            binding.tvDescription.visible(data.completed)
            binding.tvCreate.visible(data.completed)
            binding.tvUpdate.visible(data.completed)
            binding.swDone.isChecked = data.completed
            binding.swDone.visible(!data.completed)
            binding.btnUpdate.visible(!data.completed)
            binding.btnDelete.visible(true)
            binding.btnAdd.visible(false)
            binding.btnUpdate.setOnClickListener {
                data.completed = binding.swDone.isChecked
                data.description = binding.etDescription.text.toString()
                event?.let { it(data, TASK.UPDATE) }
            }
            binding.btnDelete.setOnClickListener {
                event?.let { it(data, TASK.DELETE) }
            }
        } ?: run {
            binding.tvTitle.setText(R.string.new_task)
            binding.btnAdd.setOnClickListener {
                event?.let {
                    it(
                        TaskEntity(description = binding.etDescription.text.toString()),
                        TASK.ADD
                    )
                }
            }
        }
    }

    fun setEvent(event: ((TaskEntity, TASK) -> Unit)) {
        this.event = event
    }
}
