package com.example.studybuddies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.studybuddies.app.StudyBuddiesApp
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyBuddiesApp()
        }
        FirebaseApp.initializeApp(this)

    }
}

@Preview
@Composable
fun DefaultPreview(){
    StudyBuddiesApp()
}

