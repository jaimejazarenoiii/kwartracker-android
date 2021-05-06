package com.kwartracker.android.wallet.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ItemWalletBinding
import com.kwartracker.android.wallet.model.Wallet

class MyWalletViewPagerAdapter(val context: Context) : PagerAdapter() {

    var data: List<Wallet> = listOf()
    var contentBackground =
        listOf(R.drawable.wallet_bg_green, R.drawable.wallet_bg_blue, R.drawable.wallet_bg_red)
    var contentShadow = listOf(
        R.drawable.wallet_green_shadow,
        R.drawable.wallet_blue_shadow,
        R.drawable.wallet_red_shadow
    )

    fun observeValue(list: List<Wallet>) {
        data = list
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemWalletBinding.inflate(LayoutInflater.from(context), container, false)
        val item = data[position]
        val colorPos: Int = position % contentBackground.size
        binding.contraintContents.setBackgroundResource(contentBackground[colorPos])
        binding.imageShadow.setBackgroundResource(contentShadow[colorPos])

        binding.apply {
            tvBalance.text = item.balance.toString()
            tvWalletType.text = item.type
            tvWalletName.text = item.name
        }
        container.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
