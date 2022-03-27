package com.honeykoders.bankodemia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentOkInfoBinding
import com.honeykoders.bankodemia.databinding.FragmentPasswordBinding

class ok_infoFragment : Fragment() {

    private var _binding: FragmentOkInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOkInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnBackToLogin.setOnClickListener {
            findNavController().navigate(R.id.welcome)
        }
        return root
    }


}