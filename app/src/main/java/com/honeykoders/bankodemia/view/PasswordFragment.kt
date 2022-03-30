package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentPasswordBinding
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.viewmodel.SingUpViewModel
import java.io.IOException


class PasswordFragment : Fragment() {
    private var _binding: FragmentPasswordBinding? = null
    private val binding get() = _binding!!
    val viewModel: SingUpViewModel by viewModels()
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root

        context?.let { viewModel.onCreate(context = it) }

        binding.btnCrearCuenta.setOnClickListener {
            if(utils.matchPassword(binding.tietPassword,
                    binding.tilPassword,
                    binding.tietConfirmPassword,
                    binding.tilConfirmPassword)){
                        val passVerify = utils.verifyPassword(binding.tietPassword.text.toString())
                        if (passVerify == "Ok"){
                            observers()
                            singUp()
                            utils.clearSharedPreferences()
                            //getCustomerData()
                            //Log.e("Todo listo", "para nuevo cliente")
                        }else{
                            Log.e("Error","Algo salio mal")
                        }
                }
        }
        return root
    }

    private fun getCustomerData():SingUpModel{
        saveData()
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        val email =  utils.getSharedPreferencesByName("email").toString()
        val name= utils.getSharedPreferencesByName("name").toString()
        val lastName=  utils.getSharedPreferencesByName("lastName").toString()
        val birthDate = utils.getSharedPreferencesByName("birthDate").toString()
        val phone = utils.getSharedPreferencesByName("phone").toString()
        val password = utils.getSharedPreferencesByName("password").toString()
        val identityImage = utils.getSharedPreferencesByName("identityImage").toString()
        val identityImageType = utils.getSharedPreferencesByName("identityImageType").toString()
        val occupation = utils.getSharedPreferencesByName("occupation").toString()

        val newCostumer = SingUpModel(
            email,
            name,
            lastName,
            birthDate,
            password,
            phone,
            identityImage,
            identityImageType,
            occupation
        )

       Log.e("valorPass",newCostumer.toString())
        return newCostumer
    }

    private fun saveData() {
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        utils.updateSharedPreferences("string","password", binding.tietPassword.text.toString(),false,0,0.0f)
    }


    private fun singUp(){
        try {
            val singUp = getCustomerData()
            mandarDatos(singUp)
        }catch (e: IOException){
            Log.e("Error",e.toString())
        }

    }

    private fun mandarDatos(singUp: SingUpModel) {
        viewModel.singUp(singUp)
    }

    private fun observers() {
        viewModel.singUpResponse.observe(viewLifecycleOwner){ singUp ->
            Log.d("SingUp",singUp.success.toString())
            findNavController().navigate(R.id.ok_infoFragment)
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessage", error)
            when(error){
                "User phone already exists" -> context?.let { utils.showMessage(it,getString(R.string.userphonealreadyexists)) }
                "User already exists" -> context?.let { utils.showMessage(it,getString(R.string.useralreadyexists)) }
                else -> null
            }

        }
        viewModel.badRequest.observe(viewLifecycleOwner){ badRequest ->
            if (badRequest){
                Log.e("bad",badRequest.toString())
            }
        }
        viewModel.loading.observe(viewLifecycleOwner){ loading ->
            Log.e("Pase por aqui",loading.toString())
            if (loading){
                binding.contenedorPrincipal.visibility = View.GONE
                binding.contenedorCarga.visibility = View.VISIBLE
            }else{
                binding.contenedorPrincipal.visibility = View.VISIBLE
                binding.contenedorCarga.visibility = View.GONE

            }
        }
    }
}