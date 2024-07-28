package com.example.presentation.utils

import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

internal inline fun <reified T : Any> NavGraphBuilder.navigatingToScreen(
    noinline launcherRoute: @Composable () -> Unit
) {
    animatedComposableSlideHorizontal<T> {
        launcherRoute()
    }
}

inline fun <reified T : Any> NavGraphBuilder.animatedComposableSlideHorizontal(
    crossinline content: @Composable (NavBackStackEntry) -> Unit
) {
    composable<T>(
        enterTransition = {
            slideInHorizontally(initialOffsetX = { 500 })
        },
        exitTransition = {
            fadeOut()
        },
        popEnterTransition = {
            slideInHorizontally(initialOffsetX = { - 500 })
        },
        popExitTransition = {
            fadeOut()
        }
    ) {
        content(it)
    }
}