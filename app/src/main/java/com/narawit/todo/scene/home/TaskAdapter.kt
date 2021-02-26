package com.narawit.todo.scene.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narawit.todo.R
import com.narawit.todo.base.scene.BaseAdapter
import com.narawit.todo.databinding.ItemTaskBinding
import com.narawit.todo.datasource.model.TaskEntity
import com.narawit.todo.utils.Constants.DEFAULT_FORMAT_T
import com.narawit.todo.utils.toDate
import com.narawit.todo.utils.toString

class TaskAdapter : BaseAdapter<TaskEntity, ItemTaskBinding>() {
    override fun onBind(binding: ItemTaskBinding, model: TaskEntity, itemView: View) {
        if (model.completed) binding.imgStatus.setImageResource(R.drawable.ic_check)
        else binding.imgStatus.setImageResource(R.drawable.ic_progress)
        val date = model.createdAt?.replace("Z", "+0000")?.toDate(DEFAULT_FORMAT_T)

        binding.tvDesc.text = model.description
        binding.tvDate.text = date?.toString("dd MMM")
        binding.tvTime.text = date?.toString("HH:mm")
    }

    override fun addFilter(
        charString: String,
        data: MutableList<TaskEntity>?,
        resultList: MutableList<TaskEntity>
    ): MutableList<TaskEntity> {
        TODO("Not yet implemented")
    }

    override fun createView(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            (ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ))
        )
    }
}