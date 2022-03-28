package com.honeykoders.bankodemia.common

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.preference.PreferenceManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.exceptions.VariableTypeNotFoundException
import java.util.regex.Pattern


class Utils() {

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
                    if (isIntNumber(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        false
                    }
                in "double" ->
                    if (isNumber(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        false
                    }
                in "phone" ->
                    if (isPhoneNumberValid(tiet.text.toString())
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

    fun matchPassword(
        tietPsw: TextInputEditText,
        tilPsw: TextInputLayout,
        tietMatchPsw: TextInputEditText,
        tilMatchPsw: TextInputLayout
    ): Boolean {

        val match = tietPsw.text.toString().equals(tietMatchPsw.text.toString())
        val message = "Las contraseñas no coinciden"
        if (match) {
            tilPsw.isErrorEnabled = false
            tilMatchPsw.isErrorEnabled = false
        } else {
            tilPsw.error = message
            tilPsw.error = message
        }
        return match
    }

    fun isPhoneNumberValid(number: String): Boolean {
        return number.length < 10
    }

    fun emptyField(tiet: TextInputEditText, til: TextInputLayout): Boolean {
        if (tiet.text.toString().trim().isEmpty()) {
            til.error = "Este campo es requerido"
            return true
        } else {
            til.isErrorEnabled = false
            til.error = ""
            return false
        }
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
        int_val: Int = 0,
        float_val: Float = 0.0f
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

    fun sharedPref(): SharedPreferences {
        return sharedPreferences;
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        fun showMessage(context: Context, message: Int) {
            Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG).show()
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                return true
            }
        }
        return false
    }
}

