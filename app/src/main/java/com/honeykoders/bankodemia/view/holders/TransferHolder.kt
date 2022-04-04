package com.honeykoders.bankodemia.view.holders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R

class TransferHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tv_concept: TextView = view.findViewById(R.id.textView_concepto_movimiento)
    val tv_hour: TextView = view.findViewById(R.id.textView_hora_concepto)
    val tv_amount: TextView = view.findViewById(R.id.textView_monto_movimiento)
    val transactionLayout: ConstraintLayout = view.findViewById(R.id.transactionLayout)
}