package com.honeykoders.bankodemia.view.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R

class ContactsHolder(view: View) : RecyclerView.ViewHolder(view){
    val tv_contact_name: TextView = view.findViewById(R.id.tv_contact_name)
    val tv_contact_account: TextView = view.findViewById(R.id.tv_contact_account)
    val contactsCardView: CardView = view.findViewById(R.id.contactCardView)
}