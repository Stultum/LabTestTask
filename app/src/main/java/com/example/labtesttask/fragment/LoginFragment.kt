package com.example.labtesttask.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.labtesttask.R
import com.example.labtesttask.databinding.LoginFragmentBinding
import com.example.labtesttask.repository.ProfileRepository
import com.example.labtesttask.viewmodel.LoginViewModel
import com.example.labtesttask.viewmodelfactory.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.*

class LoginFragment : Fragment() {
    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        binding.lifecycleOwner = this

        initViewModel()
        autoShowError()
        autoHideErrors()

        binding.pickDate.setOnClickListener { showDatePicker() }
        viewModel.birthday.observe(this, Observer { showBirthday() })
        viewModel.isRegFinished.observe(this, Observer {
            finishReg(binding.root, it)
        })
        return binding.root
    }

    private fun finishReg(view: View, isProfileLogged: Boolean) {
        if (!isProfileLogged) {
            view.findNavController()
                .navigate(R.id.action_loginFragment_to_mainWindowFragment_without_anim)
        } else {
            view.findNavController().navigate(R.id.action_loginFragment_to_mainWindowFragment)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory {
                LoginViewModel(
                    ProfileRepository(
                        this.context?.getSharedPreferences(
                            ProfileRepository.PROFILE_PREF,
                            Context.MODE_PRIVATE
                        )!!
                    )
                )
            }).get(
            LoginViewModel::class.java
        )
        binding.loginViewModel = viewModel
    }


    private fun autoHideErrors() {
        binding.firstNameInputLayout.editText?.addTextChangedListener {
            binding.firstNameInputLayout.error = null
        }

        binding.secondNameInputLayout.editText?.addTextChangedListener {
            binding.secondNameInputLayout.error = null
        }

        binding.passwordInputLayout.editText?.addTextChangedListener {
            binding.passwordInputLayout.error = null
        }

        binding.passwordProofInputLayout.editText?.addTextChangedListener {
            binding.passwordProofInputLayout.error = null
        }
    }

    private fun autoShowError() {
        viewModel.firstNameError.observe(this, Observer {
            if (it) {
                binding.firstNameInputLayout.error = getString(R.string.nameError)
                showSoftKeyboard(firstNameInputEditText)
            }
        })

        viewModel.secondNameError.observe(this, Observer {
            if (it) {
                binding.secondNameInputLayout.error = getString(R.string.secondNameError)
                showSoftKeyboard(secondNameInputEditText)
            }
        })

        viewModel.passwordError.observe(this, Observer {
            if (it) {
                binding.passwordInputLayout.error = getString(R.string.passwordError)
                showSoftKeyboard(passwordInputEditText)
            }
        })

        viewModel.passwordProofError.observe(this, Observer {
            if (it) {
                binding.passwordProofInputLayout.error = getString(R.string.passwordProofError)
                showSoftKeyboard(passwordProofInputEditText)
            }
        })

        viewModel.birthDateError.observe(this, Observer {
            if (it) {
                Snackbar.make(this.view!!, R.string.dateError, Snackbar.LENGTH_SHORT)
                    .show()
                showDatePicker()
            }
        })

        viewModel.ageError.observe(this, Observer {
            if (it) {
                Snackbar.make(this.view!!, R.string.ageError, Snackbar.LENGTH_SHORT)
                    .show()
                showDatePicker()
            }
        })

    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun showDatePicker() {
        val calendar =
            if (viewModel.birthday.value == null) Calendar.getInstance() else viewModel.birthday.value!!
        val datePickerDialog = DatePickerDialog(
            this.context!!, R.style.MyDatePickerDialogTheme,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                viewModel.birthday.value = calendar
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showBirthday() {
        binding.birthDay.text = viewModel.getBirthdayString()
        TransitionManager.beginDelayedTransition(binding.root as ViewGroup)
        binding.birthDay.visibility = View.VISIBLE
        binding.age.text = viewModel.age.toString()
        binding.age.visibility = View.VISIBLE
    }

}
