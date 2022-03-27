package com.honeykoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding

class PhoneFragment : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    private fun initComponents() {
        setFragmentResultListener("requestKey") { key, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val result = bundle.get("bundleKey")
            // Do something with the result...
        }
    }
}