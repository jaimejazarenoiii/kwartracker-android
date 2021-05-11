package com.kwartracker.android.transactions.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.navigation.NavigationView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentTransactionBinding
import com.kwartracker.android.transactions.ui.add.TransactionAddFragment
import com.kwartracker.android.transactions.ui.details.TransactionDetailsFragment
import com.kwartracker.android.transactions.ui.filter.TransactionsFilterFragment
import com.kwartracker.android.transactions.ui.list.TransactionsListFragment

class TransactionsFragment : Fragment() {
    lateinit var binding: FragmentTransactionBinding
    private val transactionsViewModel: TransactionsViewModel by viewModels()
    var tbTitle: TextView? = null
    var navBottomSheetModal: NavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transaction, container, false)

        tbTitle = binding.tvToolbarTitle
        navBottomSheetModal = binding.nvFilter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.ivToolbarRight.setOnClickListener {
            bottomMainSheetModal(TransactionAddFragment())
        }

        binding.ivToolbarLeft.setOnClickListener {
            bottomMainSheetModal(TransactionsListFragment())
        }

        binding.tvToolbarTitle.text = "Transactions"

        bottomMainSheetModal(TransactionsListFragment())
        LocalBroadcastManager.getInstance(view.context).registerReceiver(
            mMessageReceiver,
            IntentFilter("message")
        )

        bottomMainSheetModal(TransactionsListFragment())
        changeFragment(TransactionsFilterFragment(), R.id.nav_fragment_transactions_modal)
        bottomSheetModal(null)
    }

    private var mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceive(context: Context, intent: Intent) {
            val func = intent.getStringExtra("func")

            if (func == "filter") {
                val state = intent.getStringExtra("state")
                if (state == "close") bottomSheetModal(null)
                else bottomSheetModal(TransactionsFilterFragment())
            } else if (func == "details") {
                binding.tvToolbarTitle.text = "Transaction"
                val transID = intent.getStringExtra("transID")
                bottomMainSheetModal(TransactionDetailsFragment())
            }
        }
    }

    private fun bottomSheetModal(fragment: Fragment?) {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.nvFilter)
        val scrim = binding.scrim

        scrim.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == 5) scrim.visibility = View.GONE
                else scrim.visibility = View.VISIBLE
            }
        })

        if (fragment != null) {
            scrim.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    changeFragment(fragment, R.id.nav_fragment_transactions_modal)
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }, 500
            )
        }
    }

    private fun bottomMainSheetModal(fragment: Fragment) {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.navBackdrop)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

        Handler(Looper.getMainLooper()).postDelayed(
            {
                changeFragment(fragment, R.id.nav_host_fragment_transactions)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }, 500
        )
    }

    private fun changeFragment(fragment: Fragment, navID: Int) {
        val manager: FragmentManager? = activity?.supportFragmentManager
        val transaction: FragmentTransaction? = manager?.beginTransaction()
        val newFragment: Fragment = fragment

        transaction?.replace(navID, newFragment)
        transaction?.commit()
    }
}
