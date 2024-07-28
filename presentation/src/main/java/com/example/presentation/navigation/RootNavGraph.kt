package com.example.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.presentation.store.navigation.StoreNavHost
import com.example.presentation.utils.navigatingToScreen
import kotlinx.serialization.Serializable

@Serializable
object Store

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Store
    ) {
        navigatingToScreen<Store> {
            StoreNavHost()
        }
    }
}