package com.example.presentation.baseviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.resourses.R
import com.example.resourses.theme.DevSalesTheme
import com.example.resourses.theme.EightyFourDp

@Preview(showBackground = true)
@Composable
private fun ProgressBarPreview() {
    DevSalesTheme { ProgressBar() }
}

@Composable
fun ProgressBar() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.progress_bar_two)
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true,
            modifier = Modifier.size(EightyFourDp)
        )
    }
}