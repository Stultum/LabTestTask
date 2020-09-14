package com.example.labtesttask.fragment


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment

import com.example.labtesttask.R
import com.example.labtesttask.databinding.HelloDialogFragmentBinding
import com.example.labtesttask.database.Profile

class HelloDialogFragment : DialogFragment() {
    private lateinit var binding: HelloDialogFragmentBinding

    companion object {
        private const val PROFILE = "profile"

        fun newInstance(profile: Profile): HelloDialogFragment {
            val bundle = Bundle()
            bundle.putSerializable(PROFILE, profile)
            val dialog = HelloDialogFragment()
            dialog.arguments = bundle
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.hello_dialog_fragment, container, false)
        binding.profile = arguments?.getSerializable(PROFILE) as Profile
        this.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        this.dialog?.window?.attributes?.windowAnimations = R.style.DialogAnimation
        binding.thanksButton.setOnClickListener { dismiss() }
        return binding.root
    }
}