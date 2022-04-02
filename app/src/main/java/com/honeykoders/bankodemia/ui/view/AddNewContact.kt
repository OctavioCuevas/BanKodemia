package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding
import com.honeykoders.bankodemia.ui.model.AddNewContactModel
import com.honeykoders.bankodemia.ui.viewmodel.AddContactViewModel
import java.io.IOException


class AddNewContact : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!
    var accountType: String = "CLABE"
    val viewModel: AddContactViewModel by viewModels()
    val utils: Utils = Utils()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { viewModel.onCreate(context = it) }
        observers()

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
            if (!utils.emptyField(binding.tietAddContAcNumb,binding.tilAddContAcNumb) &&
                !utils.emptyField(binding.tietAddContInsti,binding.tilAddContInsti) &&
                !utils.emptyField(binding.tietAddContName,binding.tilAddContName)&&
                !utils.emptyField(binding.tietAddContMail,binding.tilAddContMail) &&
                !validarDigitosT()){
                Log.e("Listo","Para agregar contactos")
                addNewContact()
            }

            //Agregar nuevo contacto
            //findNavController().navigate(R.id.endAddContact)
        }

        return root
    }

    private fun observers() {
        viewModel.addNewContactResponse.observe(viewLifecycleOwner){newContact->
            Log.d("Success newContact", newContact.success)
            findNavController().navigate(R.id.endAddContact)
        }

        viewModel.error.observe(viewLifecycleOwner){error ->
            Log.d("ErrorMessageGet", error.toString())
        }
    }

    private fun addNewContact(){
        try {
            val newContact = getContactData()
            sendData(newContact)
        }catch (e: IOException){
            Log.e("Error",e.toString())
        }

    }

    private fun getContactData(): AddNewContactModel {

        context?.let { it1 -> utils.initSharedPreferences(it1) }
        val userName = binding.tietAddContName.text.toString()
        val id =  utils.getSharedPreferencesByName("userId").toString()

        val newContact = AddNewContactModel(
            userName,
            id
        )

        return newContact
    }

    private fun sendData(newContact: AddNewContactModel) {
        viewModel.addNewContact(newContact)
    }


    private fun changeDesign(buttonSelected: Button, buttonUnselected: Button) {
        buttonSelected.setTextColor(resources.getColor(R.color.teal_200))
        buttonUnselected.setTextColor(resources.getColor(R.color.black2))
    }


    private fun validarDigitosT():Boolean {
        var error: Boolean = false
        Log.e("AccountType:",accountType)
        val characters:Int = binding.tietAddContAcNumb.text!!.length
        Log.e("Characters:",characters.toString())
        when(accountType){
            "CLABE" -> if(!(characters == 18)){
                           binding.tilAddContAcNumb.setError(getString(R.string.errorlimitnumclabe))
                            error = true
                       }else{
                           binding.tilAddContAcNumb.setError("")
                            error = false
                       }
            "Tarjeta"-> if(!(characters == 16)){
                         binding.tilAddContAcNumb.setError(getString(R.string.errorlimitnumtarjeta))
                            error = true
                       }else{
                        binding.tilAddContAcNumb.setError("")
                        error = false
                       }
            else -> null
        }
        return error
    }

}