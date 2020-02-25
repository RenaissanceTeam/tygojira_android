package ru.fors.activities.presentation.view.adapter

import org.threeten.bp.LocalDate

/**
 * Created by 23alot on 24.02.2020.
 */
data class ActivitiesViewData(
    val title: String,
    val period: ClosedRange<LocalDate>,
    val color: Int
)