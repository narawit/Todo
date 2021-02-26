package com.narawit.todo.base.scene

import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.narawit.todo.R

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    val navController: NavController by lazy { findNavController() }

    protected val binding get() = _binding!!
    protected var _binding: T? = null


    abstract fun initView()
    abstract fun observer()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun navigateWithDeepLink(deepLink: String) {
        navController.navigate(deepLink.toUri())
    }

    fun navigateWithAction(res: Int) {
        navController.navigate(res)
    }
}