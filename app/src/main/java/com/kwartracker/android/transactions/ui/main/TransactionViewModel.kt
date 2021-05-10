package com.kwartracker.android.transactions.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kwartracker.android.transactions.ui.model.TransactionModel

class TransactionViewModel: ViewModel() {

    val transactions = MutableLiveData<List<TransactionModel>>()
    val transactionsLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()
    var backdrop = MutableLiveData<Boolean>()

    fun fetchTransactions() {
        loading.value = true
        val dummyData = generateDummyTransactions()

        transactions.value = dummyData
        transactionsLoadError.value = ""
        loading.value = false
    }

    fun backDropFun(state: Boolean) {
        backdrop.value = state
    }

    private fun generateDummyTransactions(): List<TransactionModel> {
        val transactions = arrayListOf<TransactionModel>()
        transactions.add(TransactionModel(1,  "dummyCapital1",""))
        transactions.add(TransactionModel(2,  "dummyCapital2",""))
        transactions.add(TransactionModel(3,  "dummyCapital3",""))
        transactions.add(TransactionModel(4,  "dummyCapital4",""))
        transactions.add(TransactionModel(5,  "dummyCapital5",""))
        transactions.add(TransactionModel(6,  "dummyCapital1",""))
        transactions.add(TransactionModel(7,  "dummyCapital2",""))
        transactions.add(TransactionModel(8,  "dummyCapital3",""))
        transactions.add(TransactionModel(9,  "dummyCapital4",""))
        transactions.add(TransactionModel(10,  "dummyCapital5",""))
        return transactions
    }

    private fun onError(message: String) {
        transactionsLoadError.value = message
        loading.value = false
    }

}