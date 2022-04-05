package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding
import com.honeykoders.bankodemia.ui.viewmodel.SendMoneyVM
import com.honeykoders.bankodemia.use_cases.GetUserContacts

class SendMoney : Fragment() {
    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!
    val viewModel: SendMoneyVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        return initComponents()
    }

    //Pressed return button - returns to home
    override fun onResume() {
        super.onResume()
        requireView().isFocusableInTouchMode = true
        requireView().requestFocus()
        requireView().setOnKeyListener { v, keyCode, event ->
            if (event.action === KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                findNavController().navigate(R.id.inicioFragment)
                true
            } else {
                false
            }
        }
    }

    private fun initComponents(): View {
        val root: View = binding.root
        binding.progressBarSendMoney.visibility = View.VISIBLE
        viewModel.context = this.requireContext()

        viewModel.onCreateVM()

        val userContacts = GetUserContacts()
        userContacts.viewModel = viewModel
        userContacts.owner = this
        userContacts.lifecycleScope = lifecycleScope
        userContacts.binding = binding
        userContacts.context = this.requireContext()
        userContacts.getContacts()

        binding.btnBacktomain.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
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