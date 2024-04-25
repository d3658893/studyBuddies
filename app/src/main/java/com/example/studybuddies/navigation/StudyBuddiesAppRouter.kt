package com.example.studybuddies.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {

    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object LoginScreen : Screen()
    object HomeScreen : Screen()
    object UserProfileScreen : Screen()
}


object StudyBuddiesAppRouter {

    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination: Screen) {
        currentScreen.value = destination
    }
}