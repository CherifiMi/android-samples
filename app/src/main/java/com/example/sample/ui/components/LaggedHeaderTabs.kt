package com.example.sample.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sample.R


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
    modifire: Modifier = Modifier
) {

}