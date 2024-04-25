package com.example.studybuddies.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddies.R
import com.example.studybuddies.components.ButtonComponent
import com.example.studybuddies.components.ClickableLoginTextComponent
import com.example.studybuddies.components.DividerTextComponent
import com.example.studybuddies.components.HeadingTextComponent
import com.example.studybuddies.components.MyTextFieldComponent
import com.example.studybuddies.components.NormalTextComponent
import com.example.studybuddies.components.PasswordTextFieldComponent
import com.example.studybuddies.components.UnderLinedTextComponent
import com.example.studybuddies.components.UploadImageComponent
import com.example.studybuddies.data.login.LoginUIEvent
import com.example.studybuddies.data.login.LoginViewModel
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.navigation.SystemBackButtonHandler

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = viewModel()) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                HeadingTextComponent(value = stringResource(id = R.string.welcome))
                NormalTextComponent(value = "to")

                NormalTextComponent(value = stringResource(R.string.study_buddies))

                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.email),
                    painterResource(id = R.drawable.message),
                    initialValue = "",
                    onTextChanged = {
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.emailError
                )

                PasswordTextFieldComponent(
                    labelValue = stringResource(id = R.string.password),
                    painterResource(id = R.drawable.lock),
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    errorStatus = loginViewModel.loginUIState.value.passwordError
                )

                Spacer(modifier = Modifier.height(40.dp))
                UnderLinedTextComponent(value = stringResource(id = R.string.forgot_password))

                Spacer(modifier = Modifier.height(40.dp))

                ButtonComponent(
                    value = stringResource(id = R.string.login),
                    onButtonClicked = {
                       loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                    },
                    isEnabled = loginViewModel.allValidationsPassed.value
                )

                Spacer(modifier = Modifier.height(20.dp))

                DividerTextComponent()

                ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                    StudyBuddiesAppRouter.navigateTo(Screen.SignUpScreen)
                })

                UploadImageComponent(painterResource(id=R.mipmap.login_image_foreground))
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }


    SystemBackButtonHandler {
        StudyBuddiesAppRouter.navigateTo(Screen.SignUpScreen)
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}