package com.honeykoders.bankodemia

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.honeykoders.bankodemia.common.HoneyKodersUtils
import com.honeykoders.bankodemia.model.Customer
import kotlinx.android.synthetic.main.fragment_customer_data.*

class CustomerDataFragment : Fragment() {

    val utils = HoneyKodersUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_data, container, false)
    }

    private fun initComponents() {
        val spannable =
            SpannableStringBuilder("Esta información es necesaria para verificar tu identidad. Nunca la usaremos sin tu consentimiento")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            59, // start
            200, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        //val tvInfo: TextView = findViewById(R.id.tv_info)
        tv_info.text = spannable

        btnContinueCD.setOnClickListener {
            if (validateForm()) {
                saveData(
                    tiet_name.text?.trim().toString(),
                    tiet_lastname.text?.trim().toString(),
                    tiet_occupation.text?.trim().toString()
                )
            }
        }
    }

    private fun validateForm(): Boolean {
        if (utils.fieldValidation("string", tiet_name, til_name, "Nombre requerido")) {
            if (utils.fieldValidation(
                    "string",
                    tiet_lastname,
                    til_lastname,
                    "Apellido requerido"
                )
            ) {
                return utils.fieldValidation(
                    "string",
                    tiet_occupation,
                    til_occupation,
                    "Nombre requerido"
                )
            } else {
                return false
            }
        } else {
            return false
        }
    }

    private fun saveData(name: String, lastname: String, occupation: String) {
        var customer = Customer().apply {
            this.name = name.lowercase()
            this.last_name = lastname.lowercase()
            this.occupation = occupation.lowercase()
        }.also {
            setFragmentResult("weon", bundleOf("bundleKey" to "weafome"))
        }
    }
}