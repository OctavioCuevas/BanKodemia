package com.honeykoders.bankodemia.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.ui.model.Transactions
import com.honeykoders.bankodemia.view.holders.TransaccionHolder

class TransactionsAdapter(
    val activity: Activity, val transactions:
    MutableList<Transactions>
) :
    RecyclerView.Adapter<TransaccionHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransaccionHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cardview_home, parent, false)
        return TransaccionHolder(view)
    }

    @SuppressLint("SetTextI18n", "ResourceAsColor")
    override fun onBindViewHolder(holder: TransaccionHolder, position: Int) {
        val transaction = transactions.get(position)
        val utils = Utils()
        activity.let { it1 -> utils.initSharedPreferences(it1) }
        val idUser = utils.getSharedPreferencesByName("userId")
        with(holder) {
            cardView.setOnClickListener {
                utils.updateSharedPreferences(
                    "string",
                    "amount",
                    transaction.amount.toString(),
                    false,
                    0,
                    0.0f
                )
                utils.updateSharedPreferences(
                    "string",
                    "date",
                    transaction.created_at,
                    false,
                    0,
                    0.0f
                )
                utils.updateSharedPreferences(
                    "string",
                    "concept",
                    transaction.concept,
                    false,
                    0,
                    0.0f
                )
                it.findNavController().navigate(R.id.detailsTransaction)
            }
            //Dar el concepto
            tvTransactionConcept.text = transaction.concept

            //Dar formato de hora
            val hour_str = transaction.created_at.substring(11,19)
            tvTransactionHour.text = utils.formatTime(utils.stringToTime(hour_str),1)

            //Verificar si es un Activo o un Pasivo
            tvTransactionAmount.setTextColor(Color.parseColor("#00CCCC"))

            tvTransactionAmount.text = "+ $" + transaction.amount.toString() + ".00"
            if (transaction.type != "DEPOSIT" && !transaction.isIncome) {
                tvTransactionAmount.text = "- $" + transaction.amount.toString() + ".00"
                tvTransactionAmount.setTextColor(Color.RED)
            }

            //Hace el efecto de Zebra en el recycler
            if (position % 2 == 0) {
                holder.itemView.setBackgroundResource(R.color.white);
            } else {
                holder.itemView.setBackgroundResource(R.color.bgItemCV);
            }
        }

    }

    override fun getItemCount(): Int = transactions.size
}