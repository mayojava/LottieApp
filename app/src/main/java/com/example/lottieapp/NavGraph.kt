package com.example.lottieapp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lottieapp.ui.home.LottieAnimationHome
import com.example.lottieapp.ui.viewmodel.HomeViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.Home.route
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {
        composable(route = Destinations.Home.route) {
            val viewModel = hiltViewModel<HomeViewModel>()
            LottieAnimationHome(viewModel)
        }
    }
}

sealed class Destinations(val route: String) {
    object Home: Destinations("home")
    object Details: Destinations("details")
}