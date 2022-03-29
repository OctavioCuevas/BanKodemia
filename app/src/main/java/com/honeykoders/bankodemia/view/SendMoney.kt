package com.honeykoders.bankodemia.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.honeykoders.bankodemia.databinding.ActivityHomeBinding
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding
import com.honeykoders.bankodemia.exceptions.EmptyTokenException
import com.honeykoders.bankodemia.use_cases.GetUserContacts
import com.honeykoders.bankodemia.viewmodel.SendMoneyVM
import java.lang.Exception

class SendMoney : Fragment() {
    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!

    val viewModel: SendMoneyVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return initComponents()
    }

    private fun initComponents() : View? {
        val root: View = binding.root

        val userContacts = GetUserContacts()

        viewModel.onCreateVM()

        userContacts.token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjE4NmRmM2M0NjBiZDNkODZhNjc1MjEiLCJpYXQiOjE2NDg0MTM1ODgsImV4cCI6MTY0ODQxNzE4OH0.niZXPi8nsArGMPGguERnVSvD9E1UNF4ZlID2jF-yJoM"

        try {
            userContacts.getContacts()
        } catch (ex: Exception) {
            when (ex) {
                is EmptyTokenException ->
                    showSnackbar(root, ex.message.toString())
            }
        }

        binding.btnBacktomain.setOnClickListener {
            val intent = Intent(activity, ActivityHomeBinding::class.java)
            startActivity(intent)
        }

        binding.btnAddContact.setOnClickListener {
            //findNavController().navigate(R.id.addNewContact)
        }
        return root
    }

    private fun showSnackbar(
        view: View,
        text: String,
        actionStrId: Int = 0,
        listener: View.OnClickListener? = null
    ) {
        val snackbar = Snackbar.make(
            view,
            text,
            BaseTransientBottomBar.LENGTH_INDEFINITE
        )
        if (actionStrId != 0 && listener != null) {
            snackbar.setAction(getString(actionStrId), listener)
        }
        snackbar.show()
    }

}