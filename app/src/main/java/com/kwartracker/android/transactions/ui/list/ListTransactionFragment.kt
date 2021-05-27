package com.kwartracker.android.transactions.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentListTransactionBinding
import com.kwartracker.android.transactions.ui.main.TransactionViewModel

class ListTransactionFragment : Fragment() {
    private lateinit var binding: FragmentListTransactionBinding
    private val transactionViewModel: TransactionViewModel by viewModels()
    private var transactionsListAdapter = ListTransactionAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_transaction,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvTransaction = binding.rvTransactionList
        val fabBackToTop = binding.fabBackToTop
        val llTransactionFilter = binding.llTransactionFilter
        val mLayoutManager = LinearLayoutManager(view.context)
        val ivLoader = binding.ivLoader
        rvTransaction.layoutManager = mLayoutManager

        rvTransaction.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var previousTotal = 0
            var loading = true
            val visibleThreshold = 10
            var firstVisibleItem = 0
            var visibleItemCount = 0
            var totalItemCount = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                println(newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    llTransactionFilter.elevation = 20F
                    fabBackToTop.visibility = View.VISIBLE

                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition()

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false
                            previousTotal = totalItemCount
                        }
                    }

                    if (!loading && (totalItemCount - visibleItemCount) <=
                        (firstVisibleItem + visibleThreshold)
                    ) {
                        fabBackToTop.visibility = View.INVISIBLE
                        transactionViewModel.fetchTransactions()
                        loading = true
                    }
                }
                if (dy <= 0) {
                    llTransactionFilter.elevation = 0F
                    fabBackToTop.visibility = View.INVISIBLE
                }
                val xxx = recyclerView.scrollY
                println("$dx $dy $xxx ")
            }
        })

        fabBackToTop.setOnClickListener {
            rvTransaction.smoothScrollToPosition(1)
        }

        binding.ibFilter.setOnClickListener {
            findNavController().navigate(R.id.action_transaction_fragment_to_date_range_transaction_fragment)
        }

        transactionViewModel.fetchTransactions()
        rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionsListAdapter
        }

        transactionViewModel.transactions.observe(
            viewLifecycleOwner,
            { transactions ->
                transactions?.let {
                    rvTransaction.visibility = View.VISIBLE
                    transactionsListAdapter.updateTransactions(it)
                }
            }
        )

        transactionViewModel.loading.observe(
            viewLifecycleOwner,
            { isLoading ->
                isLoading?.let {
                    ivLoader.visibility = if (it) View.VISIBLE else View.GONE
                    if (it) {
                        rvTransaction.visibility = View.GONE
                    }
                }
            }
        )
    }
}
