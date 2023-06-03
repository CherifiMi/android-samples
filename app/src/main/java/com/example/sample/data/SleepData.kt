package com.example.sample

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.example.sample.theme.Yellow_Awake
import com.example.sample.theme.Yellow_Deep
import com.example.sample.theme.Yellow_Light
import com.example.sample.theme.Yellow_Rem
import java.time.Duration
import java.time.LocalDateTime

data class SleepGraphData(
    val sleepDayData: List<SleepDayData>
)
@RequiresApi(Build.VERSION_CODES.O)
data class SleepDayData(
    val startDate : LocalDateTime,
    val sleepingPeriods: List<SleepPeriod>,
    val sleepScore: Int
){
    val firstSleepStart : LocalDateTime by lazy {
        sleepingPeriods.sortedBy(SleepPeriod::startTime).first().startTime
    }
    val lastSleepEnd: LocalDateTime by lazy {
        sleepingPeriods.sortedBy(SleepPeriod::startTime).last().endTime
    }
    val totalTimeInBed: Duration by lazy{
        Duration.between(firstSleepStart, lastSleepEnd)
    }
    val sleepScoreEmoji: String by lazy {
        when (sleepScore) {
            in 0..40 -> "😖"
            in 41..60 -> "😏"
            in 60..70 -> "😴"
            in 71..100 -> "😃"
            else -> "🤷‍"
        }
    }

    fun fractionOfTotalTime(sleepPeriod: SleepPeriod): Float {
        return sleepPeriod.duration.toMinutes() / totalTimeInBed.toMinutes().toFloat()
    }

    fun minutesAfterSleepStart(sleepPeriod: SleepPeriod): Long {
        return Duration.between(
            firstSleepStart,
            sleepPeriod.startTime
        ).toMinutes()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
data class SleepPeriod(
    val startTime: LocalDateTime,
    val endTime: LocalDateTime,
    val type: SleepType
){
    val duration: Duration by lazy{
        Duration.between(startTime, endTime)
    }
}

enum class SleepType(val title: Int, val color: Color) {
    Awake(R.string.sleep_type_awake, Yellow_Awake),
    Rem(R.string.sleep_type_rem, Yellow_Rem),
    Light(R.string.sleep_type_light, Yellow_Light),
    Deep(R.string.sleep_type_deep, Yellow_Deep),
}