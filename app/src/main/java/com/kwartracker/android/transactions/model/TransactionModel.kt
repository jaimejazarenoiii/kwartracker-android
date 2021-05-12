package com.kwartracker.android.transactions.model

data class TransactionModel(
    val id: Int,
    val date: String,
    val amount: Int,
    val type: String,
    val transName: String,
    val transType: String
)
