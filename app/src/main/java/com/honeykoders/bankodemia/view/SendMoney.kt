package com.honeykoders.bankodemia.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.ActivityHomeBinding
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding

class SendMoney : Fragment() {
    private var _binding: FragmentSendMoneyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSendMoneyBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

}