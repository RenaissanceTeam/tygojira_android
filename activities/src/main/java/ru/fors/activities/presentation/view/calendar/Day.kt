package ru.fors.activities.presentation.view.calendar

import com.kizitonwose.calendarview.model.CalendarDay

/**
 * Created by 23alot on 23.02.2020.
 */
data class Day(
    val day: CalendarDay,
    val activities: List<Workload>?
)

data class Workload(
    val color: Int,
    val text: String?
)