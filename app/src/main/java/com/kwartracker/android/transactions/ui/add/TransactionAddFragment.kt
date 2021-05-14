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
import com.kwartracker.android.databinding.FragmentTransactionDetailsBinding

class TransactionAddFragment : Fragment() {
    lateinit var binding: FragmentTransactionDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction_add, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        changeFragment(TransactionAddFragmentS1())
    }

    private fun changeFragment(fragment: Fragment) {
        val manager: FragmentManager? = activity?.supportFragmentManager
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        val newFragment: Fragment = fragment

        transaction?.replace(R.id.fr_steps, newFragment)
        transaction?.commit()
    }
}
