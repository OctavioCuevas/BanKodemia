package com.honeykoders.bankodemia.test

import com.honeykoders.bankodemia.common.Utils
//Borrar este archivo a la entrega de proyecto

fun main () {
    val utils = HoneyKodersUtils()
    val x = "56.5"
    val y = "6"
    println("$x es entero: ${utils.isIntNumber(x)}")
    println("$y es entero: ${utils.isIntNumber(y)}")
    println("$x es número: ${utils.isNumber(x)}")
    println("$y es número: ${utils.isNumber(y)}")
    var email = "contacto@honeykoders.com"
    println("$email es un correo válido: ${utils.isEmail(email)}")
    email = "honeykoders.com"
    println("$email es un correo válido: ${utils.isEmail(email)}")
    println("$email es un una página válida: ${utils.isWebPage(email)}")
    utils.updateSharedPreferences("string", "email", "correo@hotmail.com")
    utils.updateSharedPreferences("string", "enviar_correo", "", true)
    var correo :String? = utils.sharedPref().getString("email", "Empty")
    var tienePermiso :Boolean? = utils.sharedPref().getBoolean("tiene_permiso", false)
}