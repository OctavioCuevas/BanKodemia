package com.honeykoders.bankodemia.view.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.ui.model.Transactions
import com.honeykoders.bankodemia.view.holders.TransaccionHolder

class TransactionsAdapter(
    val activity: Activity, val transacciones:
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
        val transaccion = transacciones.get(position)
        val utils = Utils()
        with(holder) {
            cardView.setOnClickListener {
                activity.let { it1 -> utils.initSharedPreferences(it1) }
                utils.updateSharedPreferences(
                    "string",
                    "amount",
                    transaccion.amount.toString(),
                    false,
                    0,
                    0.0f
                )
                utils.updateSharedPreferences(
                    "string",
                    "date",
                    transaccion.created_at,
                    false,
                    0,
                    0.0f
                )
                utils.updateSharedPreferences(
                    "string",
                    "concept",
                    transaccion.concept,
                    false,
                    0,
                    0.0f
                )
                it.findNavController().navigate(R.id.detailsTransaction)

            }
            //Dar el concepto
            tv_concepto_movimiento.text = transaccion.concept

            //Dar formato de hora
            val hora = transaccion.created_at.substring(11, 13).toInt()
            if (hora > 12) {
                val reformato = transaccion.created_at.substring(11, 13).toInt() - 12
                tv_hora_concepto.text =
                    reformato.toString() + transaccion.created_at.substring(13, 16) + " p.m."
            } else {
                if (hora > 9) {
                    tv_hora_concepto.text = transaccion.created_at.substring(11, 16) + " a.m."
                } else {
                    tv_hora_concepto.text = transaccion.created_at.substring(12, 16) + " a.m."
                }
            }

            //Verificar si es un Activo o un Pasivo
            if (transaccion.isIncome) {
                tv_monto_movimiento.text = "+ $" + transaccion.amount.toString()
                tv_monto_movimiento.setTextColor(android.graphics.Color.GREEN)
            } else {
                tv_monto_movimiento.text = "- $" + transaccion.amount.toString()
                tv_monto_movimiento.setTextColor(android.graphics.Color.RED)
            }
        }


    }

    override fun getItemCount(): Int = transacciones.size
}