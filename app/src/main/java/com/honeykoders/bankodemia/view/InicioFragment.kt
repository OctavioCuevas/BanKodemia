package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentInicioBinding
import com.honeykoders.bankodemia.viewmodel.GetUserProfileViewModel

class InicioFragment : Fragment() {
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    val viewModel: GetUserProfileViewModel by viewModels()
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { viewModel.onCreate(context = it) }
        observers()
        viewModel.getUserProfile()
        binding.btnEnviar.setOnClickListener {
            findNavController().navigate(R.id.sendMoney)
        }

        return root
    }

    private fun observers() {

        viewModel.getUserResponse.observe(viewLifecycleOwner){userProfile ->
            val balance = userProfile.data.balance.toString()
            val id = userProfile.data.user._id
            context?.let { it1 -> utils.initSharedPreferences(it1) }
            utils.updateSharedPreferences("string","userId",id,false,0,0.0f)
            Log.d("Success profile Get", userProfile.data.user._id)
            binding.tvCantidadDisponible.setText("$$balance.00")
        }

        viewModel.error.observe(viewLifecycleOwner){error ->
            Log.d("ErrorMessageGet", error.toString())
        }

    }

}