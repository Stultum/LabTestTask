package com.example.labtesttask.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.labtesttask.R
import com.example.labtesttask.databinding.LoginFragmentBinding
import com.example.labtesttask.viewmodel.LoginViewModel

class LoginFragment : Fragment(){
    private lateinit var binding: LoginFragmentBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

}
