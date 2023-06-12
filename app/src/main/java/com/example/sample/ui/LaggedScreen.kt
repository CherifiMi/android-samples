package com.example.sample.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sample.SleepGraphData
import com.example.sample.data.sleepData
import com.example.sample.theme.SmallHeadingStyle
import com.example.sample.theme.Yellow
import com.example.sample.theme.YellowVariant
import com.example.sample.ui.components.*
import com.example.sample.yellowBackground
import java.time.DayOfWeek
import java.time.format.TextStyle
import java.util.*


@Composable
fun LaggedScreen(){
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.yellowBackground()
        ) {
            LaggerHeader(modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(32.dp))
            LaggedSleepSummary(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        var selectedTab by remember { mutableStateOf(SleepTab.Week) }
        LaggedHeaderTabs(
            onTabSelected = { selectedTab = it },
            selectedTab = selectedTab,
        )

        Spacer(modifier = Modifier.height(16.dp))

        //todo
        val sleepState by remember{
            mutableStateOf(sleepData)
        }
        LaggedTimeGraph(sleepState)
    }
}

@Composable
fun LaggedTimeGraph(sleepGraphData: SleepGraphData) {
    val scrollState = rememberScrollState()
    val hours = (sleepGraphData.earliestStartHour..23) + (0..sleepGraphData.latestEndHour)

    TimeGraph(
        modifier = Modifier
            .horizontalScroll(scrollState)
            .wrapContentSize(),
        dayItemsCount = sleepGraphData.sleepDayData.size,
        hoursHeader = {
            HoursHeader(hours)
        },
        dayLabel = { index ->
            val data = sleepGraphData.sleepDayData[index]
            DayLabel(data.startDate.dayOfWeek)
        },
        bar = { index ->
            val data = sleepGraphData.sleepDayData[index]
            // We have access to Modifier.timeGraphBar() as we are now in TimeGraphScope
            SleepBar(
                sleepData = data,
                modifier = Modifier.padding(bottom = 8.dp)
                    .timeGraphBar(
                        start = data.firstSleepStart,
                        end = data.lastSleepEnd,
                        hours = hours,
                    )
            )
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DayLabel(dayOfWeek: DayOfWeek) {
    Text(
        dayOfWeek.getDisplayName(
            TextStyle.SHORT, Locale.getDefault()
        ),
        Modifier
            .height(24.dp)
            .padding(start = 8.dp, end = 24.dp),
        style = SmallHeadingStyle,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun HoursHeader(hours: List<Int>) {
    Row(
        Modifier
            .padding(bottom = 16.dp)
            .drawBehind {
                val brush = Brush.linearGradient(listOf(YellowVariant, Yellow))
                drawRoundRect(
                    brush,
                    cornerRadius = CornerRadius(10.dp.toPx(), 10.dp.toPx()),
                )
            }
    ) {
        hours.forEach {
            Text(
                text = "$it",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(50.dp)
                    .padding(vertical = 4.dp),
                style = SmallHeadingStyle
            )
        }
    }
}
