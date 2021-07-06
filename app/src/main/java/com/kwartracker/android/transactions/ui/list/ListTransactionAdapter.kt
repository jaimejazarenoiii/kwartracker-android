package com.kwartracker.android.transactions.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ItemTransactionBinding
import com.kwartracker.android.transactions.model.TransactionModel

class ListTransactionAdapter(
    var transactions: ArrayList<TransactionModel>
) :
    RecyclerView.Adapter<ListTransactionAdapter.TransactionViewHolder>() {

    fun updateTransactions(newTransactions: List<TransactionModel>) {
        transactions.clear()
        transactions.addAll(newTransactions)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = TransactionViewHolder(
        ItemTransactionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
    )

    override fun getItemCount(): Int {
        return if (transactions.size > 10) {
            10
        } else {
            transactions.size
        }
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(transactions[position])
    }

    class TransactionViewHolder(itemsTransactionBinding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(itemsTransactionBinding.root) {
        private val tvTransactionType = itemsTransactionBinding.tvTransactionType
        private val ivDetails = itemsTransactionBinding.ivDetails

        fun bind(model: TransactionModel) {
            tvTransactionType.text = model.type

            ivDetails.setOnClickListener {
                itemView.findNavController().navigate(
                    R.id.action_list_transaction_fragment_to_details_transaction_fragment
                )
            }
        }
    }
}
