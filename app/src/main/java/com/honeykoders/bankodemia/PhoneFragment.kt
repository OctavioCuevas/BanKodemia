package com.honeykoders.bankodemia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding

class PhoneFragment : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddNewContactBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
}