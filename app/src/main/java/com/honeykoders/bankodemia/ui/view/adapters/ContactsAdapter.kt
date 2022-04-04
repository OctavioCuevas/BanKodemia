package com.honeykoders.bankodemia.ui.view.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.ui.model.contacts.Contacts
import com.honeykoders.bankodemia.ui.view.holders.ContactsHolder

class ContactsAdapter(private val context: Context,
                      private val listContacts: List<Contacts>) :
    RecyclerView.Adapter<ContactsHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactsHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_contacts, parent, false)
        return ContactsHolder(view)
    }

    override fun onBindViewHolder(holder: ContactsHolder, position: Int) {
        val utils = Utils()
        val contacts = listContacts.get(position)
        with(holder) {
            tv_contact_name.text = contacts.shortName
            tv_contact_account.text = utils.getRandomCard()
            cv_user_contact.setOnClickListener {
                holder.cv_user_contact.setBackgroundColor(Color.parseColor("#E5E5E5"))
                tv_contact_account.text = contacts.shortName
                context?.let { it1 -> utils.initSharedPreferences(it1) }
                utils.updateSharedPreferences(
                    "string",
                    "contactId",
                    contacts.Id.toString(),
                    false,
                    0,
                    0.0f
                )
                utils.updateSharedPreferences(
                    "string",
                    "contactName",
                    contacts.shortName.toString(),
                    false,
                    0,
                    0.0f
                )
                it.findNavController().navigate(R.id.transferencia)
            }
        }
    }

    override fun getItemCount(): Int = listContacts.size

}