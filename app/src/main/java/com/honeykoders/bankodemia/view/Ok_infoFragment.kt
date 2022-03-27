package com.honeykoders.bankodemia.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD:app/src/main/java/com/honeykoders/bankodemia/PhoneFragment.kt
import androidx.fragment.app.setFragmentResultListener
import com.honeykoders.bankodemia.databinding.FragmentAddNewContactBinding

class PhoneFragment : Fragment() {

    private var _binding: FragmentAddNewContactBinding? = null
    private val binding get() = _binding!!
=======
import com.honeykoders.bankodemia.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ok_infoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ok_infoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
>>>>>>> 4b371674d096a8ef49d54a5d9c9dbfdcaec406e2:app/src/main/java/com/honeykoders/bankodemia/view/Ok_infoFragment.kt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD:app/src/main/java/com/honeykoders/bankodemia/PhoneFragment.kt
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
=======
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ok_info, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ok_infoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ok_infoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
>>>>>>> 4b371674d096a8ef49d54a5d9c9dbfdcaec406e2:app/src/main/java/com/honeykoders/bankodemia/view/Ok_infoFragment.kt
    }
}