package com.narawit.todo.scene.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narawit.todo.R
import com.narawit.todo.base.scene.BaseFragment
import com.narawit.todo.databinding.FragmentProfileBinding
import com.narawit.todo.scene.MainViewModel
import com.orhanobut.hawk.Hawk
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    private val viewModel by viewModel<ProfileViewModel>()
    private val shared by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initView() {
        shared.setBottomNavigationVisible(true)

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
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
        viewModel.data.observe(viewLifecycleOwner, {
            it?.let {
                binding.tvName.text = "Name : ${it.name}"
                binding.tvAge.text = "Age : ${it.age}"
                binding.tvMail.text = "Mail : ${it.email}"
            }
        })
        viewModel.state.observe(viewLifecycleOwner, {
            it?.let {
                if (it) {
                    Hawk.deleteAll()
                    navigateWithAction(R.id.action_profileFragment_to_loginFragment)
                }
            }
        })
    }
}