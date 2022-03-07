package com.honeykoders.bankodemia.common

import android.text.Editable
import androidx.fragment.app.Fragment
import com.honeykoders.bankodemia.R

/*Valida que la cantidad a enviar no sea 0 o null y transforma en Double la cantidad*/
fun validarCantidad(value: Editable?):Double{
    if (value != null) {
        if(value.isNotEmpty()){
            val result = value.trim().toString().toDoubleOrNull()
            if(result != null){
                return result
            }
            else
                return 0.00
        }else {
            return 0.00
        }
    }else{
        return 0.00
    }
}
