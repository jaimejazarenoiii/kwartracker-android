package com.kwartracker.android.wallet.model

class WalletTransactions(
    var id: Int,
    var date: String,
    var amount: Int,
    var type: String,
    var trans_name: String,
    var trans_type: String
) {

    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) {
            return false
        }
        other as WalletTransactions
        if (id != other.id) {
            return false
        }
        if (date != other.date) {
            return false
        }
        if (amount != other.amount) {
            return false
        }
        if (type != other.type) {
            return false
        }
        if (trans_name != other.trans_name) {
            return false
        }
        if (trans_type != other.trans_type) {
            return false
        }
        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + date.hashCode()
        result = 31 * result + amount
        result = 31 * result + type.hashCode()
        result = 31 * result + trans_name.hashCode()
        result = 31 * result + trans_type.hashCode()
        return result
    }
}
