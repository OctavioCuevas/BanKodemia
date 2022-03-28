package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentPasswordBinding
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
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
    ): View? {
        _binding = FragmentPasswordBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnCrearCuenta.setOnClickListener {
            observers()
            singUp()
        }
        return root
    }
    private fun singUp(){
        /*imagen:"R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" */
        //Log.e("DocIdent",docIdent.toString())
        try {
            val singUp =
                SingUpModel(
                    "jesusPrueba@email.com",
                    "Jesus",
                    "Prueba",
                    "2013-04-14T00:40:37.437Z",
                    "hola1234",
                    "+524491234589",
                    "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
                    "INE",
                    //"INE",
                    "Ingeniero"
                )
            if (singUp != null) {
                mandarDatos(singUp)
            }
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
            //shared.saveToken(it.access_token)
            //shared.saveSession(login)
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
    }


}