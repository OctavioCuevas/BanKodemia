package com.honeykoders.bankodemia.test

import com.honeykoders.bankodemia.common.Utils
//Borrar este archivo a la entrega de proyecto

fun main () {
    val utils: Utils = Utils()
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
    //utils.updateSharedPreferences("string", "email", "correo@hotmail.com")
    //utils.updateSharedPreferences("string", "enviar_correo", "", true)
    //var correo :String? = utils.sharedPref().getString("email", "Empty")
    //var tienePermiso :Boolean? = utils.sharedPref().getBoolean("tiene_permiso", false)
    println(utils.getRandomCard())
    println(utils.getRandomCard())
    println(utils.getRandomCard())
    println(utils.getRandomCard())
    println("Password Kodemia")
    println(utils.verifyPasswordKodemia("mi&con123"))
    println(utils.verifyPasswordKodemia("miCon123"))
    println(utils.verifyPasswordKodemia("miC0"))
    println(utils.verifyPasswordKodemia("PassW0rd"))
    println(utils.verifyPasswordKodemia("123123123"))
    println(utils.verifyPasswordKodemia("spiderman"))
    println(utils.verifyPasswordKodemia("koder2koder4"))
    println(utils.verifyPasswordKodemia("15151515151"))
    println("isRepeated")
    println(utils.isRepeated("111111"))
    println(utils.isRepeated("aaaaaa"))
    println(utils.isConsecutive("123123b"))
    println(utils.isConsecutive("123456"))
    println(utils.isConsecutive("987654"))
    println(utils.isConsecutive("987653"))
    println("haveConsecutive")
    println(utils.haveConsecutive("perro123"))
    println(utils.haveConsecutive("perro1243"))
    println(utils.haveConsecutive("pe456rro1243"))
    print("haveRepeated")
    println(utils.isSequence("123a123"))
    println(utils.isSequence("koderkoder"))
    println(utils.isSequence("123123123"))
    println(utils.isSequence("ab1111ba"))
    println(utils.isSequence("ab11ab11"))
    println(utils.isSequence("koder2koder3"))
}