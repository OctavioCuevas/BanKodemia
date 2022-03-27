package com.honeykoders.bankodemia.model

class Customer(
    var name: String = "",
    var last_name: String = "",
    var occupation: String = ""
) {

    fun getCusomerData() {
        println("Name: $name\n" +
                "Last name: $last_name\n" +
                "Occupation: $occupation\n")
    }
}