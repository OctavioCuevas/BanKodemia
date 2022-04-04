package com.honeykoders.bankodemia.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentLoginBinding
import com.honeykoders.bankodemia.model.LoginModel
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.viewmodel.GetUserProfileViewModel
import com.honeykoders.bankodemia.viewmodel.SingUpViewModel
import com.honeykoders.bankodemia.viewmodel.UserLoginModel
import java.io.IOException


class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    val viewModel: UserLoginModel by viewModels()
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { viewModel.onCreate(context = it) }
        observers()
        binding.btnIniciarSesion.setOnClickListener {
            if (!utils.emptyField(binding.tietMaillogin,binding.tilMaillogin) &&
                !utils.emptyField(binding.tietMaillogin,binding.tilMaillogin)){
                    binding.loginProgressBar.visibility = View.VISIBLE
                    login()
            }
        }
        binding.btnBacktomain.setOnClickListener {
                findNavController().navigate(R.id.welcome)
        }
        return root
    }

    private fun observers() {
        viewModel.loginResponse.observe(viewLifecycleOwner){ loginUser ->
            Log.e("Token",loginUser.token)
            context?.let { it1 -> utils.initSharedPreferences(it1) }
            utils.updateSharedPreferences("string","token", loginUser.token!!,false,0,0.0f)
            binding.loginProgressBar.visibility = View.GONE
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessage", error)
            binding.loginProgressBar.visibility = View.GONE
            when(error){
                "Unauthorized" -> context?.let { utils.showMessage(it,R.string.invalidCredentials) }
                "Invalid credentials" -> context?.let { utils.showMessage(it,R.string.invalidCredentials) }
                else -> null
            }
        }

    }


    private fun login(){
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

    private fun getLoginInfo(): LoginModel {

        val email = binding.tietMaillogin.text.toString().trim()
        val password = binding.tietPwdlogin.text.toString()

        return LoginModel(
            email,
            password
        )
    }


}