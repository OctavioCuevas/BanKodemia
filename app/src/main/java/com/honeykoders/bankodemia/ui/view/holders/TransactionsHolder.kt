package com.honeykoders.bankodemia.view.holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R

class TransaccionHolder (view: View) : RecyclerView.ViewHolder(view){
    val cardView: ConstraintLayout = view.findViewById(R.id.layout_item_home)
    val tvTransactionConcept: TextView = view.findViewById(R.id.iv_transaction_concept)
    val tvTransactionHour: TextView = view.findViewById(R.id.tv_transaction_hour)
    val tvTransactionAmount: TextView = view.findViewById(R.id.tv_transaction_amount)
}