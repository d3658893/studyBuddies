package com.example.studybuddies.screens

//noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material3.rememberSc
//import com.example.studybuddies.components.AppToolbar
//import com.example.studybuddies.components.NavigationDrawerBody
//import com.example.studybuddies.components.NavigationDrawerHeader
//import androidx.compose.material.rememberScaffoldState

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddies.components.AppToolbar
import com.example.studybuddies.components.NavigationDrawerBody
import com.example.studybuddies.components.NavigationDrawerHeader
import com.example.studybuddies.data.home.HomeViewModel
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.ui.theme.Primary
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {


    homeViewModel.getUserData()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
        ModalDrawerSheet {
            NavigationDrawerHeader(homeViewModel.emailId.value)
            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
                onNavigationItemClicked = { it ->
                    Log.d("ComingHere","inside_NavigationItemClicked")
                    Log.d("ComingHere","${it.itemId} ${it.title}")
                    val selectedItemId = it.itemId
                    StudyBuddiesAppRouter.navigateTo(it.itemId)
                    // Usage
//                    val screen = try {
//                        val clazz = Class.forName("com.example.studybuddies.Screen\$${selectedItemId}")
//                        clazz.kotlin.objectInstance as Screen
//                    } catch (e: ClassNotFoundException) {
//                        null
//                    }
//                    Log.d("screen", it.toString())
//                    screen?.let { StudyBuddiesAppRouter.navigateTo(it) }
//                    Log.d("ComingHere","inside_NavigationItemClicked")
//                    Log.d("ComingHere","${it.itemId} ${it.title}")
//                    StudyBuddiesAppRouter.navigateTo(SelectedItemId)

                })
        }
    }){
    Scaffold(
        topBar = {

            AppToolbar(
                toolbarTitle = "Study Buddies App",
                logoutButtonClicked = {
                    homeViewModel.logout()
                },
                navigationIconClicked = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
            )
        },
        content = { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
                    .padding(paddingValues)
            ) {
                Text("Hey its Home Screen")
            }
        }
    )
}


}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}