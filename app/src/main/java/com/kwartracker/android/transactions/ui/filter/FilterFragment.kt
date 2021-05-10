package com.kwartracker.android.transactions.ui.filter

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.InsetDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.appcompat.view.menu.MenuBuilder
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentSignupBinding
import com.kwartracker.android.databinding.FragmentTransactionDetailsBinding
import com.kwartracker.android.databinding.FragmentTransactionsListFilterBinding
import com.kwartracker.android.transactions.ui.main.TransactionFragment

class FilterFragment : Fragment() {
    lateinit var binding: FragmentTransactionsListFilterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transactions_list_filter, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibCategory.setOnClickListener { v: View ->
            showMenu(v, R.menu.transaction_category)
        }

        binding.ibClose.setOnClickListener {
            val intent = Intent("message")
            intent.putExtra("func", "filter")
            intent.putExtra("state", "close")
            LocalBroadcastManager.getInstance(view.context).sendBroadcast(intent)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun showMenu(v: View, @MenuRes menuRes: Int) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(menuRes, popup.menu)
        if (popup.menu is MenuBuilder) {
            val menuBuilder = popup.menu as MenuBuilder
            menuBuilder.setOptionalIconsVisible(true)
            for (item in menuBuilder.visibleItems) {
                val iconMarginPx =
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 10F, resources.displayMetrics)
                        .toInt()
                if (item.icon != null) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                        item.icon = InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx,0)
                    } else {
                        item.icon =
                            object : InsetDrawable(item.icon, iconMarginPx, 0, iconMarginPx, 0) {
                                override fun getIntrinsicWidth(): Int {
                                    return intrinsicHeight + iconMarginPx + iconMarginPx
                                }
                            }
                    }
                }
            }
        }
        popup.show()
    }
}
