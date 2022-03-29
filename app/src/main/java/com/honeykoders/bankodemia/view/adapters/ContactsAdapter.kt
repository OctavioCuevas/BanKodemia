package com.honeykoders.bankodemia.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.model.contacts.Contacts
import com.honeykoders.bankodemia.view.holders.ContactsHolder
import java.text.SimpleDateFormat
import java.util.*

class ContactsAdapter(private val context: Context, private val listContacts: List<Contacts>) :
    RecyclerView.Adapter<ContactsHolder>()  {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) : ContactsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contacts,parent,false)
        return ContactsHolder(view)
        //return ContactsHolder(this)
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        val contacts = listContacts.get(position)
        with(holder){
            tv_contact_name.text = contacts.shortName
            tv_contact_account.text = contacts.shortName
        }
    }

    override fun getItemCount(): Int = listContacts.size

}