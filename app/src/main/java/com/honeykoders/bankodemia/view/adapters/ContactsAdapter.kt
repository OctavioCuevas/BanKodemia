package com.honeykoders.bankodemia.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
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
        val utils = Utils()
        val contacts = listContacts.get(position)
        with(holder){
            tv_contact_name.text = contacts.shortName
            tv_contact_account.text = contacts.shortName
            contactsCardView.setOnClickListener {
                context?.let { it1 -> utils.initSharedPreferences(it1) }
                utils.updateSharedPreferences("string","contactId",
                    contacts.user?.Id.toString() ,false,0,0.0f)
                Log.e("ContactID",contacts.user?.Id.toString())
                utils.updateSharedPreferences("string","contactName",contacts.shortName.toString() ,false,0,0.0f)
                it.findNavController().navigate(R.id.transferencia)
            }
        }
    }

    override fun getItemCount(): Int = listContacts.size

}