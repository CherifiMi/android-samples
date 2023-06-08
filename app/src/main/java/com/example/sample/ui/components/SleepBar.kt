package com.example.sample.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.sample.SleepDayData
import com.example.sample.SleepType
import com.example.sample.data.sleepData


@Preview
@Composable
fun sleepPrev() {
    SleepBar(sleepData = sleepData.sleepDayData.first())
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SleepBar(
    sleepData: SleepDayData,
    modifier: Modifier = Modifier
) {
    var isExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    val transition = updateTransition(targetState = isExpanded, label = "expanded")

    Column(
        modifier = Modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                isExpanded = !isExpanded
            }
    ) {
        SleepRoundedBar(
            sleepData,
            transition
        )

        transition.AnimatedVisibility(
            enter = fadeIn(animationSpec = tween(animationDuration)) + expandVertically(
                animationSpec = tween(animationDuration)
            ),
            exit = fadeOut(animationSpec = tween(animationDuration)) + shrinkVertically(
                animationSpec = tween(animationDuration)
            ),
            content = {
                DetailLegend()
            },
            visible = { it }
        )
    }
}

@Composable
fun DetailLegend() {

}

@OptIn(ExperimentalTextApi::class)
@Composable
fun SleepRoundedBar(sleepData: SleepDayData, transition: Transition<Boolean>) {
    val textMeasurer = rememberTextMeasurer()

    val hight by transition.animateFloat(
        label = "hight",
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        }
    ) {
        if ( it) 1f else 0f
    }

    Spacer(
        modifier = Modifier
            .drawWithCache {
                val width = this.size.width
                val cornerRadiusStartPx = 2.dp.toPx()
                val collapsedCornerRadiusPx = 10.dp.toPx()
                val animatedCornerRadius = CornerRadius(
                    lerp(cornerRadiusStartPx, collapsedCornerRadiusPx, (1 - animationProgress))
                )

                val lineThicknessPx = lineThickness.toPx()
                val roundedRectPath = Path()
                roundedRectPath.addRoundRect(
                    RoundRect(
                        rect = Rect(
                            Offset(x = 0f, y = -lineThicknessPx / 2f),
                            Size(
                                this.size.width + lineThicknessPx * 2,
                                this.size.height + lineThicknessPx
                            )
                        ),
                        cornerRadius = animatedCornerRadius
                    )
                )
                val roundedCornerStroke = Stroke(
                    lineThicknessPx,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round,
                    pathEffect = PathEffect.cornerPathEffect(
                        cornerRadiusStartPx * animationProgress
                    )
                )
                val barHeightPx = barHeight.toPx()

                val sleepGraphPath = generateSleepPath(
                    this.size,
                    sleepData, width, barHeightPx, animationProgress,
                    lineThickness.toPx() / 2f
                )
                val gradientBrush =
                    Brush.verticalGradient(
                        colorStops = sleepGradientBarColorStops.toTypedArray(),
                        startY = 0f,
                        endY = SleepType.values().size * barHeightPx
                    )
                val textResult = textMeasurer.measure(AnnotatedString(sleepData.sleepScoreEmoji))

                onDrawBehind {
                    drawSleepBar(
                        roundedRectPath,
                        sleepGraphPath,
                        gradientBrush,
                        roundedCornerStroke,
                        animationProgress,
                        textResult,
                        cornerRadiusStartPx
                    )
                }
            }
            .height(height)
            .fillMaxWidth()
    )

}

fun DrawScope.drawSleepBar(
    roundedRectPath: Path,
    sleepGraphPath: Any,
    gradientBrush: Brush,
    roundedCornerStroke: Stroke,
    animationProgress: Any,
    textResult: TextLayoutResult,
    cornerRadiusStartPx: Float
) {

}


private const val animationDuration = 500
private val lineThickness = 2.dp
private val barHeight = 24.dp
private val textPadding = 4.dp