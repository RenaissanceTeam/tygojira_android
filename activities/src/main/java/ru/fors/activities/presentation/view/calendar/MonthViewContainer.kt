package ru.fors.activities.presentation.view.calendar

import android.view.View
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day.view.*
import kotlinx.android.synthetic.main.calendar_month.view.*

/**
 * Created by 23alot on 23.02.2020.
 */
class MonthViewContainer(view: View) : ViewContainer(view) {
    val textView = view.calendar_month_text

    // Without the kotlin android extensions plugin
    // val textView = view.findViewById<TextView>(R.id.calendarDayText)
}