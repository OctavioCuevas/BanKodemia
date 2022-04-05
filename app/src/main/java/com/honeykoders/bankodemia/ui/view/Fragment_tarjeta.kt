package com.honeykoders.bankodemia.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentTarjetaBinding

class Fragment_tarjeta : Fragment() {

    //binding
    private var _binding: FragmentTarjetaBinding? = null
    private val binding get() = _binding!!

    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTarjetaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.ivUser.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
            context?.let { utils.showMessage(it,"Se ha cerrado sesión con exito") }
            Log.e("Imagen","Click en imagen")
        }


        return root
    }


}