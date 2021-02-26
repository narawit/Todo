package com.narawit.todo.scene.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.narawit.todo.base.scene.BaseFragment
import com.narawit.todo.databinding.FragmentHomeBinding
import com.narawit.todo.scene.MainViewModel
import com.narawit.todo.scene.dialog.TaskDialog
import com.narawit.todo.utils.TASK
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private val viewModel by viewModel<HomeViewModel>()
    private val shared by sharedViewModel<MainViewModel>()
    private val adapter by lazy { TaskAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    var dialog: TaskDialog? = null
    override fun initView() {
        shared.setBottomNavigationVisible(true)
        adapter.setRecyclerViewItemClick { model, _ ->
            dialog = TaskDialog(model)
            dialog?.setEvent { data, task ->
                if (task == TASK.UPDATE)
                    viewModel.update(data)
                else if (task == TASK.DELETE)
                    viewModel.delete(data)
            }
            dialog?.show(childFragmentManager, "Dialog")
        }
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        binding.btnAdd.setOnClickListener {
            dialog = TaskDialog(null)
            dialog?.setEvent { data, task ->
                if (task == TASK.ADD)
                    viewModel.create(data)
            }
            dialog?.show(childFragmentManager, "Dialog")
        }

        binding.swipe.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun observer() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            error?.let {
                shared.setError(it)
            }
        })
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                shared.setLoading(it)
            }
        })
        viewModel.data.observe(this, {
            dialog?.dismiss()
            binding.swipe.isRefreshing = false
            adapter.clear(true)
            adapter.addAllList(it)
        })

    }
}