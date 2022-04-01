package com.honeykoders.bankodemia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentDetailsTransactionBinding
import com.honeykoders.bankodemia.databinding.FragmentLoginBinding

class DetailsTransaction : Fragment() {

    private var _binding: FragmentDetailsTransactionBinding? = null
    private val binding get() = _binding!!
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsTransactionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        binding.textViewFechaHome.text = utils.getSharedPreferencesByName("date")
        binding.tvCantidad.text = "$" + utils.getSharedPreferencesByName("amount")
        binding.tvConcepto.text = utils.getSharedPreferencesByName("concept")
        binding.textViewConcepto2Home.text = utils.getSharedPreferencesByName("concept")
        binding.btnDetalle.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
        }
        return root
    }


}