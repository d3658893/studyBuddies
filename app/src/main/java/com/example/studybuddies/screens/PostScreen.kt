package com.example.studybuddies.screens

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.studybuddies.components.HeadingTextComponent
import com.example.studybuddies.components.MyTextFieldComponent
import com.example.studybuddies.components.NormalTextComponent
import com.example.studybuddies.components.postImage
import com.example.studybuddies.data.post.PostUIEvent
import com.example.studybuddies.data.post.PostViewModel
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.navigation.SystemBackButtonHandler

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun PostScreen(postViewModel: PostViewModel = viewModel()) {

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
//                UploadImageComponent(painterResource(id=R.mipmap.login_image_foreground))

                HeadingTextComponent(value = stringResource(id = R.string.createAPost))

                NormalTextComponent(value = stringResource(R.string.createAPost))

                Spacer(modifier = Modifier.height(20.dp))

                MyTextFieldComponent(labelValue = stringResource(id = R.string.title),
                    painterResource(id = android.R.drawable.ic_menu_compass),
                    initialValue = "",
                    onTextChanged = {
                        postViewModel.onEvent(PostUIEvent.TitleChanged(it))
                    },
                    errorStatus = postViewModel.postUIState.value.titleError
                )

                MyTextFieldComponent(
                    labelValue = stringResource(id = R.string.description),
                    painterResource(id = android.R.drawable.ic_dialog_info),
                    initialValue = "",
                    onTextChanged = {
                        postViewModel.onEvent(PostUIEvent.DescriptionChanged(it))
                    },
                    errorStatus = postViewModel.postUIState.value.descriptionError
                )

                Spacer(modifier = Modifier.height(360.dp))
                ButtonComponent(
                    value = stringResource(id = R.string.save),
                    onButtonClicked = {
                        postViewModel.onEvent(PostUIEvent.SaveButtonClicked)
                    },
                    isEnabled = postViewModel.allValidationsPassed.value
                )
            }
        }

        if(postViewModel.postInProgress.value) {
            CircularProgressIndicator()
        }
        postImage(onImageChanged ={
            postViewModel.onEvent(PostUIEvent.ImageChanged(it))
        })
    }

    SystemBackButtonHandler {
        StudyBuddiesAppRouter.navigateTo(Screen.HomeScreen)
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun PostScreenPreview() {
    PostScreen()
}