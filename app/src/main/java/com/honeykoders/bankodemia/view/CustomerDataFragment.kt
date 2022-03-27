package com.honeykoders.bankodemia.view

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.DatePickerFragment
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentCreateAccountBinding
import com.honeykoders.bankodemia.databinding.FragmentCustomerDataBinding
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_customer_data.*


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
        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.createAccount)
        }

        binding.tietBirthday.setOnFocusChangeListener { view, b ->
            if(binding.tietBirthday.isFocused){
                showDatePickerDialog(view)
            }
        }
        binding.tietBirthday.setOnClickListener {
            view?.let { it1 -> showDatePickerDialog(it1) }
        }
        binding.btnContinuar.setOnClickListener {
            if (!utils.emptyField(tiet_name,til_name) ||
                !utils.emptyField(tiet_lastname,til_lastname) ||
                !utils.emptyField(tiet_work,til_work)||
                !utils.emptyField(tiet_birthday,til_birthday)){
                    findNavController().navigate(R.id.phoneFragment)
            }
        }
        return root
    }
    fun showDatePickerDialog(v: View) {
        val dialogFecha = DatePickerFragment{year, month, day -> showDate(year, month, day) }
        activity?.let { dialogFecha.show(it.getSupportFragmentManager(), "datePicker") }
    }

    private fun showDate(year: Int, month: Int, day: Int) {
        binding.tietBirthday.setText("$day/$month/$year")
    }


}