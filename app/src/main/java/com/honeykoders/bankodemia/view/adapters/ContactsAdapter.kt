package com.honeykoders.bankodemia.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.model.contacts.Contacts
import com.honeykoders.bankodemia.view.holders.ContactsHolder

class ContactsAdapter(private val context: Context, private val listHours: List<Contacts>) :
    RecyclerView.Adapter<ContactsHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) {
      //  val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_send_money.rv_,parent,false)
       // return ContactsHolder(view)
        //return ContactsHolder(this)
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {

    }

    override fun getItemCount(): Int = listHours.size

    }