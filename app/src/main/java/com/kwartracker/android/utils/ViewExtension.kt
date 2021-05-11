package com.kwartracker.android.utils

import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.kwartracker.android.R

fun ViewPager.onPageChange(view: LinearLayout, count: Int, onPageSelected: (Int) -> Unit) {
    val dotsCount: Int = count
    val dots = arrayOfNulls<ImageView>(dotsCount)
    for (i in 0 until dotsCount) {
        dots[i] = ImageView(context)
        dots[i]?.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.non_active_dot
            )
        )
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(5, 0, 5, 0)
        view.addView(dots[i], params)
    }
    dots[0]?.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.active_dots))
    addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            for (i in 0 until dotsCount) {
                dots[i]?.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.non_active_dot
                    )
                )
            }
            dots[position]?.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.active_dots
                )
            )
            onPageSelected(position)
        }

        override fun onPageScrollStateChanged(state: Int) {}
    })
}
