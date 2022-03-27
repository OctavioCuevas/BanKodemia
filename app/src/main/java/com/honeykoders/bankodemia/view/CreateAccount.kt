package com.honeykoders.bankodemia.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentCreateAccountBinding
import kotlinx.android.synthetic.main.fragment_create_account.*

class CreateAccount : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.tietMaillogin.addTextChangedListener ( object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                utils.fieldValidation("email",binding.tietMaillogin,binding.tilMaillogin,"Correo invalido")
            }
        })


        binding.btnBacktomain.setOnClickListener {
            findNavController().navigate(R.id.welcome)
        }
        binding.btnContinuar.setOnClickListener {
            if (!utils.emptyField(tiet_maillogin,til_maillogin)){
                if(utils.fieldValidation("email",binding.tietMaillogin,binding.tilMaillogin,"Correo invalido")){
                    context?.let { it1 -> utils.initSharedPreferences(it1) }
                    utils.updateSharedPreferences("string","email",binding.tietMaillogin.text.toString(),false,0,0.0f)
                    findNavController().navigate(R.id.customerDataFragment)
                }else{
                    context?.let { it -> utils.showMessage(it,R.string.invalidMail) }
                }
            }else{
                context?.let { it -> utils.showMessage(it,R.string.requiredField) }
            }
        }
        return root
    }

}