package com.honeykoders.bankodemia.ui.view

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
import com.honeykoders.bankodemia.ui.viewmodel.GetUserProfileViewModel
import com.honeykoders.bankodemia.ui.model.MakeTransactionDeposit
import com.honeykoders.bankodemia.ui.model.Transactions
import com.honeykoders.bankodemia.view.adapters.TransactionsAdapter
import com.honeykoders.bankodemia.viewmodel.MakeDepositViewModel
import java.text.SimpleDateFormat
import java.util.*

class InicioFragment : Fragment() {

    //binding
    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    //viewModel
    val viewModel: GetUserProfileViewModel by viewModels()
    val viewModelDeposit: MakeDepositViewModel by viewModels()
    val utils: Utils = Utils()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initComponents()
        observers()
        viewModel.getUserProfile()

        binding.btnEnviar.setOnClickListener {
            findNavController().navigate(R.id.sendMoney)
        }
        binding.btnRecibir.setOnClickListener {
            makeDeposit()
        }
        return root
    }

    private fun initComponents() {
        context?.let { viewModel.onCreate(context = it) }
        context?.let { viewModelDeposit.onCreate(context = it) }
        setCurrentDate()
    }

    fun setCurrentDate(){
        val sdf = SimpleDateFormat("dd MMM yyyy")
        val currentDate = sdf.format(Date())
        binding.tvDate.text = currentDate.toString()
    }

    private fun makeDeposit() {
        viewModelDeposit.makeTransactionDeposit(getDepositInformation())
    }

    private fun getDepositInformation(): MakeTransactionDeposit {
        val deposit = MakeTransactionDeposit(
            50,
            "DEPOSIT",
            "Depósito Bancario"
        )
        return deposit
    }

    private fun observers() {

        viewModel.getUserResponse.observe(viewLifecycleOwner){ userProfile ->
            Log.d("Success profile Get", userProfile.data.user._id)
            setCurrentBalance(userProfile.data.balance.toString())
            saveUserId(userProfile.data.user._id)
            setRecyclerTransactions(userProfile.data.transactions, binding.recyclerViewHome)
            binding.progressBar.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessageGet", error.toString())
        }

        viewModelDeposit.makeDepositResponse.observe(viewLifecycleOwner){ deposit ->
            Log.d("Deposit", deposit.toString())
            context?.let { utils.showMessage(it,"Se ha realizado un abono por: "+deposit.data.transaction.amount) }
        }
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