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
import com.honeykoders.bankodemia.model.AddNewContactModel
import com.honeykoders.bankodemia.model.SearchUsersModel
import com.honeykoders.bankodemia.viewmodel.AddContactViewModel
import com.honeykoders.bankodemia.viewmodel.SearchUsersViewModel
import kotlinx.android.synthetic.main.fragment_create_account.*
import java.io.IOException


class AddNewContact : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!
    var accountType: String = "CLABE"
    val viewModel: AddContactViewModel by viewModels()
    val viewModelSearchUsers: SearchUsersViewModel by viewModels()
    val utils: Utils = Utils()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initcomponents()
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
                searchUserByMail(binding.tietAddContMail.text.toString())
                //addNewContact()
            }
        }

        return root
    }

    private fun initcomponents() {
        context?.let { viewModel.onCreate(context = it) }
        context?.let { viewModelSearchUsers.onCreate(context = it) }
    }

    private fun observers() {
        viewModel.addNewContactResponse.observe(viewLifecycleOwner){newContact->
            Log.d("Success newContact", newContact.success)
            findNavController().navigate(R.id.endAddContact)
        }

        viewModel.error.observe(viewLifecycleOwner){error ->
            Log.d("ErrorMessageGet", error.toString())
        }

        viewModelSearchUsers.searchUsersResponse.observe(viewLifecycleOwner){ user->
            Log.e("User:", user.toString())
            validateUser(user)
            /*val size = user.data.users.size
            Log.e("User:", size.toString())*/
            //saveUserId(user.data.users[0]._id)
            //addNewContact
            //findNavController().navigate(R.id.customerDataFragment)
        }

        viewModelSearchUsers.error.observe(viewLifecycleOwner){ user->
           // context?.let { utils.showMessage(it,R.string.mailIsAlreadyUsed) }
        }
    }

    private fun validateUser(user: SearchUsersModel?) {
        val size = user?.data?.users?.size

        Log.e("UserSize:", size.toString())
        if (size != null){
            if(size > 0){
                val email = user?.data?.users?.get(0)?.email
                if (email.equals(binding.tietAddContMail.text.toString())){
                    saveUserId(user.data.users[0]._id)
                }else{
                    context?.let { utils.showMessage(it,"El correo debe coincidir con el usuario registrado, verifique que sea correcto") }
                }
            }else{
                context?.let { utils.showMessage(it,"El correo debe coincidir con el usuario registrado, verifique que sea correcto") }
            }
        }

       /* if (size != null) {
            if (!size.equals(1)){
                context?.let { utils.showMessage(it,"El correo debe coincidir con el usuario registrado, verifique que sea correcto") }
            }else{
                if (email != null) {
                    if (email.equals(binding.tietAddContMail.text.toString())){
                        saveUserId(user.data.users[0]._id)
                    }else{
                        context?.let { utils.showMessage(it,"El correo debe coincidir con el usuario registrado, verifique que sea correcto") }
                    }
                }else{
                    context?.let { utils.showMessage(it,"El correo debe coincidir con el usuario registrado, verifique que sea correcto") }
                }
            }
        }*/
    }

    private fun saveUserId(id: String) {
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        val userLogedId = utils.getSharedPreferencesByName("userId").toString()
        Log.e("userId",id)
        Log.e("UserLogged",userLogedId)
        if (id.equals(userLogedId)){
            context?.let { utils.showMessage(it,"El correo NO debe ser el mismo con el que se inició sesión, intente con uno diferente") }
        }else{
            utils.updateSharedPreferences("string","userIdContact",id,false,0,0.0f)
            addNewContact()
            //Log.e("UserId",id)
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

    private fun searchUserByMail(email: String) {
        viewModelSearchUsers.searchUser(email)
    }

    private fun getContactData(): AddNewContactModel {

        context?.let { it1 -> utils.initSharedPreferences(it1) }
        val userName = binding.tietAddContName.text.toString()
        val id =  utils.getSharedPreferencesByName("userIdContact").toString()

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