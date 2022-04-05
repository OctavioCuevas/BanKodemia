package com.honeykoders.bankodemia.ui.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.DatePickerFragment
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentCustomerDataBinding
import kotlinx.android.synthetic.main.fragment_customer_data.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CustomerDataFragment : Fragment() {

    private var _binding: FragmentCustomerDataBinding? = null
    private val binding get() = _binding!!
    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomerDataBinding.inflate(inflater, container, false)
        val root: View = binding.root

        utils.setValidationListener(
            binding.tilName,
            binding.tietName,
            getString(R.string.error_name)
        )
        utils.setValidationListener(
            binding.tilLastname,
            binding.tietLastname,
            getString(R.string.error_lastname)
        )
        utils.setValidationListener(
            binding.tilOccupation,
            binding.tietOccupation,
            getString(R.string.error_occupation)
        )

        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.createAccount)
        }

        binding.tietBirthday.setOnFocusChangeListener { view, b ->
            if (binding.tietBirthday.isFocused) {
                showDatePickerDialog(view)
            }
        }
        binding.tietBirthday.setOnClickListener {
            view?.let { it1 -> showDatePickerDialog(it1) }
        }
        binding.btnContinueCD.setOnClickListener {
            if (!utils.emptyField(tiet_name, til_name) &&
                !utils.emptyField(tiet_lastname, til_lastname) &&
                !utils.emptyField(tiet_occupation, til_occupation) &&
                !utils.emptyField(tiet_birthday, til_birthday)
            ) {
                saveCustomerData()
                findNavController().navigate(R.id.phoneFragment)
            }
        }
        return root
    }

    private fun saveCustomerData() {
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        utils.updateSharedPreferences(
            "string",
            "name",
            binding.tietName.text.toString(),
            false,
            0,
            0.0f
        )
        utils.updateSharedPreferences(
            "string",
            "lastName",
            binding.tietLastname.text.toString(),
            false,
            0,
            0.0f
        )
        utils.updateSharedPreferences(
            "string",
            "occupation",
            binding.tietOccupation.text.toString(),
            false,
            0,
            0.0f
        )
    }


    private fun showDatePickerDialog(v: View) {
        val dateDialog = DatePickerFragment { year, month, day -> showDate(year, month, day) }
        activity?.let {
            dateDialog.show(it.supportFragmentManager, "datePicker")
        }
    }


    private fun showDate(year: Int, month: Int, day: Int) {
        val date = "$day/$month/$year"
        binding.tietBirthday.setText(date)
        dateFormat(year, month, day)
    }


    fun dateFormat(year: Int, month: Int, day: Int) {
        var formatDate = ""
        Log.e("mont", month.toString())
        if (month < 9) {
            formatDate = "$year-0$month-0$day"
        } else {
            formatDate = "$year-$month-$day"
        }
        Log.e("Date1", formatDate)
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        utils.updateSharedPreferences("string", "birthDate", formatDate, false, 0, 0.0f)
    }
}