package ru.fors.activities.api.domain.dto

/**
 * Created by 23alot on 18.03.2020.
 */
data class Workload(
    val activity: Activity,
    val employeeId: Long,
    val workUnits: List<WorkUnit>,
    val workloadId: Long
)

data class WorkUnit(
    val id: Long,
    val hours: Int,
    val date: String
)