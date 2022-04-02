package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
import com.honeykoders.bankodemia.databinding.FragmentVerificarDocumentoIdentidadBinding

class VerificarDocumentoIdentidad : Fragment() {

    private var _binding: FragmentVerificarDocumentoIdentidadBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerificarDocumentoIdentidadBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnEntendido.setOnClickListener {
            findNavController().navigate(R.id.seleccionarDocumentoIdentidad)
        }
        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.phoneFragment)
        }
        return root
    }


}