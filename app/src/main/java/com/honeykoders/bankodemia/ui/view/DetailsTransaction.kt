package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentDetailsTransactionBinding
import com.honeykoders.bankodemia.databinding.FragmentInicioBinding
import com.honeykoders.bankodemia.databinding.FragmentPasswordBinding

class DetailsTransaction : Fragment() {
    private var _binding: FragmentDetailsTransactionBinding? = null
    private val binding get() = _binding!!

    val utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsTransactionBinding.inflate(inflater, container, false)
        val root: View = binding.root
        
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        val quantity = "\$${utils.getSharedPreferencesByName("amount")}.00"
        binding.tvCantidad.text = quantity

        val date_str = utils.getSharedPreferencesByName("date")
        Log.e("DebugHK", "La fecha string: $date_str")
        val date = date_str?.substring(0,10)
        val hour = date_str?.substring(11,19)
        Log.e("DebugHK", "Date: $date hour: $hour")
        binding.textViewFechaHome.text = utils.translateDate(utils.formatTimeStamp(utils.stringToDate("$date $hour"),1)!!                           )
        binding.tvConcepto.text = utils.getSharedPreferencesByName("concept")

        binding.btnDetalle.setOnClickListener {
            findNavController().navigate(R.id.inicioFragment)
        }
        return root

    }

}