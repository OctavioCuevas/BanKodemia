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
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
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

    fun setValidationListener(
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

    fun verifyPasswordKodemia(password: String): String {
        return if (password.length >= 6) {
            if (password.matches("^[a-zA-Z0-9]*$".toRegex())) {
                if (isRepeated(password)) {
                    if (!isSequence(password)) {
                        if (!isConsecutive(password)) {
                            //por si el requerimiento es que la contraseña "Koder123" sea inválda
                            if (!haveConsecutive(password)) {
                                if (findInCommonPasswords(password)) {
                                    "ok"
                                } else {
                                    "La contraseña está en la lista de contraseñas muy comunes"
                                }
                            } else {
                                "La contraseña no puede tener números consecutivos"
                            }
                        } else {
                            "La contraseña no puede ser números consecutivos"
                        }
                    } else {
                        "La contraseña no puede ser una secuencia"
                    }
                } else {
                    "La contraseña no puede ser una secuencia"
                }
            } else
                "La contraseña solo puede ser alfanúmerica"
        } else {
            "La contraseña debe tener 6 o más caracteres"
        }
    }

    fun isSequence(string: String): Boolean {
        val length = string.length / 2
        var firstPart = string.substring(0, length)
        var secondPart = string.substring(length + if (string.length.mod(2) == 0) 0 else 1)
        if (firstPart == secondPart) {
            return true
        } else {
            if (firstPart == secondPart.reversed()) {
                return true
            } else {
                var temp = firstPart
                while (temp.length > 1) {
                    if (checkSequence(string, temp)) {
                        return true
                    }
                    temp = temp.substring(0, temp.length - 1)
                }
                return false
            }
        }
    }

    private fun checkSequence(string: String, needle: String): Boolean {
        var formedString = needle
        while (formedString.length < string.length) {
            formedString += needle
        }
        return formedString == string
    }

    fun haveConsecutive(string: String): Boolean {
        val length = string.length
        val chars: Array<String> =
            string.toCharArray().map { it.toString() }.toTypedArray()
        var currentChar = 0
        while (currentChar < length - 2) {
            if (checkConsecutive(chars, currentChar)) {
                return true
            } else {
                currentChar++
            }
        }
        return false
    }

    fun checkConsecutive(chars: Array<String>, current_char: Int): Boolean {
        val string = chars[current_char] + chars[current_char + 1] + chars[current_char + 2]
        return if (isIntNumber(string)) {
            isConsecutive(string)
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
        return number.length == 10
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

    fun parseHour(date: String): String {
        var hour = ""
        for (i in 11..15) {
            hour = hour + date[i].toString()
        }
        return hour
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

    fun stringToDate(date: String): Date {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        //sdf.timeZone(TimeZone.getTimeZone("GMT"))
        return sdf.parse(date)
    }

    fun stringToTime(date: String): Date {
        val sdf = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
        //sdf.timeZone(TimeZone.getTimeZone("GMT"))
        return sdf.parse(date)
    }

    fun formatTimeStamp(date: Date, type: Int): String? {
        var format = when (type) {
            1 -> SimpleDateFormat("dd 'de' MMMM 'de' yyyy hh:mm aa", Locale.ENGLISH)
            2 -> SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa", Locale.ENGLISH)
            3 -> SimpleDateFormat("EEEE, dd 'de' MMMMMMM 'de' yyyy hh:mm aa", Locale.ENGLISH)
            4 -> SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
            5 -> SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.ENGLISH)
            else ->
                SimpleDateFormat("dd 'de' MMMMMM 'de' yyyy (hh:mm aa)", Locale.ENGLISH)
        }
        return format.format(date)
    }

    fun formatTime(date: Date, type: Int): String? {
        var format = when (type) {
            1 -> SimpleDateFormat("hh:mm aa", Locale.ENGLISH)
            2 -> SimpleDateFormat("hh:mm:ss aa", Locale.ENGLISH)
            else ->
                SimpleDateFormat("hh:mm", Locale.ENGLISH)
        }
        return format.format(date)
    }

    fun translateDate(date: String): String {
        var date = date
        date = date.replace("January", "Enero")
        date = date.replace("February", "Febrero")
        date = date.replace("March", "Marzo")
        date = date.replace("April", "Abril")
        date = date.replace("May", "Mayo")
        date = date.replace("June", "Junio")
        date = date.replace("July", "Julio")
        date = date.replace("August", "Agosto")
        date = date.replace("September", "Septiembre")
        date = date.replace("October", "Octubre")
        date = date.replace("November", "Noviembre")
        date = date.replace("December", "Diciembre")

        date = date.replace("Monday", "Lunes")
        date = date.replace("Tuesday", "Martes")
        date = date.replace("Wednesday", "Miércoles")
        date = date.replace("Thursday", "Jueves")
        date = date.replace("Friday", "Viernes")
        date = date.replace("Saturday", "Sábado")
        date = date.replace("Sunday", "Domingo")
        return date
    }

}

