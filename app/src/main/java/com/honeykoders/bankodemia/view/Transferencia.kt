package com.honeykoders.bankodemia.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.HoneyKodersUtils
import com.honeykoders.bankodemia.databinding.FragmentDialogTransferenciaBinding
import com.honeykoders.bankodemia.databinding.FragmentTransferenciaBinding
import com.honeykoders.bankodemia.model.MakeTransactionPayment
import com.honeykoders.bankodemia.viewmodel.TransactionViewModel


class Transferencia : Fragment() {
    private var _binding: FragmentTransferenciaBinding? = null
    private val binding get() = _binding!!
    val viewModel: TransactionViewModel by viewModels()
    val utils: HoneyKodersUtils = HoneyKodersUtils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferenciaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val btnTransferencia: Button = binding.btnTransferencia
        val tiet_concepto: TextInputEditText = binding.tietConcepto
        val tiet_cantidad: TextInputEditText = binding.tietCantidad

        observers()

        btnTransferencia?.setOnClickListener {
            showDialog()
            val cantidadEnviada = utils.numberValidationInt(tiet_cantidad.text)
            tiet_cantidad.setText(cantidadEnviada.toString())
            Toast.makeText(context, cantidadEnviada.toString(), Toast.LENGTH_LONG).show()
        }

        /*tiet_cantidad.setOnFocusChangeListener { view: View, b: Boolean ->
            tiet_cantidad.setText("")
        }*/

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
        val builder = AlertDialog.Builder(context)
        val bindingDialog : FragmentDialogTransferenciaBinding = FragmentDialogTransferenciaBinding.inflate(layoutInflater)
        builder.setView(bindingDialog.root)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()
        bindingDialog.apply {
            btnAceptar.setOnClickListener {
                makeTransaction()
                //Toast.makeText(context,"Transferencia Realizada",Toast.LENGTH_LONG).show()
                //findNavController().navigate(R.id.procesandoTransaccion)
                dialog.dismiss()
            }
        }
    }

    fun makeTransaction(){
        val cantidad = binding.tietCantidad.text.toString().toInt()
        val concepto = binding.tietConcepto.text.toString()
        /*val makeTransaction = MakeTransactionPayment(
            10,
            "PAYMENT",
            "62186df3c460bd3d86a67521",
            "Pago comida gatos",
        )*/
        val makeTransaction = MakeTransactionPayment(
            cantidad,
            "PAYMENT",
            "62186df3c460bd3d86a67521",
            concepto,
        )
        mandarDatos(makeTransaction)
    }

    private fun mandarDatos(makeTransactionPayment: MakeTransactionPayment) {
        viewModel.makeTransactionPayment(makeTransactionPayment)
    }

    fun observers() {
        viewModel.makeTransactionResponse.observe(viewLifecycleOwner){ makeTransaction ->
            Log.e("SingUp",makeTransaction.success.toString())
            if(makeTransaction.success){
                findNavController().navigate(R.id.transaccionFinalizada)
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){ loading ->
            Log.e("Pase por aqui",loading.toString())
            if (loading){
                findNavController().navigate(R.id.procesandoTransaccion)
            }else{
                findNavController().navigate(R.id.transaccionFinalizada)
            }
        }

        viewModel.badRequest.observe(viewLifecycleOwner){ badRequest ->
            if (badRequest){
                Log.e("badRequest",badRequest.toString())
                context?.let { utils.showMessage(it,"Hubo un error, intente más tarde") }
            }
        }
        viewModel.broken.observe(viewLifecycleOwner){ broken ->
            if (broken){
                Log.e("Broken",broken.toString())
                context?.let { utils.showMessage(it,"Estas quebrado.") }
            }
        }
        viewModel.inssuficientFunds.observe(viewLifecycleOwner){ inssuficientFunds ->
            if (inssuficientFunds){
                Log.e("inssuficientFunds",inssuficientFunds.toString())
                context?.let { utils.showMessage(it,"Fondos insuficientes") }
            }
        }
    }
}