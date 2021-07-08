package com.kwartracker.android.reports.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kwarchart.android.chart.BarChart
import com.kwarchart.android.chart.PieChart
import com.kwarchart.android.enum.BarChartType
import com.kwarchart.android.enum.LegendPosition
import com.kwarchart.android.enum.PieChartType
import com.kwartracker.android.R
import com.kwartracker.android.databinding.FragmentReportsDashboardBinding
import com.kwartracker.android.kwarchart.BarChartImpl
import com.kwartracker.android.kwarchart.PieChartImpl
import com.kwartracker.android.reports.model.LegendData
import com.kwartracker.android.reports.ui.adapter.ChartLegendAdapter

class ReportsDashboardFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentReportsDashboardBinding
    private lateinit var incomeCategoryLegend: ChartLegendAdapter
    private var legendData = arrayListOf<LegendData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReportsDashboardBinding.inflate(inflater)

        binding.toolbar.tvTitle.text = getString(R.string.title_reports)
        binding.toolbar.btnContainer.visibility = View.INVISIBLE
        binding.toolbar.btnBack.setOnClickListener(this)

        incomeExpenseBarChart()
        incomeByCategoryPieChart()
        expenseByCategoryPieChart()
        walletBalancePieChart()

        return binding.root
    }

    private fun incomeExpenseBarChart() {
        binding.composeBarIncomeExpense.apply {
            setContent {
                BarChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color.White),
                    data =
                    arrayListOf(
                        BarChartImpl.incomeSeries,
                        BarChartImpl.expenseSeries
                    ),
                    legendPos = LegendPosition.BOTTOM,
                    type = BarChartType.VERTICAL
                )
            }
        }
    }

    private fun displayLegend(
        category: ArrayList<String>,
        value: ArrayList<Float>,
        recyclerView: RecyclerView,
        amount: String? = null
    ) {
        legendData = arrayListOf()
        category.forEachIndexed { i, name ->
            legendData.add(
                LegendData(
                    name,
                    value[i],
                    amount
                )
            )
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = ChartLegendAdapter(legendData, context)
        }
    }

    private fun incomeByCategoryPieChart() {
        val incomeCategory = arrayListOf(
            "Category Salary", "Category Gift", "Category Bonus", "Category Other"
        )
        val incomeValues = arrayListOf(
            1050f, 5000f, 2050f, 8000f
        )

        displayLegend(incomeCategory, incomeValues, binding.rvIncomeCategoryLegend)

        binding.composePieIncomeCategory.apply {
            setContent {
                PieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color.White),
                    data = PieChartImpl.chartData(incomeCategory, incomeValues),
                    type = PieChartType.DOUGHNUT
                )
            }
        }
    }

    private fun expenseByCategoryPieChart() {
        val expenseCategory = arrayListOf(
            "Food", "Grocery", "Transportation", "Bills"
        )
        val expenseValues = arrayListOf(
            500f, 1000f, 100f, 100f
        )

        displayLegend(
            expenseCategory,
            expenseValues,
            binding.rvExpenseCategoryLegend
        )

        binding.composePieExpenseCategory.apply {
            setContent {
                PieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color.White),
                    data = PieChartImpl.chartData(expenseCategory, expenseValues),
                    type = PieChartType.DOUGHNUT
                )
            }
        }
    }

    private fun walletBalancePieChart() {
        val balanceCategory = arrayListOf(
            "BPI Savings", "Unionbank Savings", "Daily Allowance", "Food Allowance"
        )
        val balanceValues = arrayListOf(
            123f, 456f, 789f, 900f
        )

        displayLegend(
            balanceCategory,
            balanceValues,
            binding.rvWalletBalanceLegend,
            resources.getString(R.string.sample_wallet_balance)
        )

        binding.composePieWalletBalance.apply {
            setContent {
                PieChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color.White),
                    data = PieChartImpl.chartData(balanceCategory, balanceValues),
                    type = PieChartType.DOUGHNUT
                )
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.toolbar.btnBack -> {
                findNavController().popBackStack()
            }
        }
    }
}
