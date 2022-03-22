package com.honeykoders.bankodemia.common

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.preference.PreferenceManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.honeykoders.bankodemia.exceptions.VariableTypeNotFoundException


class HoneyKodersUtils() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    /*Valida que la cantidad a enviar no sea 0 o null y transforma en entero la cantidad*/
    fun numberValidationInt(value: Editable?): Int {
        return if (value != null) {
            if (value.isNotEmpty()) {
                value.trim().toString().toInt() ?: 0
            } else {
                0
            }
        } else {
            0
        }
    }

    // Valida el campo que se ha ingresado el un campo cualquiera
    fun fieldValidation(
        type: String,
        tiet: TextInputEditText,
        til: TextInputLayout,
        errorMessage: String
    ): Boolean {
        var ok = true;
        if (tiet.text.toString().isEmpty()) {
            ok = false
        } else {
            when (type) {
                in "email" ->
                    if (isEmail(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        false
                    }
                in "webpage" ->
                    if (isWebPage(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        false
                    }
                in "int" ->
                    if (isIntNumber(tiet.text.toString())
                    ) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        false
                    }
                else -> {
                    println("ok")
                }
            }

        }
        return ok
    }

    fun isIntNumber(value: String): Boolean {
        return value.isNotEmpty() && value.all(Char::isDigit)
    }

    fun isNumber(value: String): Boolean {
        return try {
            value.toDouble()
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isEmail(value: String): Boolean {
        return PatternsCompat.EMAIL_ADDRESS.matcher(value).matches()
    }

    fun isWebPage(value: String): Boolean {
        return PatternsCompat.WEB_URL.matcher(value).matches()
    }

    private fun setValidationListener(
        til: TextInputLayout,
        tiet: TextInputEditText,
        errorMessage: String
    ) {
        tiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(txt: Editable?) {
                if (txt.toString().isEmpty()) {
                    til.error = errorMessage
                } else {
                    til.isErrorEnabled = false
                    til.error = ""
                }
            }
        })
    }

    fun initSharedPreferences(context: Context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        editor = sharedPreferences.edit()
    }

    fun updateSharedPreferences(
        type: String,
        name: String,
        str_val: String,
        bol_val: Boolean = false,
        int_val: Int,
        float_val: Float
    ) {
        when (type) {
            "boolean" -> this.editor.putBoolean(name, bol_val)
            "float" -> this.editor.putFloat(name, float_val)
            "int" -> this.editor.putInt(name, int_val)
            "string" -> this.editor.putString(name, str_val)
            else -> throw VariableTypeNotFoundException()
        }
        editor.apply()
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}

