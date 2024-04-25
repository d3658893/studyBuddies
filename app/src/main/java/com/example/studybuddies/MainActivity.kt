package com.example.studybuddies

//import androidx.compose.material3.HorizontalDivider
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.studybuddies.app.StudyBuddiesApp
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudyBuddiesApp()
        }
        FirebaseApp.initializeApp(this)
        val db = Firebase.firestore.collection("users")
        Log.d("db","$db")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DefaultPreview(){
    StudyBuddiesApp()
}
