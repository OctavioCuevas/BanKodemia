package com.honeykoders.bankodemia.ui.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentCreateAccountBinding
import com.honeykoders.bankodemia.ui.model.LoginModel
import com.honeykoders.bankodemia.ui.model.SearchUsersModel
import com.honeykoders.bankodemia.ui.viewmodel.SearchUsersViewModel
import com.honeykoders.bankodemia.ui.viewmodel.UserLoginModel
import kotlinx.android.synthetic.main.fragment_create_account.*
import java.io.IOException

class CreateAccount : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private val utils: Utils = Utils()
    val viewModel: UserLoginModel by viewModels()
    val viewModelSearchUsers: SearchUsersViewModel by viewModels()
    lateinit var token:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { viewModel.onCreate(context = it) }
        context?.let { viewModelSearchUsers.onCreate(context = it) }
        observersLogin()
        searchUserLogin()
        observersSearchUsers()

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
                    searchUserByMail(tiet_maillogin.text.toString())
                }else{
                    context?.let { it -> utils.showMessage(it,R.string.invalidMail) }
                }
            }else{
                context?.let { it -> utils.showMessage(it,getString(R.string.requiredField)) }
            }
        }
        return root
    }

    private fun observersSearchUsers() {
        viewModelSearchUsers.searchUsersResponse.observe(viewLifecycleOwner){ user->
            validateUser(user)
            /*if(user.data.users.size == 0){
                findNavController().navigate(R.id.customerDataFragment)
            }else{
                context?.let { utils.showMessage(it,R.string.mailIsAlreadyUsed) }
            }*/

        }

        viewModelSearchUsers.error.observe(viewLifecycleOwner){ user->
            context?.let { utils.showMessage(it,R.string.mailIsAlreadyUsed) }
        }

    }

    private fun validateUser(user: SearchUsersModel?) {
        val size = user?.data?.users?.size

        Log.e("UserSize:", size.toString())
        if (size != null){
            if(size == 0) {
                findNavController().navigate(R.id.customerDataFragment)
            }else{
                val email = user?.data?.users?.get(0)?.email
                if (email.equals(binding.tietMaillogin.text.toString())){
                    context?.let { utils.showMessage(it,R.string.mailIsAlreadyUsed) }
                }else{
                    findNavController().navigate(R.id.customerDataFragment)
                }
            }
        }
    }

    private fun searchUserByMail(email:String){
        viewModelSearchUsers.searchUser(email)
    }

    private fun searchUserLogin(){
        try {
            val login = getLoginInfo()
            sendData(login)
        }catch (e: IOException){
            Log.e("Error",e.toString())
        }
    }

    private fun sendData(login: LoginModel) {
        viewModel.loginUser(login)
    }

    private fun getLoginInfo(): LoginModel{
        val userLogin = LoginModel(
            "koder@kodemia.mx",
            "BondJames007"
        )

        return userLogin
    }

    private fun observersLogin() {
        viewModel.loginResponse.observe(viewLifecycleOwner){ loginUser ->
            Log.e("Token",loginUser.token)
            context?.let { it1 -> utils.initSharedPreferences(it1) }
            utils.updateSharedPreferences("string","token", loginUser.token!!,false,0,0.0f)
            token = utils.getSharedPreferencesByName("token").toString()


        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessage", error)
            when(error){
                "Unauthorized" -> context?.let { utils.showMessage(it,R.string.invalidCredentials) }
                "Invalid credentials" -> context?.let { utils.showMessage(it,R.string.invalidCredentials) }
                else -> null
            }
        }
    }

}