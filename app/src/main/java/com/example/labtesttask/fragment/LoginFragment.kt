package com.example.labtesttask.fragment

import android.app.DatePickerDialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.labtesttask.R
import com.example.labtesttask.database.ProfileDataBase
import com.example.labtesttask.databinding.LoginFragmentBinding
import com.example.labtesttask.repository.ProfileRepository
import com.example.labtesttask.viewmodel.LoginViewModel
import com.example.labtesttask.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.login_fragment.*
import java.util.*

class LoginFragment : Fragment(){
    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        binding.lifecycleOwner = this
        binding.pickDate.setOnClickListener { showDatePicker() }
        viewModel.birthday.observe(this, Observer { showBirthday() })
        val factory = ViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)
        autoHideErrors()
        return binding.root
    }


    private fun autoHideErrors(){
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

    private fun autoShowError()
    {
        viewModel.firstNameError.observe(this, Observer {
            if(it){
                binding.firstNameInputLayout.error = ""
                showSoftKeyboard(firstNameInputEditText)
            }
        })

        viewModel.secondNameError.observe(this, Observer {
            if(it){
                binding.secondNameInputLayout.error = ""
                showSoftKeyboard(secondNameInputEditText)
            }
        })

        viewModel.passwordError.observe(this, Observer {
            if(it){
                binding.passwordInputLayout.error = ""
                showSoftKeyboard(passwordInputEditText)
            }
        })

        viewModel.passwordProofError.observe(this, Observer {
            if(it){
                binding.passwordProofInputLayout.error = ""
                showSoftKeyboard(passwordProofInputEditText)
            }
        })


    }

    private fun showSoftKeyboard(view: View) {
        if (view.requestFocus()) {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun showDatePicker(){
        val calendar =
            if (viewModel.birthday.value == null) Calendar.getInstance() else viewModel.birthday.value!!
        val datePickerDialog = DatePickerDialog(
            this.context!!,
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
