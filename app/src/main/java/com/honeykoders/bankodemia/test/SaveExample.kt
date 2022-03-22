package com.honeykoders.bankodemia.test

//Borrar este archivo a la entrega de proyecto

//Ejemplo de como procesar la info de un formulario
//En este caso se está guardando en sharepreferences pero hay que cambiarlo para que consuma la api
/*private fun saveData(uName: String, uEmail: String, uPassword: String) {
    val user = User().apply {
        name = uName.lowercase()
        email = uEmail.lowercase()
        password = uPassword
    }.also {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val uKey = "username"
        val uPass = "password"
        val uEmail = "email"
        val storedUser = prefs.getString(uKey, "NaN")
        val editor = prefs.edit()
        editor.putString(uKey, it.name)
        editor.putString(uPass, it.password)
        editor.putString(uEmail, it.email)
        editor.apply()
    }
}*/