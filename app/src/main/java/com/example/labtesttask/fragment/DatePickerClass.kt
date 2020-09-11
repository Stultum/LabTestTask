package com.example.labtesttask.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.DatePicker
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerClass:DialogFragment(), DatePickerDialog.OnDateSetListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar: Calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(activity as Context, AlertDialog.THEME_HOLO_LIGHT, this, year, month, day)

        val textView = TextView(activity)

        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)

        textView.layoutParams = layoutParams
        textView.setPadding(20,20,20,20)
        textView.gravity = Gravity.CENTER
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25f)
        textView.text = "Выберите дату своего рождения"
        textView.setTextColor(Color.BLACK)
        textView.setBackgroundColor(Color.WHITE)

        datePickerDialog.setCustomTitle(textView)

        return datePickerDialog
    }

    override fun onDateSet(view: DatePicker?, year:Int, month: Int, dayOfMonth: Int){

    }

}