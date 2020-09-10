package com.example.labtesttask.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.labtesttask.R
import com.example.labtesttask.viewmodel.MainWindowViewModel

class MainWindowFragment : Fragment() {

    companion object {
        fun newInstance() = MainWindowFragment()
    }

    private lateinit var viewModel: MainWindowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_window_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainWindowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
