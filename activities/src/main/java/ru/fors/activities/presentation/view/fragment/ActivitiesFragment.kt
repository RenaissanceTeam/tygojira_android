package ru.fors.activities.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.core.util.toRange
import androidx.lifecycle.lifecycleScope
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import kotlinx.android.synthetic.main.fragment_activities.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.scope.currentScope
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.WeekFields
import ru.fors.activities.R
import ru.fors.activities.api.domain.dto.Activity
import ru.fors.activities.presentation.view.calendar.Day
import ru.fors.activities.presentation.view.calendar.DayViewContainer
import ru.fors.activities.presentation.view.calendar.MonthViewContainer
import ru.fors.activities.presentation.view.calendar.Workload
import ru.fors.activities.presentation.viewmodel.ActivitiesViewModel
import ru.fors.activities.presentation.viewmodel.ActivitiesViewState
import ru.fors.navigation.ui.BaseFragment
import java.util.*

/**
 * Created by 23alot on 26.01.2020.
 */
@InternalCoroutinesApi
class ActivitiesFragment : BaseFragment() {

    private val model: ActivitiesViewModel by currentScope.inject()
    private var dayColorMap: Map<LocalDate, List<Workload>> = mapOf()

    override val layoutRes: Int
        get() = R.layout.fragment_activities

    override val shouldShowNavigationBar: Boolean
        get() = true

    private val colors by lazy {
        listOf<Int>(
            resources.getColor(R.color.activity1),
            resources.getColor(R.color.activity2),
            resources.getColor(R.color.activity3),
            resources.getColor(R.color.activity4),
            resources.getColor(R.color.activity5),
            resources.getColor(R.color.activity6),
            resources.getColor(R.color.activity7)
        )
    }

    private val transparentColor by lazy { resources.getColor(R.color.transparent) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activities_calendar.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                val newDay = Day(
                    day,
                    activities = dayColorMap[day.date]?.reversed()
                )
                container.updateWorkload(newDay)
            }
        }

        activities_calendar.monthHeaderBinder =
            object : MonthHeaderFooterBinder<MonthViewContainer> {
                override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                    container.textView.text = month.yearMonth.toString()
                }

                override fun create(view: View): MonthViewContainer = MonthViewContainer(view)
            }

        val dm = DisplayMetrics()
        val wm = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
        wm.defaultDisplay.getMetrics(dm)
        activities_calendar.dayWidth = dm.widthPixels / 7
        activities_calendar.dayHeight = dm.widthPixels / 4

        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusMonths(10)
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        activities_calendar.setup(firstMonth, lastMonth, firstDayOfWeek)
        activities_calendar.scrollToMonth(currentMonth)

        lifecycleScope.launch {
            model.state
                .collect { updateState(it) }
        }
        model.onActivitiesRequired()
        activities_toolbar.title = "123"
        activities_toolbar.subtitle = "345"
    }

    private fun updateState(state: ActivitiesViewState) {
        val b = state.activities.map(Activity::id)
        // Мапа id -> цвет
        val c = mutableMapOf<Long, Int>()
        b.forEachIndexed { index, id ->
            c[id] = colors[index % colors.count()]
        }
        val a = mutableMapOf<LocalDate, MutableList<Workload>>()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        state.activities.forEachIndexed { index, activity ->
            val start = LocalDate.parse(activity.startDate, formatter)
            val end = LocalDate.parse(activity.endDate)
            var day = LocalDate.from(start)
            val color = c[activity.id]!!
            while (day in start.rangeTo(end)) {
                if (!a.containsKey(day)) a[day] = mutableListOf(
                    Workload(transparentColor, null),
                    Workload(transparentColor, null),
                    Workload(transparentColor, null),
                    Workload(transparentColor, null),
                    Workload(transparentColor, null),
                    Workload(transparentColor, null),
                    Workload(transparentColor, null)
                )
                val workload = Workload(
                    color,
                    if (day == start) activity.name else null
                )
                a[day]?.set(index, workload)
                day = day.plusDays(1)
            }
        }
        dayColorMap = a
        a.keys.forEach { activities_calendar.notifyDateChanged(it) }
    }
}