package com.example.sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sample.SleepGraphData
import com.example.sample.data.sleepData
import com.example.sample.yellowBackground
import java.time.LocalDateTime


@Composable
fun LaggedScreen(){
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(modifier = Modifier.yellowBackground()) {
            LaggerHeader(modifire = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(32.dp))
            JetLaggedSleepSummary(modifier = Modifier.padding(start = 16.dp, end = 16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        var selectedTab by remember { mutableStateOf(SleepTab.Week) }

        JetLaggedHeaderTabs(
            onTabSelected = { selectedTab = it },
            selectedTab = selectedTab,
        )

        Spacer(modifier = Modifier.height(16.dp))
        val sleepState by remember{
            mutableStateOf(sleepData)
        }
        JetLaggedTimeGraph(sleepState)
    }
}

@Composable
fun JetLaggedTimeGraph(sleepState: SleepGraphData) {

}

@Composable
fun JetLaggedHeaderTabs(onTabSelected: () -> Unit, selectedTab: Any) {

}

enum class SleepTab {
    Week
}

@Composable
fun LaggerHeader(modifire: Modifier) {

}


@Composable
fun JetLaggedSleepSummary(modifier: Modifier) {
}
