package com.honeykoders.bankodemia.ui.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentDialogTransferenciaBinding
import com.honeykoders.bankodemia.databinding.FragmentTransferenciaBinding
import com.honeykoders.bankodemia.ui.model.MakeTransactionPayment
import com.honeykoders.bankodemia.ui.viewmodel.TransactionViewModel


class Transferencia : Fragment() {
    private var _binding: FragmentTransferenciaBinding? = null
    private val binding get() = _binding!!
    val viewModel: TransactionViewModel by viewModels()
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransferenciaBinding.inflate(inflater,container,false)
        val root: View = binding.root
        initComponents()
        observers()

        binding.btnTransferencia.setOnClickListener {
            showDialog()
            val cantidadEnviada = utils.numberValidationInt(binding.tietCantidad.text)
            binding.tietCantidad.setText(cantidadEnviada.toString())
        }

        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.sendMoney)
        }

        return root

    }

    private fun initComponents() {
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        context?.let { viewModel.onCreate(context = it) }
        val transferTo = utils.getSharedPreferencesByName("contactName")
        binding.tvNombreBeneficiario.text = transferTo
        binding.tvCuentaBanco.text = utils.getSharedPreferencesByName("accountNumber")
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
                dialog.dismiss()
            }
        }
    }

    fun makeTransaction(){
        val cantidad = binding.tietCantidad.text.toString().toInt()
        val concepto = binding.tietConcepto.text.toString()
        val contactId = utils.getSharedPreferencesByName("contactId").toString()
        Log.e("ContactIDTransfer",contactId)

        val makeTransaction = MakeTransactionPayment(
            cantidad,
            "PAYMENT",
            contactId,
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
                binding.contenedorPrincipal.visibility = View.GONE
                binding.contenedorCarga.visibility = View.VISIBLE
            }else{
                binding.contenedorPrincipal.visibility = View.VISIBLE
                binding.contenedorCarga.visibility = View.GONE
            }
        }

        viewModel.badRequest.observe(viewLifecycleOwner){ badRequest ->
            if (badRequest){
                Log.e("badRequest",badRequest.toString())
                context?.let { utils.showMessage(it,getString(R.string.serverError)) }
                findNavController().navigate(R.id.transaccionFinalizada)
            }
        }
        viewModel.broken.observe(viewLifecycleOwner){ broken ->
            if (broken){
                Log.e("Broken",broken.toString())
                context?.let { utils.showMessage(it,getString(R.string.broken)) }
                findNavController().navigate(R.id.transaccionFinalizada)
            }
        }
        viewModel.insufficientFunds.observe(viewLifecycleOwner){ insufficientFunds ->
            if (insufficientFunds){
                Log.e("inssuficientFunds",insufficientFunds.toString())
                context?.let { utils.showMessage(it,getString(R.string.insufficientFunds)) }
                findNavController().navigate(R.id.transaccionFinalizada)
            }
        }
    }
}