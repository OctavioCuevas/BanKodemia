package com.honeykoders.bankodemia.ui.view.holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R

class ContactsHolder(view: View) : RecyclerView.ViewHolder(view){
    val tv_contact_name: TextView = view.findViewById(R.id.tv_contact_name)
    val tv_contact_account: TextView = view.findViewById(R.id.tv_contact_account)
    val cv_user_contact: CardView = view.findViewById(R.id.cv_user_contact)
}