package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding
import com.honeykoders.bankodemia.use_cases.GetUserContacts
import com.honeykoders.bankodemia.ui.viewmodel.SendMoneyVM

class SendMoney : Fragment() {
    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!
    private val sessionToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI2MjE4NmRmM2M0NjBiZDNkODZhNjc1MjEiLCJpYXQiOjE2NDg1MzA5OTgsImV4cCI6MTY0ODUzNDU5OH0.wQe3lixhzreihp9wUdYTHcmRm4dapr-Uh9puVDTqgik"

    val viewModel: SendMoneyVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return initComponents()
    }

    private fun initComponents() : View {
        val root: View = binding.root
        binding.progressBarSendMoney.visibility = View.VISIBLE
        viewModel.context = this.requireContext()

        viewModel.onCreateVM()

        val userContacts = GetUserContacts()
        userContacts.token = sessionToken
        userContacts.viewModel = viewModel
        userContacts.owner = this
        userContacts.lifecycleScope = lifecycleScope
        userContacts.binding = binding
        userContacts.context = this.requireContext()
        userContacts.getContacts()

        binding.btnBacktomain.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
            /*val intent = Intent(activity, ActivityHomeBinding::class.java)
            startActivity(intent)*/
        }

        binding.btnAddContact.setOnClickListener {
            findNavController().navigate(R.id.addNewContact)
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