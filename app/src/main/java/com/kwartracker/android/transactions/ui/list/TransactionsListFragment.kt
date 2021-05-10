package com.kwartracker.android.transactions.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionBinding
import com.kwartracker.android.databinding.FragmentTransactionsListBinding
import com.kwartracker.android.transactions.ui.main.TransactionViewModel


class TransactionsListFragment : Fragment() {
    private lateinit var binding: FragmentTransactionsListBinding
    private lateinit var mainBinding: FragmentTransactionBinding
    private val transactionViewModel: TransactionViewModel by viewModels()
    private var transactionsListAdapter = TransactionsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_transactions_list,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvTransaction = binding.rvTransactionList
        val fabBackToTop = binding.fabBackToTop
        val svTransactionList = binding.svTransactionList
        val llTransactionFilter = binding.llTransactionFilter

        svTransactionList.setOnScrollChangeListener { _, scrollX, scrollY, oldScrollX, oldScrollY ->
            if(10 < scrollY) {
                llTransactionFilter.background = AppCompatResources.getDrawable(view.context, R.drawable.border)
                fabBackToTop.visibility = View.VISIBLE
            }

            if (scrollY == 0) {
                llTransactionFilter.setBackgroundResource(0)
                fabBackToTop.visibility = View.INVISIBLE
            }
        }

        fabBackToTop.setOnClickListener {
            svTransactionList.scrollTo(0,0)
        }

        binding.ibFilter.setOnClickListener {
            val intent = Intent("message")
            intent.putExtra("func", "filter")
            LocalBroadcastManager.getInstance(view.context).sendBroadcast(intent)
        }

        transactionViewModel.fetchTransactions()
        rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionsListAdapter
        }

        transactionViewModel.transactions.observe(viewLifecycleOwner, Observer { transactions ->
            transactions?.let {
                rvTransaction.visibility = View.VISIBLE
                transactionsListAdapter.updateTransactions(it)
            }
        })

    }
}