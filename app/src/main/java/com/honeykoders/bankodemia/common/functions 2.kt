package com.honeykoders.bankodemia.common

import android.content.Context
import android.text.Editable
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.honeykoders.bankodemia.R

/*Valida que la cantidad a enviar no sea 0 o null y transforma en Double la cantidad*/
fun validarCantidad(value: Editable?):Int{
    if (value != null) {
        if(value.isNotEmpty()){
            val result = value.trim().toString().toInt()
            if(result != null){
                return result
            }
            else
                return 0
        }else {
            return 0
        }
    }else{
        return 0
    }
}

fun mostrarMensajeDeError(context: Context, mensaje: String){
    Toast.makeText(context,mensaje, Toast.LENGTH_LONG).show()
}

