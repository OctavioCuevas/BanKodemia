package com.honeykoders.bankodemia.use_cases

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding
import com.honeykoders.bankodemia.exceptions.EmptyTokenException
import com.honeykoders.bankodemia.ui.model.contacts.Contacts
import com.honeykoders.bankodemia.ui.model.contacts.ContactsMain
import com.honeykoders.bankodemia.ui.view.adapters.ContactsAdapter
import com.honeykoders.bankodemia.ui.viewmodel.SendMoneyVM
import kotlinx.coroutines.launch

class GetUserContacts() {
    val utils = Utils()

    var token: String = ""
        set(value) {
            field = value.ifEmpty { throw EmptyTokenException() }
        }
    lateinit var viewModel: SendMoneyVM
    lateinit var binding: FragmentSendMoneyBinding
    lateinit var lifecycleScope: LifecycleCoroutineScope
    lateinit var owner: LifecycleOwner
    lateinit var context: Context

    fun getContacts() {
        this.getContactsData()
        this.observer()
    }

    private fun getContactsData() {
        this.viewModel.getUserContacts()
    }

    private fun observer() {
        viewModel.contactsResponse.observe(owner) { contactsEntity: ContactsMain ->
            lifecycleScope.launch {
                contactsEntity.apply {
                    showContacts(contactsEntity)
                    binding.progressBarSendMoney.visibility = View.GONE
                }
            }
        }
    }

    private fun showContacts(contactsEntity: ContactsMain) {
        setRecycler(contactsEntity.data.contacts, binding.rvListOfContacts)
    }

    private fun setRecycler(contacts: List<Contacts>, recyclerView: RecyclerView) {
        Log.e("size","${contacts.size}")
        val contactsAdapter = ContactsAdapter(context, contacts)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = contactsAdapter
        }
    }

}