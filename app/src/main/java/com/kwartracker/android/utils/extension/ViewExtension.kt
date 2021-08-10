package com.kwartracker.android.utils.extension

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.kwartracker.android.R
import com.kwartracker.android.utils.CoroutineTask

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

fun View.setVisible(visibility: Boolean = true) {
    if (visibility) {
        setVisibility(View.VISIBLE)
    } else {
        setVisibility(View.GONE)
    }
}

fun CardView.clearCardViewConfiguration() {
    setCardBackgroundColor(Color.TRANSPARENT)
    cardElevation = 0f
}

fun LifecycleOwner.bind(
    text: MutableLiveData<String>,
    afterTextChanged: ((String) -> Unit)? = null
) {
    text.observe(
        this,
        Observer {
            afterTextChanged?.invoke(it)
        }
    )
}

fun <T : Any> Fragment.handleApolloResponse(
    task: CoroutineTask<T>,
    handler: ((T?) -> Unit)? = null
) {
    task.observe(viewLifecycleOwner) {
        if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
            onSuccess {
                handler?.invoke(it)
            }
            onFailure {}
        }
    }
}

// @BindingAdapter("setTextMax")
// fun TextView.setText(genderType: GenderType) {
//    this.text = getSgenderType.drawableResId
// }
