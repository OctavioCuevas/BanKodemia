package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentSeleccionarDocumentoIdentidadBinding
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
import java.lang.Exception

class SeleccionarDocumentoIdentidad : Fragment() {

    private var _binding: FragmentSeleccionarDocumentoIdentidadBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeleccionarDocumentoIdentidadBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnINE.setOnClickListener {
            mandarDocumentoIdentidad("INE")
        }
        binding.btnDocMig.setOnClickListener {
            mandarDocumentoIdentidad("DOCUMENTO MIGRATORIO")
        }
        binding.btnPasaporte.setOnClickListener {
            mandarDocumentoIdentidad("PASAPORTE")
        }
        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.verificarDocumentoIdentidad)
        }
        return root
    }

    private fun mandarDocumentoIdentidad(docIdent: String) {
        try {
            val bundle = bundleOf("docIdent" to docIdent)
            view?.findNavController()?.navigate(R.id.subirDocumentoIdentidad, bundle)
        }catch (e: Exception){

        }

    }
}
