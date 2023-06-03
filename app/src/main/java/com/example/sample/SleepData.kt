package com.example.sample

import androidx.compose.ui.graphics.Color
import com.example.sample.ui.theme.Yellow_Awake
import com.example.sample.ui.theme.Yellow_Deep
import com.example.sample.ui.theme.Yellow_Light
import com.example.sample.ui.theme.Yellow_Rem
import java.time.LocalDateTime

data class SleepData(
    val sleepDayData: List<SleepDayData>
)
data class SleepDayData(
    val startDate : LocalDateTime,
    val sleepingPeriods: List<SleepPeriod>,
    val sleepScore: Int
){

}

data class SleepPeriod(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val type: SleepType
)

enum class SleepType(val title: Int, val color: Color) {
    Awake(R.string.sleep_type_awake, Yellow_Awake),
    Rem(R.string.sleep_type_rem, Yellow_Rem),
    Light(R.string.sleep_type_light, Yellow_Light),
    Deep(R.string.sleep_type_deep, Yellow_Deep),
}
