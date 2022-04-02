package com.honeykoders.bankodemia.ui.model
/*
* Example:
* {
"token": "hHeEaDeRr.PpAayYLloOaAdD.SsiGgNnaAtTuRreE",
"expiresIn": "5m"
}
* */
data class ResponseUserLoggedIn(
 val token:String,
 val expiresIn:String
)
