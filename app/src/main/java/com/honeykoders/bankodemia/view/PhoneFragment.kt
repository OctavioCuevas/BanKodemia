package com.honeykoders.bankodemia.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hbb20.CountryCodePicker
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentPhoneBinding
import kotlinx.android.synthetic.main.fragment_create_account.*
import kotlinx.android.synthetic.main.fragment_phone.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneFragment : Fragment() {
    private var _binding: FragmentPhoneBinding? = null
    private val binding get() = _binding!!
    private val utils: Utils = Utils()
    private var ccp: CountryCodePicker?=null
    private var countryCode:String?=null
    private var phoneNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhoneBinding.inflate(inflater, container, false)
        val root: View = binding.root

        countryCode = ccp?.defaultCountryCode

        binding.tietNumber.addTextChangedListener ( object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editText: Editable?) {
                utils.fieldValidation("phone",binding.tietNumber,binding.tilNumber,"Teléfono invalido")
            }
        })

        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.customerDataFragment)
        }

        binding.countryCodePicker.setOnCountryChangeListener {
            countryCode = ccp!!.selectedCountryCode
        }

        binding.btnContinuar.setOnClickListener {
            if (!utils.emptyField(tiet_number,til_number)){
                phoneNumber = countryCode + binding.tietNumber.text
                findNavController().navigate(R.id.verificarDocumentoIdentidad)
            }
        }

        return root
    }

}