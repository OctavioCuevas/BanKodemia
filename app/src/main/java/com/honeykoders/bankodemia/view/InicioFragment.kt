package com.honeykoders.bankodemia.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.Utils
import com.honeykoders.bankodemia.databinding.FragmentInicioBinding
import com.honeykoders.bankodemia.model.Transactions
import com.honeykoders.bankodemia.view.adapters.TransactionsAdapter
import com.honeykoders.bankodemia.viewmodel.GetUserProfileViewModel


class InicioFragment : Fragment() {

    //binding
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    //viewModel
    val viewModel: GetUserProfileViewModel by viewModels()

    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root
        context?.let { viewModel.onCreate(context = it) }
        observers()
        viewModel.getUserProfile()
        binding.btnEnviar.setOnClickListener {
            findNavController().navigate(R.id.sendMoney)
        }

        return root
    }

    //Se inicia SharedPreferences

    private fun observers() {
        viewModel.getUserResponse.observe(viewLifecycleOwner){ userProfile ->
            Log.d("Success profile Get", userProfile.data.user._id)
            setCurrentBalance(userProfile.data.balance.toString())
            saveUserId(userProfile.data.user._id)
            setRecyclerTransactions(userProfile.data.transactions, binding.recyclerViewHome)
            //binding.progressBar.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessageGet", error.toString())
        }

       // viewModelDeposit.makeDepositR
    }


    private fun saveUserId(id: String) {
        context?.let { it1 -> utils.initSharedPreferences(it1) }
        utils.updateSharedPreferences("string","userId",id,false,
            0,0.0f)
    }

    private fun setCurrentBalance(balance: String) {
        binding.tvCantidadDisponible.setText("$$balance.00")
    }

    //Se mandan las transacciones para visualizar en pantalla Home
    private fun setRecyclerTransactions(transactions: List<Transactions>, recyclerView:
    RecyclerView) {
        Log.e("size","${transactions.size}")
        val transactionAdapter = TransactionsAdapter(context as Activity,
            transactions as MutableList<Transactions>
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                false)
            adapter = transactionAdapter
        }
    }


}