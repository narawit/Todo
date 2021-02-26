package com.narawit.todo.scene.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.narawit.todo.R
import com.narawit.todo.base.scene.BaseFragment
import com.narawit.todo.databinding.FragmentLoginBinding
import com.narawit.todo.datasource.model.UserEntity
import com.narawit.todo.scene.MainViewModel
import com.narawit.todo.utils.Constants.TOKEN
import com.narawit.todo.utils.isEmailValid
import com.narawit.todo.utils.passwordValid
import com.narawit.todo.utils.visible
import com.orhanobut.hawk.Hawk
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel by viewModel<LoginViewModel>()
    private val shared by sharedViewModel<MainViewModel>()
    private var isLogin = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val registerTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.btnRegister.isEnabled = validate()

        }

    }
    private val loginTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(p0: Editable?) {
            binding.btnLogin.isEnabled =
                !(binding.etEmail.text.isNullOrEmpty() || binding.etPassword.text.isNullOrEmpty())
        }

    }

    override fun initView() {
        if (Hawk.contains(TOKEN))
            navigateWithDeepLink(getString(R.string.deep_link_home))

        binding.etEmail.addTextChangedListener(loginTextWatcher)
        binding.etPassword.addTextChangedListener(loginTextWatcher)
        shared.setBottomNavigationVisible(false)

        binding.btnLogin.setOnClickListener {
            if (isLogin) {
                viewModel.login(
                    UserEntity(
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )
            }
        }
        binding.btnRegister.setOnClickListener {
            if (isLogin) {
                binding.tvTitle.setText(R.string.register)
                binding.etEmail.removeTextChangedListener(loginTextWatcher)
                binding.etPassword.removeTextChangedListener(loginTextWatcher)
                binding.etEmail.addTextChangedListener(registerTextWatcher)
                binding.etPassword.addTextChangedListener(registerTextWatcher)
                binding.etName.addTextChangedListener(registerTextWatcher)
                binding.etAge.addTextChangedListener(registerTextWatcher)
                binding.etConfirmPassword.addTextChangedListener(registerTextWatcher)
                binding.btnLogin.visible(false)
                binding.btnBack.visible(true)
                binding.layoutName.visible(true)
                binding.layoutAge.visible(true)
                binding.layoutConfirmPassword.visible(true)
                binding.btnRegister.isEnabled = false
                binding.layoutEmail.isErrorEnabled = true
                binding.layoutPassword.isErrorEnabled = true
                binding.layoutPassword.isHelperTextEnabled = true
                binding.layoutPassword.helperText = getString(R.string.password_helper)
                isLogin = false
                binding.etName.requestFocus()
            } else {
                viewModel.register(
                    UserEntity(
                        age = binding.etAge.text.toString().toIntOrNull(),
                        name = binding.etName.text.toString(),
                        email = binding.etEmail.text.toString(),
                        password = binding.etPassword.text.toString()
                    )
                )
            }
        }
        binding.btnBack.setOnClickListener {
            binding.tvTitle.setText(R.string.app_name)
            binding.btnLogin.visible(true)
            binding.btnBack.visible(false)
            binding.layoutName.visible(false)
            binding.layoutAge.visible(false)
            binding.layoutConfirmPassword.visible(false)

            clearForm()

            binding.btnLogin.isEnabled = false
            binding.btnRegister.isEnabled = true

            binding.layoutEmail.isErrorEnabled = false
            binding.layoutPassword.isErrorEnabled = false
            binding.layoutPassword.isHelperTextEnabled = false
            binding.layoutPassword.helperText = null

            isLogin = true
            binding.etEmail.requestFocus()
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
        viewModel.state.observe(this, {
            if (it) {
                navigateWithAction(R.id.action_loginFragment_to_homeFragment)
            } else {
                //alert
            }
        })
    }

    private fun validate(mode: Boolean = false): Boolean {
        var validate = true
        if (!binding.etEmail.text.toString().isEmailValid()) {
            binding.layoutEmail.error = getString(R.string.email_error)
            validate = validate && false
        } else binding.layoutEmail.error = null
        if (!binding.etPassword.text.toString().passwordValid()) {
            binding.layoutPassword.error = getString(R.string.password_helper)
            validate = validate && false
        } else binding.layoutPassword.error = null
        if (binding.etName.text.isNullOrEmpty()) {
            binding.layoutName.error = getString(R.string.name_error)
            validate = validate && false
        } else binding.layoutName.error = null
        if (binding.etAge.text.isNullOrEmpty()) {
            binding.layoutAge.error = getString(R.string.age_error)
            validate = validate && false
        } else binding.layoutAge.error = null
        if (binding.etConfirmPassword.text.toString() != binding.etPassword.text.toString()) {
            binding.layoutConfirmPassword.error = getString(R.string.confirm_error)
            validate = validate && false
        } else binding.layoutConfirmPassword.error = null
        return validate
    }

    private fun clearForm() {
        binding.etEmail.removeTextChangedListener(registerTextWatcher)
        binding.etAge.removeTextChangedListener(registerTextWatcher)
        binding.etEmail.removeTextChangedListener(registerTextWatcher)
        binding.etPassword.removeTextChangedListener(registerTextWatcher)
        binding.etConfirmPassword.removeTextChangedListener(registerTextWatcher)

        binding.etEmail.addTextChangedListener(loginTextWatcher)
        binding.etPassword.addTextChangedListener(loginTextWatcher)

        binding.etName.text = null
        binding.etAge.text = null
        binding.etEmail.text = null
        binding.etPassword.text = null
        binding.etConfirmPassword.text = null

        binding.layoutName.error = null
        binding.layoutAge.error = null
        binding.layoutEmail.error = null
        binding.layoutPassword.error = null
        binding.etConfirmPassword.error = null
    }
}