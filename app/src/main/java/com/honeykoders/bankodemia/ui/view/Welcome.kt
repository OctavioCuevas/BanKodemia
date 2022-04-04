package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentLoginBinding
import com.honeykoders.bankodemia.databinding.FragmentWelcomeBinding


class Welcome : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(R.id.login)
        }
        binding.btnCrearCuenta.setOnClickListener {
            findNavController().navigate(R.id.createAccount)
        }
        return root
    }


}