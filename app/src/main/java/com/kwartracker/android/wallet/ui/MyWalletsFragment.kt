package com.kwartracker.android.wallet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentWalletsBinding
import com.kwartracker.android.utils.onPageChange
import com.kwartracker.android.wallet.model.Wallet
import com.kwartracker.android.wallet.model.WalletTransactions

class MyWalletsFragment : Fragment() {

    private lateinit var binding: FragmentWalletsBinding
    private lateinit var walletsAdapter: MyWalletViewPagerAdapter
    private val walletTransactionsAdapter = WalletTransactionsAdapter()

    private val transaction = listOf(
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 1,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 2,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        ),
        WalletTransactions(
            id = 3,
            type = "Jufiel",
            amount = 500,
            transType = "Utang",
            transName = "Bayad ni Marites",
            date = ""
        )
    )

    private val data = listOf(
        Wallet(
            id = 1,
            name = "Jufiel",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 2,
            name = "Danace",
            balance = 500,
            type = "Budget"

        ),
        Wallet(
            id = 3,
            name = "Marvin",
            balance = 500,
            type = "Travel"
        ),
        Wallet(
            id = 4,
            name = "Jaime",
            balance = 500,
            type = "Alak"
        ),
        Wallet(
            id = 5,
            name = "Harry",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 5,
            name = "Tyrone",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 5,
            name = "Ellie",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 5,
            name = "Jacob",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 5,
            name = "Meng",
            balance = 500,
            type = "Savings"
        ),
        Wallet(
            id = 5,
            name = "Rexen",
            balance = 500,
            type = "Savings"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallets, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(context, GridLayoutManager.VERTICAL, false)
        walletsAdapter = MyWalletViewPagerAdapter(requireContext())

        binding.walletLayout.recylerViewTransactions.layoutManager = linearLayoutManager
        binding.walletLayout.recylerViewTransactions.adapter = walletTransactionsAdapter
        binding.walletLayout.viewPager.adapter = walletsAdapter
        binding.walletLayout.viewPager.setPadding(100, 0, 100, 0)
        walletsAdapter.observeValue(data)
        binding.walletLayout.viewPager.onPageChange(
            binding.walletLayout.sliderDots,
            walletsAdapter.count
        ) {}

        walletTransactionsAdapter.setData(transaction)
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_walletsFragment_to_addWalletFragment)
        }
    }
}
