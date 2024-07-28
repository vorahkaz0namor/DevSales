package com.example.presentation.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.resourses.R

@Composable
internal fun StoreLauncherRoute(
    storeViewModel: StoreLauncherVM,
) {
    StoreLauncherScreen()
}

@Composable
private fun StoreLauncherScreen(

) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mode_edit_24),
            contentDescription = null
        )
    }
}