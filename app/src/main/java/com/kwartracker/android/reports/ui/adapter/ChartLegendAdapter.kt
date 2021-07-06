package com.kwartracker.android.reports.ui.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.kwartracker.android.R
import com.kwartracker.android.databinding.ItemChartLegendBinding
import com.kwartracker.android.reports.model.LegendData

class ChartLegendAdapter(
    private val legendData: ArrayList<LegendData>,
    private val context: Context
) :
    RecyclerView.Adapter<ChartLegendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemChartLegendBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(legendData[position], position, context)
    }

    override fun getItemCount(): Int {
        return legendData.size
    }

    class ViewHolder(binding: ItemChartLegendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val legendColor = binding.clLegend
        private val dotColor = binding.ciColor
        private val categoryName = binding.tvCategoryName
        private val categoryLabel = binding.tvCategoryLabel
        private val categoryAmount = binding.tvCategoryAmount

        private val alpha = listOf(
            "FF", "CC", "99", "66"
        )

        fun bind(legendData: LegendData, position: Int, context: Context) {
            if (legendData.amounts != null) {
                categoryAmount.visibility = View.VISIBLE
                categoryAmount.text = legendData.amounts
            }

            dotColor.background.setTint(Color.parseColor("#${alpha[position]}0091EA"))
            categoryName.text = legendData.names
            categoryLabel.text = context.resources.getString(R.string.sample_percentage)

            if (position % 2 == 0) {
                legendColor.background =
                    AppCompatResources.getDrawable(context, R.drawable.round_corner_light_blue)
                return
            }

            legendColor.background =
                AppCompatResources.getDrawable(context, R.drawable.round_corner_with_border)
        }
    }
}
