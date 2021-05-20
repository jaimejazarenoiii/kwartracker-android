package com.kwartracker.android.wallet.ui.add

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.annotation.MenuRes
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentAddWalletBinding
import com.kwartracker.android.wallet.model.WalletType

class AddWalletFragment : Fragment() {

    private lateinit var binding: FragmentAddWalletBinding
    private lateinit var walletTypeListAdapter: WalletTypeListAdapter
    private var walletTypes = listOf<WalletType>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_wallet, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        walletTypes = listOf(
            WalletType(id = 1, title = getString(R.string.title_wallet)),
            WalletType(id = 2, title = getString(R.string.title_savings))
        )
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.etCurrency.setOnClickListener {
            showMenu(it, R.menu.currency_list)
        }
        binding.etWalletType.setOnClickListener {
            showMenus(it)
        }
    }

    private fun showMenu(view: View, @MenuRes menuRes: Int) {
        val ctw = ContextThemeWrapper(view.context, R.style.Transaction_Popup_Category)
        val popup = PopupMenu(ctw, view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            val data = view as TextView
            data.text = item.title.toString()
            return@setOnMenuItemClickListener true
        }
        popup.show()
    }

    private fun showMenus(view: View) {
        walletTypeListAdapter = WalletTypeListAdapter(walletTypes)
        val popup = ListPopupWindow(
            view.context,
            null,
            0,
            R.style.Transaction_Popup_Category
        )
        popup.anchorView = view
        popup.setAdapter(walletTypeListAdapter)
        walletTypeListAdapter.onWalletTypeSelected = { walletType ->
            val data = view as TextView
            data.text = walletType.title
            popup.dismiss()
        }
        popup.show()
    }
}
