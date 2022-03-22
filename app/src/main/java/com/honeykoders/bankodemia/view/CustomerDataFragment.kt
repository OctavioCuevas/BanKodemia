package com.honeykoders.bankodemia.view

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.honeykoders.bankodemia.R


class CustomerDataFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponents()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_customer_data, container, false)
    }

    private fun initComponents() {
        val spannable = SpannableStringBuilder("Esta información es necesaria para verificar tu identidad. Nunca la usaremos sin tu consentimiento")
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            59, // start
            200, // end
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )
        //val tvInfo: TextView = findViewById(R.id.tv_info)
        //tv_info.text = spannable
    }
}