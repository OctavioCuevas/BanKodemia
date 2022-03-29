package com.honeykoders.bankodemia.view.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R

class ContactsHolder(view: View) : RecyclerView.ViewHolder(view){
    val tv_contact_name: ImageView = view.findViewById(R.id.tv_contact_name)
    val tv_contact_account: TextView = view.findViewById(R.id.tv_contact_account)
}