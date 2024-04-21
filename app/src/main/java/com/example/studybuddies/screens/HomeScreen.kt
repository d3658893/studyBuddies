package com.example.studybuddies.screens

//noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material3.rememberScaffoldState
//import com.example.studybuddies.components.AppToolbar
//import com.example.studybuddies.components.NavigationDrawerBody
//import com.example.studybuddies.components.NavigationDrawerHeader
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddies.data.home.HomeViewModel

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {

//    val scaffoldState = rememberScaffoldState()
//    val coroutineScope = rememberCoroutineScope()
//
//    homeViewModel.getUserData()
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            AppToolbar(toolbarTitle = stringResource(id = R.string.home),
//                logoutButtonClicked = {
//                    homeViewModel.logout()
//                },
//                navigationIconClicked = {
//                    coroutineScope.launch {
//                        scaffoldState.drawerState.open()
//                    }
//                }
//            )
//        },
//        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
//        drawerContent = {
//            NavigationDrawerHeader(homeViewModel.emailId.value)
//            NavigationDrawerBody(navigationDrawerItems = homeViewModel.navigationItemsList,
//                onNavigationItemClicked = {
//                    Log.d("ComingHere","inside_NavigationItemClicked")
//                    Log.d("ComingHere","${it.itemId} ${it.title}")
//                })
//        }
//
//    ) { paddingValues ->
//
//        Surface(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color.White)
//                .padding(paddingValues)
//        ) {
//            Column(modifier = Modifier.fillMaxSize()) {
//
//
//            }
//
//        }
//    }
//}
//    paddingValues ->
    Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding()
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Text("Hey its Home Screen")

            }

        }
}
@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}