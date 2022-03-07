package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentDialogTransferenciaBinding


class DialogTransferencia : DialogFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.round_corner)
        return inflater.inflate(R.layout.fragment_dialog_transferencia, container, false)
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width,ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}