package com.kwartracker.android.wallet.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.databinding.ItemTransactionBinding
import com.kwartracker.android.wallet.model.WalletTransactions

class WalletTransactionsAdapter :
    RecyclerView.Adapter<WalletTransactionsAdapter.WalletTransactionViewHolder>() {

    var results: List<WalletTransactions> = listOf()

    fun setData(result: List<WalletTransactions>) {
        val oldItem = this.results
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(WalletTransactionDiffUtilCallback(oldItem, result))
        this.results = result
        diffResult.dispatchUpdatesTo(this)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletTransactionViewHolder {
        return WalletTransactionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: WalletTransactionViewHolder, position: Int) {
        val item = results[position]
        item.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = results.size

    class WalletTransactionViewHolder(val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: WalletTransactions) {
            binding.apply {
                tvAmount.text = item.amount.toString()
                tvTransactionName.text = item.trans_name
                tvTransactionType.text = item.trans_type
            }
        }

        companion object {
            fun from(parent: ViewGroup): WalletTransactionViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTransactionBinding.inflate(layoutInflater, parent, false)
                return WalletTransactionViewHolder(binding)
            }
        }
    }

    class WalletTransactionDiffUtilCallback(var oldShowResults: List<WalletTransactions>, var newShowResults: List<WalletTransactions>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldShowResults.size
        }

        override fun getNewListSize(): Int {
            return newShowResults.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldShowResults[oldItemPosition].id == newShowResults[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldShowResults[oldItemPosition] == newShowResults[newItemPosition]
        }

    }
}
