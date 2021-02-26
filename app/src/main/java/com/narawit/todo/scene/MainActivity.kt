package com.narawit.todo.scene

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.narawit.todo.R
import com.narawit.todo.base.scene.BaseActivity
import com.narawit.todo.databinding.ActivityMainBinding
import com.narawit.todo.scene.dialog.LoadingDialog
import com.narawit.todo.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewController: NavController by lazy {
        Navigation.findNavController(this, R.id.nav_main)
    }

    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initBottomNavigation()
        observer()
    }

    private fun initView() {
        setContentView(binding.root)
    }

    private fun initBottomNavigation() {
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            viewController
        )
    }

    private val dialog by lazy {
        LoadingDialog(this)
    }

    private fun observer() {
        viewModel.error.observe(this, {
            Log.i(TAG, "${it.message}")
            AlertDialog.Builder(this)
                .setMessage(it.message)
                .show()
        })
        viewModel.isLoading.observe(this, {
            Log.i(TAG, "Loading : $it")
            if (it)
                dialog.show()
            else
                dialog.dismiss()
        })
        viewModel.bottomNavigationVisible.observe(this, {
            binding.bottomNavigation.visible(it)
        })

    }
}