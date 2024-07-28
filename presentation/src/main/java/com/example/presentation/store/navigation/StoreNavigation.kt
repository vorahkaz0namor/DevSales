package com.example.presentation.store.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.presentation.store.StoreLauncherRoute
import com.example.presentation.store.StoreLauncherVM
import com.example.presentation.utils.navigatingToScreen
import kotlinx.serialization.Serializable

@Serializable
object StoreMainScreen

@Composable
fun StoreNavHost(
    storeNavController: NavHostController = rememberNavController(),
    storeViewModel: StoreLauncherVM = hiltViewModel<StoreLauncherVM>()
) {
    NavHost(
        navController = storeNavController,
        startDestination = StoreMainScreen
    ) {
        navigatingToScreen<StoreMainScreen> {
            StoreLauncherRoute(storeViewModel = storeViewModel)
        }
    }
}