package com.example.labtesttask.binding

import android.annotation.SuppressLint
import android.widget.Button
import androidx.databinding.BindingAdapter
import com.example.labtesttask.R
import kotlinx.android.synthetic.main.login_fragment.view.*

@SuppressLint("ResourceAsColor")
@BindingAdapter("android:enabledAndBackground")
fun setEnabledAndBackground(view: Button, enabled: Boolean) {
    view.isEnabled = enabled
    if (enabled)
    {
        view.regButton.setBackgroundResource(R.drawable.button_background)
        view.regButton.setTextColor(R.color.font_color)
    }
    else
    {
        view.regButton.setBackgroundResource(R.drawable.button_background_transparent)
        view.regButton.setTextColor(R.color.font_bright_color)
    }
}