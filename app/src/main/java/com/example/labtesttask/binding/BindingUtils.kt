package com.example.labtesttask.binding

import android.widget.Button
import androidx.databinding.BindingAdapter
import com.example.labtesttask.R
import kotlinx.android.synthetic.main.login_fragment.view.*

@BindingAdapter("android:enabledAndBackground")
fun setEnabledAndBackground(view: Button, enabled: Boolean) {
    view.isEnabled = enabled
    if (enabled)
        view.regButton.setBackgroundResource(R.drawable.button_background)
    else
        view.regButton.setBackgroundResource(R.drawable.button_background_transparent)
}