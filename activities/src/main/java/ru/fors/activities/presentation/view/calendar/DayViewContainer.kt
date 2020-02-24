package ru.fors.activities.presentation.view.calendar

import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day.view.*

/**
 * Created by 23alot on 23.02.2020.
 */
class DayViewContainer(view: View) : ViewContainer(view) {

    fun updateWorkload(day: Day) {
        view.calendar_day_text.text = day.day.date.dayOfMonth.toString()
        day.activities?.forEach { workload ->
            val childView = TextView(view.context)
            childView.text = workload.text.orEmpty()
            childView.textSize = 7f
            childView.gravity = Gravity.CENTER_VERTICAL
            childView.setBackgroundColor(workload.color)
            val params = LinearLayout.LayoutParams(view.calendar_day_list.layoutParams)
            params.height = 50
            params.width = MATCH_PARENT
            childView.layoutParams = params
            view.calendar_day_list.addView(childView)
        }
    }
}