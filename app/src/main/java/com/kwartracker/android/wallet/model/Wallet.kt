package com.kwartracker.android.wallet.model

data class Wallet(
    var id: Int? = null,
    var name: String,
    var balance: Int,
    var type: String
)
