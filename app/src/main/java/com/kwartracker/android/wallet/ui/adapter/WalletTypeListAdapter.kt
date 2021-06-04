package com.kwartracker.android.wallet.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.databinding.ItemWalletTypeBinding
import com.kwartracker.android.wallet.model.WalletType

class WalletTypeListAdapter(private val walletTypes: List<WalletType>) : BaseAdapter() {

    var onWalletTypeSelected: ((WalletType) -> Unit)? = null

    override fun getCount(): Int {
        return walletTypes.size
    }

    override fun getItem(position: Int): Any {
        return walletTypes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val walletType = walletTypes[position]
        val viewHolder = WalletTypeViewHolder.from(parent)
        viewHolder.bind(walletType)
        viewHolder.binding.root.setOnClickListener {
            onWalletTypeSelected?.invoke(walletType)
        }
        return viewHolder.binding.root
    }

    class WalletTypeViewHolder(val binding: ItemWalletTypeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WalletType) {
            binding.apply {
                tvWalletType.text = item.title
            }
        }

        companion object {
            fun from(parent: ViewGroup?): WalletTypeViewHolder {
                val layoutInflater = LayoutInflater.from(parent?.context)
                val binding = ItemWalletTypeBinding.inflate(layoutInflater, parent, false)
                return WalletTypeViewHolder(binding)
            }
        }
    }
}
