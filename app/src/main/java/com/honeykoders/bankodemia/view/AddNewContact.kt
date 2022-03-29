package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding
import com.honeykoders.bankodemia.databinding.FragmentSendMoneyBinding


class addNewContact : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!
    var accountType: String = "CLABE"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btnClabe.setOnClickListener {
            binding.tvAddContAcNumb.setText(R.string.tarjetatxttv)
            changeDesign(binding.btnClabe,binding.btnTarjeta)
            binding.tvAddContAcNumb.setText("18 dígitos")
            accountType ="CLABE"
        }

        binding.btnTarjeta.setOnClickListener {
            binding.tvAddContAcNumb.setText(R.string.tarjetatxttv)
            changeDesign(binding.btnTarjeta,binding.btnClabe)
            binding.tvAddContAcNumb.setText("Nº de tarjeta")
            accountType = "Tarjeta"
        }

        binding.btnBacktomain.setOnClickListener {
            findNavController().navigate(R.id.sendMoney)
        }
        binding.btnAddNewContact.setOnClickListener {
            validarDigitosT()
            //Agregar nuevo contacto
            //findNavController().navigate(R.id.endAddContact)
        }

        return root
    }


    private fun changeDesign(buttonSelected: Button, buttonUnselected: Button) {
        buttonSelected.setTextColor(resources.getColor(R.color.teal_200))
        buttonUnselected.setTextColor(resources.getColor(R.color.black2))
    }


    private fun validarDigitosT() {
        //18 digits
        Log.e("AccountType:",accountType)
        val characters:Int = binding.tietAddContAcNumb.text!!.length
        Log.e("Characters:",characters.toString())
        when(accountType){
            "CLABE" -> if(!(characters == 18)){
                           binding.tilAddContAcNumb.setError(getString(R.string.errorlimitnumclabe))
                       }else{
                           binding.tilAddContAcNumb.setError("")
                       }
            "Tarjeta"-> if(!(characters == 16)){
                         binding.tilAddContAcNumb.setError(getString(R.string.errorlimitnumtarjeta))
                       }else{
                        binding.tilAddContAcNumb.setError("")
                       }
            else -> null
        }

    }

}