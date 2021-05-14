package com.kwartracker.android.transactions.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionAdd1Binding

class TransactionAddFragmentS1 : Fragment() {
    lateinit var binding: FragmentTransactionAdd1Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_add_1, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnNext
        changeFragment(TransactionAddFragmentS2())
    }

    private fun changeFragment(fragment: Fragment) {
        val manager: FragmentManager? = activity?.supportFragmentManager
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        val newFragment: Fragment = fragment

        transaction?.replace(R.id.fr_steps, newFragment)
        transaction?.commit()
    }
}
