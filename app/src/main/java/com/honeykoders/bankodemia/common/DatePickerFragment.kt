package com.honeykoders.bankodemia.common

import android.app.DatePickerDialog
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.honeykoders.bankodemia.R
import java.util.*

class DatePickerFragment(val listener: (year: Int, month: Int, day: Int) -> Unit) :
    DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dp = DatePickerDialog(
            requireActivity(),
            R.style.customDatePickerStyle,
            this,
            year,
            month,
            day
        )
            .also {
                it.setButton(DatePickerDialog.BUTTON_POSITIVE, "Aceptar", it)
                it.getButton(DatePickerDialog.BUTTON_POSITIVE)
            }
        return dp
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        listener(year, month + 1, day)
    }
}