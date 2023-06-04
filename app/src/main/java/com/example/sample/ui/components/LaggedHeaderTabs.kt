package com.example.sample.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sample.R
import com.example.sample.data.sleepData
import com.example.sample.theme.SmallHeadingStyle
import com.example.sample.theme.Yellow


enum class SleepTab(val title: Int) {
    Day(R.string.sleep_tab_day_heading),
    Week(R.string.sleep_tab_week_heading),
    Month(R.string.sleep_tab_month_heading),
    SixMonths(R.string.sleep_tab_six_months_heading),
    OneYear(R.string.sleep_tab_one_year_heading)
}

@Composable
fun LaggedHeaderTabs(
    onTabSelected: (SleepTab) -> Unit,
    selectedTab: SleepTab,
    modifier: Modifier = Modifier
) {

    ScrollableTabRow(
        modifier = modifier,
        edgePadding = 12.dp,
        selectedTabIndex = selectedTab.ordinal,
        containerColor = Color.White,
        indicator = {tabPositions: List<TabPosition> ->
            Box(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedTab.ordinal])
                    .fillMaxSize()
                    .padding(horizontal = 2.dp)
                    .border(BorderStroke(2.dp,Yellow), RoundedCornerShape(10.dp))
            )
        },
        divider = {}
    ) {

        SleepTab.values().forEachIndexed{i,s ->
            val selected = i == selectedTab.ordinal
            SleepTabText(
                sleepTab = s,
                selected = selected,
                onTabSelected = onTabSelected,
                index = i
            )
        }

    }

}

@Composable
fun SleepTabText(
    sleepTab: SleepTab,
    selected: Boolean,
    onTabSelected: (SleepTab) -> Unit,
    index: Int
) {
    Tab(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(16.dp)),
        selected = selected,
        unselectedContentColor = Color.Black,
        selectedContentColor = Color.Black,
        onClick = {
            onTabSelected(SleepTab.values()[index])
        }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 6.dp, horizontal = 4.dp),
            text = stringResource(id = sleepTab.title),
            style = SmallHeadingStyle
        )
    }
}
