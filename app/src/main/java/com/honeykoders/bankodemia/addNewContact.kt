package com.honeykoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding


class addNewContact : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnClabe.setOnClickListener {
            binding.tvAddContAcNumb.setText(R.string.tarjetatxttv)
            //funcion para validar 16 digitos de tarjeta
            validarDigitosT()
        }
    }

    private fun validarDigitosT() {
        val caracteres:Int = binding.tietAddContAcNumb.text!!.length
        if(caracteres<18){
            binding.tilAddContAcNumb.setError(getString(R.string.errorlimitnumclabe))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


}