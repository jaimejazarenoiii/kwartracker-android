package com.kwartracker.android.wallet.model

data class WalletTransactions(
    var id: Int,
    var date: String,
    var amount: Int,
    var type: String,
    var transName: String,
    var transType: String
)
