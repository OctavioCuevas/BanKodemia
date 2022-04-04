package com.honeykoders.bankodemia.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.toColorFilter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors.getColor
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.model.Transaction
import com.honeykoders.bankodemia.model.Transactions
import com.honeykoders.bankodemia.view.holders.TransferHolder


class TransferAdapter(private val context: Context, private val listTransfer: List<Transactions>):
    RecyclerView.Adapter<TransferHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cardview_home,parent,false)
        return TransferHolder(view)
    }

    override fun onBindViewHolder(holder: TransferHolder, position: Int) {
        val utils = Utils()
        val transaction = listTransfer.get(position)

        with(holder){
            if(position % 2 == 0)
            {
                holder.itemView.setBackgroundResource(R.color.white);
            }
            else
            {
                holder.itemView.setBackgroundResource(R.color.bgItemCV);
            }
            val hour = utils.parseHour(transaction.created_at)
            tv_concept.text = transaction.concept
            tv_hour.text = hour

            if(transaction.isIncome){
                tv_amount.text = "+" + transaction.amount.toString() + ".00"
            }else{
                tv_amount.text = "-" + transaction.amount.toString() + ".00"
            }
            transactionLayout.setOnClickListener {
                context?.let { it1 -> utils.initSharedPreferences(it1) }
                utils.updateSharedPreferences("string","amount",transaction.amount.toString() ,false,0,0.0f)
                utils.updateSharedPreferences("string","date",transaction.created_at ,false,0,0.0f)
                utils.updateSharedPreferences("string","concept",transaction.concept ,false,0,0.0f)

                it.findNavController().navigate(R.id.detailsTransaction)

            }
        }
    }
    override fun getItemCount(): Int = listTransfer.size
}