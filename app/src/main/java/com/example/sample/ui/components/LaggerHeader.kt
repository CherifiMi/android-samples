package com.example.sample.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sample.R
import com.example.sample.theme.HeadingStyle
import com.example.sample.theme.SmallHeadingStyle
import com.example.sample.theme.TitleBarStyle

@Preview(showBackground = true)
@Composable
fun LaggerHeader(modifier: Modifier = Modifier) {
    Box(
        modifier.height(150.dp)
    ) {
        Row(modifier = Modifier.windowInsetsPadding(insets = WindowInsets.systemBars)) {
            IconButton(
                onClick = { },
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.not_implemented)
                )
            }

            Text(
                stringResource(R.string.jetlagged_app_heading),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                style = TitleBarStyle,
                textAlign = TextAlign.Start
            )
        }
    }
}


@Composable
fun LaggedSleepSummary(modifier: Modifier=Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column{
            Text(
                stringResource(R.string.average_time_in_bed_heading),
                style = SmallHeadingStyle
            )
            Text(
                stringResource(R.string.placeholder_text_ave_time),
                style = HeadingStyle
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                stringResource(R.string.average_sleep_time_heading),
                style = SmallHeadingStyle
            )
            Text(
                stringResource(R.string.placeholder_text_ave_time_2),
                style = HeadingStyle
            )
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
}