package com.honeykoders.bankodemia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentTransaccionFinalizadaBinding
import com.honeykoders.bankodemia.databinding.FragmentTransferenciaBinding


class TransaccionFinalizada : Fragment() {
    private var _binding: FragmentTransaccionFinalizadaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransaccionFinalizadaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnVolver.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
        }
        return root
    }

}