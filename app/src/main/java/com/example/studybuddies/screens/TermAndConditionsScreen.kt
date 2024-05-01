package com.example.studybuddies.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studybuddies.R
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.navigation.SystemBackButtonHandler
import com.example.studybuddies.ui.theme.Purple40
import com.example.studybuddies.ui.theme.WhiteColor

@Composable
fun TermsAndConditionScreen(){
    val scrollState = rememberScrollState()

    Surface(modifier= Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(16.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize().verticalScroll(scrollState)
        ) {
            Text(
                text = "Terms & Conditions",
                modifier = Modifier
                .fillMaxWidth()
//                    .height(40.dp)
                    .background(Purple40),
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ), color = WhiteColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = stringResource(id = R.string.terms_text),
                modifier = Modifier
                .fillMaxWidth()
//                    .height(40.dp)
                    .background(Color.White),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal
                ), color = Purple40,
                textAlign = TextAlign.Center
            )
        }
    }
    SystemBackButtonHandler {
        StudyBuddiesAppRouter.navigateTo(Screen.HomeScreen)
    }
}

@Preview
@Composable
fun TermsAndConditionScreenPreview(){
    TermsAndConditionScreen()
}