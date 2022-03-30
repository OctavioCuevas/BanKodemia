package com.honeykoders.bankodemia.common

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.core.util.PatternsCompat
import androidx.preference.PreferenceManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.honeykoders.bankodemia.exceptions.VariableTypeNotFoundException
import java.util.regex.Pattern
import kotlin.random.Random


class Utils {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    /*Valida que la cantidad a enviar no sea 0 o null y transforma en entero la cantidad*/
    fun numberValidationInt(value: Editable?): Int {
        return if (value != null) {
            if (value.isNotEmpty()) {
                value.trim().toString().toInt()
            } else {
                0
            }
        } else {
            0
        }
    }

    fun validateImageTaken(image: String?): Boolean {
        return !(image.isNullOrBlank() || image.isNullOrEmpty())
    }

    // Valida el campo que se ha ingresado el un campo cualquiera
    fun fieldValidation(
        type: String,
        tiet: TextInputEditText,
        til: TextInputLayout,
        errorMessage: String
    ): Boolean {
        var ok = true
        if (tiet.text.toString().isEmpty()) {
            ok = false
        } else {
            when (type) {
                in "email" ->
                    if (isEmail(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        ok = false
                    }
                in "webpage" ->
                    if (isWebPage(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        ok = false
                    }
                in "int" ->
                    if (isIntNumber(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        ok = false
                    }
                in "double" ->
                    if (isNumber(tiet.text.toString())) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        ok = false
                    }
                in "phone" ->
                    if (isPhoneNumberValid(tiet.text.toString())
                    ) {
                        til.isErrorEnabled = false
                    } else {
                        til.error = errorMessage
                        ok = false
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

    fun verifyPassword(password: String): String {
        var message = "Ok"
        val regex: Regex
        if (password.length < 6) { //al menos 6 caracteres
            message = "Contraseña debe tener 6 o más caracteres"
        } else { //valida que sean alfanuméricos
            regex = Regex("[a-zA-Z0-9.? ]*")
            if (!password.matches(regex)) {
                message = "Contraseña NO debe tener carácteres especiales"
            }
        }
        return message
    }

    fun verifyPassword2(password: String): Boolean {
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +        //at least 1 lower case letter
                    "(?=.*[A-Z])" +        //at least 1 upper case letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 4 characters
                    "$"
        )
        return passwordRegex.matcher(password).matches()
    }

    fun verifyPasswordKodemia(password: String): Boolean {
        return if (password.length >= 6) {
            if (password.matches("^[a-zA-Z0-9]*$".toRegex())) {
                if (isRepeated(password)) {
                    return if (!isConsecutive(password)) {
                        findInCommonPasswords(password)
                    } else {
                        false
                    }
                } else {
                    false
                }
            } else
                false
        } else {
            false
        }
    }

    fun isConsecutive(string: String): Boolean {
        if (string.length > 2) {
            if (isIntNumber(string)) {
                val numbers: Array<Int> =
                    string.toCharArray().map { Integer.parseInt(it.toString()) }.toTypedArray()
                val initialVal = numbers[0]
                var isUp = false
                var isDown = false
                var i = 0
                if (initialVal in 0..7) {
                    val finalUpwardsVal = initialVal + string.length - 1
                    if (finalUpwardsVal <= 9) {
                        isUp = true
                        for (n in initialVal..finalUpwardsVal) {
                            if (numbers[i] != n) {
                                isUp = false
                                break
                            }
                            i++
                        }
                    }
                }
                if (isUp) {
                    return true
                } else {
                    if (initialVal in 4..9) {
                        val finalDownVal = initialVal - string.length + 1
                        if (finalDownVal >= 0) {
                            i = 0
                            isDown = true
                            for (n in initialVal downTo finalDownVal) {
                                if (numbers[i] != n) {
                                    isDown = false
                                    break
                                }
                                i++
                            }
                        }
                    }
                    return isDown
                }
            }
        }
        return false
    }


    fun isRepeated(string: String): Boolean {
        var temp: Char? = null
        string.toCharArray().map { c ->
            if (temp != null) {
                if (temp != c)
                    return true
            }
            temp = c
        }
        return false
    }

    fun findInCommonPasswords(password: String): Boolean {
        val passwords = listOf(
            "password",
            "123123",
            "contraseña",
            "12345678",
            "qwerty",
            "123456789",
            "1234567",
            "dragon",
            "abc123",
            "abcabc",
            "qwertyuiop",
            "666666",
            "123321",
            "superman",
            "654321",
            "zxcvbnm",
            "asdfgh",
            "batman",
            "spiderman"
        )
        return passwords.indexOf(password) == -1
    }

    fun matchPassword(
        tietPsw: TextInputEditText,
        tilPsw: TextInputLayout,
        tietMatchPsw: TextInputEditText,
        tilMatchPsw: TextInputLayout
    ): Boolean {
        val match = tietPsw.text.toString() == tietMatchPsw.text.toString()
        val message = "Las contraseñas no coinciden"
        if (match) {
            tilPsw.isErrorEnabled = false
            tilMatchPsw.isErrorEnabled = false
        } else {
            tilPsw.error = message
            tilMatchPsw.error = message
        }
        return match
    }

    fun isPhoneNumberValid(number: String): Boolean {
        return number.length < 10
    }

    fun emptyField(tiet: TextInputEditText, til: TextInputLayout): Boolean {
        return if (tiet.text.toString().trim().isEmpty()) {
            til.error = "Este campo es requerido"
            true
        } else {
            til.isErrorEnabled = false
            til.error = ""
            false
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
        return sharedPreferences
    }

    fun showMessage(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
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

    fun getSharedPreferencesByName(name: String): String? {
        return sharedPreferences.getString(name, "")
    }

    fun clearSharedPreferences() {
        editor.clear().apply()
    }


    fun showMessage(context: Context, message: Int) {
        Toast.makeText(context, context.getString(message), Toast.LENGTH_LONG).show()
    }

    fun getRandomCard(): String {
        val banks = listOf("BBVA", "SANTANDER", "BANORTE", "HSBC", "INVEX", "AFIRME")
        val randomBank = banks.random()
        val randomValues = List(4) { Random.nextInt(1234, 9876) }
        var card = ""
        for (number in randomValues)
            card += " $number"
        return card.trim() + " / " + randomBank
    }

}

