package com.honeykoders.bankodemia.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.honeykoders.bankodemia.HomeActivity
import com.honeykoders.bankodemia.databinding.ActivityHomeBinding
import com.honeykoders.bankodemia.databinding.FragmentLoginBinding


class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnIniciarSesion.setOnClickListener {
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
        }
        return root
    }


}