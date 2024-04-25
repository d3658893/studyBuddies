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

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//@Composable
//fun ApplayoutDrawer(){
//    val navigationController = rememberNavController()
//    val corOutlineScope = rememberCoroutineScope()
//    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
//    val context = LocalContext.current.applicationContext
//
//    ModalNavigationDrawer(
//        drawerState = drawerState,
//        gesturesEnabled = true,
//        drawerContent = {
//        ModalDrawerSheet {
//            Box(modifier = Modifier
//                .background(Primary)
//                .fillMaxWidth()
//                .height(150.dp)){
//                Text(text = "")
//            }
//            HorizontalDivider()
//            NavigationDrawerItem(label = { Text(text="Home", color = Primary) },
//                selected = false,
//                icon = {Icon(imageVector = Icons.Default.Home, contentDescription = null)},
//                onClick = {
//                    corOutlineScope.launch { drawerState.close() }
//                    StudyBuddiesAppRouter.navigateTo(Screen.LoginScreen)
//                })
//            NavigationDrawerItem(label = { Text(text="Terms & Conditions", color = Primary) },
//                selected = false,
//                icon = {Icon(imageVector = Icons.Default.PrivacyTip, contentDescription = null)},
//                onClick = {
//                    corOutlineScope.launch { drawerState.close() }
//                    StudyBuddiesAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
//                }
//            )
//            NavigationDrawerItem(label = { Text(text="Terms & Conditions", color = Primary) },
//            selected = false,
//            icon = {Icon(imageVector = Icons.Default.PrivacyTip, contentDescription = null)},
//            onClick = {
//                corOutlineScope.launch { drawerState.close() }
//                StudyBuddiesAppRouter.navigateTo(Screen.TermsAndConditionsScreen)
//                }
//            )
//        }
//    }){
//        Scaffold(
//            topBar = {
//                val corOutlineScope = rememberCoroutineScope()
//                TopAppBar(title = { Text(text = "Study Buddies")},
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = Primary,
//                        titleContentColor = WhiteColor,
//                        navigationIconContentColor = WhiteColor
//                    ),
//                    navigationIcon = {
//                        IconButton(onClick = {
//                            corOutlineScope.launch { drawerState.open() }
//                        }) {
//                            Icon( Icons.Rounded.Menu, contentDescription = "Menu Button")
//                        }
//                    }
//                )
//
//            }
//
//        ) {
//            NavHost(navController = navigationController,NavGraph=""{
//
//            }
//        }
//
//    }
//
//}
//
