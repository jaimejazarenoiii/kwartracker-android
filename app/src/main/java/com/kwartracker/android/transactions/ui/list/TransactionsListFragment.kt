package com.kwartracker.android.transactions.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionsListBinding
import com.kwartracker.android.main.ui.MainActivity
import com.kwartracker.android.transactions.ui.filter.TransactionsFilterFragment
import com.kwartracker.android.transactions.ui.main.TransactionsViewModel

class TransactionsListFragment : Fragment() {
    private lateinit var binding: FragmentTransactionsListBinding
    private val transactionsViewModel: TransactionsViewModel by viewModels()
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

    private fun actionMode() {
        val main = (activity as AppCompatActivity?)!!
        val callback = object : ActionMode.Callback {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                (activity as AppCompatActivity?)!!.menuInflater.inflate(
                    R.menu.transaction_action_bar,
                    menu
                )
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item?.itemId) {
                    R.id.menu_toolbar_add -> {
                        findNavController().navigate(R.id.transaction_add_fragment)
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }
        }
        val actionMode = main.startSupportActionMode(callback)
        actionMode?.title = getString(R.string.title_transactions)
        main.supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rvTransaction = binding.rvTransactionList
        val fabBackToTop = binding.fabBackToTop
        val llTransactionFilter = binding.llTransactionFilter
        val mLayoutManager = LinearLayoutManager(view.context)
        val ivLoader = binding.ivLoader
        rvTransaction.layoutManager = mLayoutManager
        actionMode()

        rvTransaction.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            var previousTotal = 0
            var loading = true
            val visibleThreshold = 10
            var firstVisibleItem = 0
            var visibleItemCount = 0
            var totalItemCount = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
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

                    if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                        fabBackToTop.visibility = View.INVISIBLE
                        transactionsViewModel.fetchTransactions()
                        loading = true
                    }
                }

                if (dy <= 0) {
                    llTransactionFilter.elevation = 0F
                    fabBackToTop.visibility = View.INVISIBLE
                }
            }
        })

        fabBackToTop.setOnClickListener {
            rvTransaction.smoothScrollToPosition(1)
        }

        binding.ibFilter.setOnClickListener {
            (activity as MainActivity).bottomSheetModal(TransactionsFilterFragment())
        }

        transactionsViewModel.fetchTransactions()
        rvTransaction.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = transactionsListAdapter
        }

        transactionsViewModel.transactions.observe(viewLifecycleOwner, { transactions ->
            transactions?.let {
                rvTransaction.visibility = View.VISIBLE
                transactionsListAdapter.updateTransactions(it)
            }
        })

        transactionsViewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            isLoading?.let {
                ivLoader.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    rvTransaction.visibility = View.GONE
                }
            }
        })
    }
}
