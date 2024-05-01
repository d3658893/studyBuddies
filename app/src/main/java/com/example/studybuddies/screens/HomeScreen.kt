package com.example.studybuddies.screens

import android.content.ContentValues.TAG
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studybuddies.components.AppToolbar
import com.example.studybuddies.components.NavigationDrawerBody
import com.example.studybuddies.components.NavigationDrawerHeader
import com.example.studybuddies.components.PostCards
import com.example.studybuddies.data.PostDataItems
import com.example.studybuddies.data.home.HomeViewModel
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.example.studybuddies.ui.theme.Primary
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch


@OptIn(DelicateCoroutinesApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = viewModel()) {
    homeViewModel.getUserData()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scrollState = rememberScrollState()
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_gallery)
    val bitmap = remember { mutableStateOf(img) }
    var isUploadingOnLoad by remember { mutableStateOf(false) }
    var postDataItemsList by remember { mutableStateOf<List<PostDataItems>>(emptyList()) }

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

                @Composable
                fun displayPosts(scrollState:ScrollState){
                    // Display PostCards
//                    Box(
//                        modifier = Modifier.fillMaxSize().padding(20.dp),
////                      contentAlignment = Alignment.TopCenter
//                    ) {
//                        Surface(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .background(Color.White)
//                                .padding(28.dp)
//                        ) {
                            Column(modifier = Modifier.height(250.dp).width(320.dp).padding(20.dp)
                                .verticalScroll(scrollState),
                                verticalArrangement = Arrangement.Center
                            ) {
                                for (postDataItem in postDataItemsList) {


//                        bitmap.value?.let { loadedBitmap ->
                                    PostCards(
                                        title = postDataItem.title,
                                        description = postDataItem.description,
                                        author = postDataItem.author,
                                        imageURI = postDataItem.imageURI
                                    )
//                        }
                                }
//                            }
//                        }
                    }
                }
                if (!isUploadingOnLoad) {
                    val db = Firebase.firestore
                    db.collection("postData")
                        .get()
                        .addOnSuccessListener { querySnapshot ->

                            val list = mutableListOf<PostDataItems>()
                            for (document in querySnapshot) {
                                val imageURI = document.getString("ImageURI") ?: ""
                                val title = document.getString("Title") ?: ""
                                val description = document.getString("Description") ?: ""
                                val author = document.getString("CreatedBy") ?: ""

                                list.add(PostDataItems(title,description,author, imageURI))
                            }
                            postDataItemsList = list.toList()
                            isUploadingOnLoad = true
                            Log.d(TAG, "Successful inside documents.")
                        }
                        .addOnFailureListener { exception ->
                            Log.w(TAG, "Error getting documents.", exception)
                            isUploadingOnLoad = false
                        }

                }
                displayPosts(scrollState=scrollState)
            }
        }
    )


    }

}


fun GetImageBitmap(imageURI:String):Bitmap{
    val img: Bitmap = BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_gallery)
    var bitmap = img
    //getImage
    val storageRef = FirebaseStorage.getInstance().reference.child("postImages/android.graphics.Bitmap@5fbb1d7")
//    val localfile = File.createTempFile("tempImage","jpeg")
//        storageRef.getFile(localfile).addOnSuccessListener {
//
//            bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
//            Log.d("SuccessImageGet", "$bitmap")
//        }.addOnFailureListener { exception ->
//            // Handle failure
//            Log.d("FailedImageGet", "$exception")
//        }
    storageRef.listAll()
        .addOnSuccessListener { listResult ->
            if (listResult.items.isNotEmpty()) {
                val sortedItems = listResult.items
                val storageRef = sortedItems[0]
//                    val storageRef = listResult.items[0]
                // Download the image directly as a byte array
                storageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener { bytes ->
                    // Convert the byte array to a bitmap
                    val downloadedBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    // Update the state with the downloaded bitmap
                    bitmap = downloadedBitmap
                }.addOnFailureListener {
                    // Handle failure
                }
            }
        }
    return bitmap
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}