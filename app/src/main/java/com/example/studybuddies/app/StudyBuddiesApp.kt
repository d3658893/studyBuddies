package com.example.studybuddies.app

//import androidx.lifecycle.viewmodel.compose.viewModel

import UserProfileScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.screens.HomeScreen
import com.example.studybuddies.screens.LoginScreen
import com.example.studybuddies.screens.PostScreen
import com.example.studybuddies.screens.SignUpScreen
import com.example.studybuddies.screens.TermsAndConditionScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StudyBuddiesApp(){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White,
    ) {

        Crossfade(targetState = StudyBuddiesAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionScreen()
                }

                is Screen.LoginScreen -> {
                    LoginScreen()
                }

                is Screen.HomeScreen -> {
                    HomeScreen()
                }
                is Screen.UserProfileScreen -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        UserProfileScreen()
                    }
                }
                is Screen.PostScreen -> {
                    PostScreen()
                }

            }
        }
        
    }
}
