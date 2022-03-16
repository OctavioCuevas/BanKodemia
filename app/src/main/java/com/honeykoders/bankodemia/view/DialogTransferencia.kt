package com.honeykoders.bankodemia.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentDialogTransferenciaBinding
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
import com.honeykoders.bankodemia.databinding.FragmentTransferenciaBinding
import com.honeykoders.bankodemia.model.MakeTransaction
import com.honeykoders.bankodemia.model.MakeTransactionPayment
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.viewmodel.SingUpViewModel
import com.honeykoders.bankodemia.viewmodel.TransactionViewModel


class DialogTransferencia : DialogFragment() {
    private var _binding: FragmentDialogTransferenciaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner)
        _binding = FragmentDialogTransferenciaBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}