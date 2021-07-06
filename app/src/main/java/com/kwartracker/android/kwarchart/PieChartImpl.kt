package com.kwartracker.android.kwarchart

import android.graphics.Color
import androidx.compose.ui.graphics.Color as ComposeColor
import com.kwarchart.android.model.ChartData
import com.kwarchart.android.model.PieSeries

object PieChartImpl {

    private val alpha = listOf(
        "FF", "CC", "99", "66"
    )

    fun chartData(
        categoryName: ArrayList<String>,
        categoryValue: ArrayList<Float>
    ): ArrayList<PieSeries<String>> {
        val initialPieSeries = arrayListOf<PieSeries<String>>()

        categoryName.forEachIndexed { i, s ->
            initialPieSeries.add(chart(s, categoryValue[i], i))
        }
        return initialPieSeries
    }

    private fun chart(categoryName: String, categoryValue: Float, index: Int) = PieSeries(
        data = ChartData(categoryName, categoryValue),
        color = ComposeColor(Color.parseColor("#${alpha[index]}0091EA")),
        legend = "$categoryName $categoryValue%"
    )
}
