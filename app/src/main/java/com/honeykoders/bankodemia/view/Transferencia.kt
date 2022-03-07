package com.honeykoders.bankodemia.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.validarCantidad
import com.honeykoders.bankodemia.databinding.FragmentTransferenciaBinding

private var _binding: FragmentTransferenciaBinding? = null

// This property is only valid between onCreateView and
// onDestroyView.
private val binding get() = _binding!!

class Transferencia : Fragment() {


    //val tiet_concepto: TextInputEditText = binding.tietConcepto

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferenciaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnTransferencia: Button = binding.btnTransferencia
        val tiet_concepto: TextInputEditText = binding.tietConcepto
        val tiet_cantidad: TextInputEditText = binding.tietCantidad
        btnTransferencia?.setOnClickListener {
            showDialog()
            val cantidadEnviada = validarCantidad(tiet_cantidad.text)
            tiet_cantidad.setText(cantidadEnviada.toString())
            Toast.makeText(context, cantidadEnviada.toString(), Toast.LENGTH_LONG).show()
        }

        tiet_cantidad.setOnFocusChangeListener { view: View, b: Boolean ->
            tiet_cantidad.setText("")
        }

        tiet_concepto.setOnFocusChangeListener { view: View, b: Boolean ->
            if (tiet_concepto.text.toString() == getString(R.string.pago_croque)) {
                tiet_concepto.setText("")
            }
        }
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDialog() {
        DialogTransferencia().show(childFragmentManager,"Custom Fragment")
    }
}