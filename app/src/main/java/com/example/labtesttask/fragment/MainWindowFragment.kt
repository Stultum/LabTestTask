package com.example.labtesttask.fragment

import android.app.AlertDialog
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.labtesttask.R
import com.example.labtesttask.activity.MainActivity
import com.example.labtesttask.databinding.MainWindowFragmentBinding
import com.example.labtesttask.database.LoginedProfile
import com.example.labtesttask.repository.ProfileRepository
import com.example.labtesttask.viewmodel.LoginViewModel
import com.example.labtesttask.viewmodel.MainWindowViewModel
import com.example.labtesttask.viewmodelfactory.ViewModelFactory

class MainWindowFragment : Fragment() {
    private lateinit var binding: MainWindowFragmentBinding
    private lateinit var viewModel: MainWindowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_window_fragment, container, false)

        initViewModel()

        viewModel.isAccountLeave.observe(this, Observer {
            leaveAccount(binding.root)

        })

        binding.buttonHello.setOnClickListener { showHelloDialog() }

        return binding.root
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory {
                MainWindowViewModel(
                    ProfileRepository(
                        this.context?.getSharedPreferences(
                            ProfileRepository.PROFILE_PREF,
                            Context.MODE_PRIVATE
                        )!!
                    )
                )
            }).get(
            MainWindowViewModel::class.java
        )
        binding.mainWindowViewModel = viewModel
    }

    private fun showHelloDialog() {
        val helloDialogFragment = HelloDialogFragment.newInstance(LoginedProfile.profile)
        helloDialogFragment.show(childFragmentManager, "")
    }

    private fun leaveAccount(view: View) {
        view.findNavController().navigate(R.id.action_mainWindowFragment_to_loginFragment)
    }
}
