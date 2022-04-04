package com.honeykoders.bankodemia.view.holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.honeykoders.bankodemia.R

class TransaccionHolder (view: View) : RecyclerView.ViewHolder(view){
    val cardView: ConstraintLayout = view.findViewById(R.id.layout_item_home)
    val tv_concepto_movimiento: TextView = view.findViewById(R.id.textView_concepto_movimiento)
    val tv_hora_concepto: TextView = view.findViewById(R.id.textView_hora_concepto)
    val tv_monto_movimiento: TextView = view.findViewById(R.id.textView_monto_movimiento)
}