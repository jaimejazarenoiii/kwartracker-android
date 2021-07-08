package com.kwartracker.android.kwarchart

import androidx.compose.ui.graphics.Color
import com.kwarchart.android.model.BarSeries
import com.kwarchart.android.model.ChartData

object BarChartImpl {

    val expenseSeries = BarSeries(
        data = mutableListOf(
            ChartData(1, 1100f),
            ChartData(2, 400f),
            ChartData(3, 500f),
            ChartData(4, 1500f),
            ChartData(5, 1800f),
            ChartData(6, 1200f),
            ChartData(7, 2200f)
        ),
        color = Color(0xFFE26767),
        legend = "Expense"
    )

    val incomeSeries = BarSeries(
        data = mutableListOf(
            ChartData(1, 4100f),
            ChartData(2, 2500f),
            ChartData(3, 3500f),
            ChartData(4, 3700f),
            ChartData(5, 4500f),
            ChartData(6, 3200f),
            ChartData(7, 3700f)
        ),
        color = Color(0xFF0EBCA7),
        legend = "Income"
    )
}
