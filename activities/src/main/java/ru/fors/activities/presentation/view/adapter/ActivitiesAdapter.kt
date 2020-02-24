package ru.fors.activities.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activities_item.view.*
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.FormatStyle
import ru.fors.activities.R

/**
 * Created by 23alot on 24.02.2020.
 */
class ActivitiesAdapter : RecyclerView.Adapter<ActivitiesAdapter.ActivitiesViewHolder>() {

    private var items: List<ActivitiesViewData> = listOf()

    private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    fun setItems(items: List<ActivitiesViewData>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivitiesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activities_item, parent, false)

        return ActivitiesViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ActivitiesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(data: ActivitiesViewData) {
            view.activities_item_color.setBackgroundColor(data.color)
            view.activities_item_title.text = data.title

            val start = data.period.start.format(formatter)
            val end = data.period.endInclusive.format(formatter)
            view.activities_item_description.text = "${start}-${end}"
        }

    }
}