package com.kwartracker.android.transactions.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.transactions.model.TransactionModel

class TransactionsViewModel : ViewModel() {

    val transactions = MutableLiveData<List<TransactionModel>>()
    val loading = MutableLiveData<Boolean>()

    fun fetchTransactions() {
        loading.value = true
        val dummyData = generateDummyTransactions()

        transactions.value = dummyData
        loading.value = false
    }

    private fun generateDummyTransactions(): List<TransactionModel> {
        val transactions = arrayListOf<TransactionModel>()
        for (i in 1..20) {
            transactions.add(
                TransactionModel(
                    1, "dummyCapital $i",
                    (0..10).random(), "Type $i",
                    "Name $i",
                    "Type $i"
                )
            )
        }

        return transactions
    }
}
