package com.example.studybuddies.data.post

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studybuddies.data.rules.postValidator
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import java.io.ByteArrayOutputStream

class PostViewModel : ViewModel() {

    private val TAG = PostViewModel::class.simpleName
    val db = Firebase.firestore
    var postUIState = mutableStateOf(PostUIState())
    var allValidationsPassed = mutableStateOf(false)
    var postInProgress = mutableStateOf(false)
    var latestImageName =mutableStateOf("")

    fun onEvent(event: PostUIEvent) {
        when (event) {
            is PostUIEvent.TitleChanged -> {
                postUIState.value = postUIState.value.copy(
                    title = event.title
                )
            }

            is PostUIEvent.DescriptionChanged -> {
                postUIState.value = postUIState.value.copy(
                    description = event.description
                )
            }

            is PostUIEvent.ImageChanged -> {
                postUIState.value = postUIState.value.copy(
                    bitmap = event.image
                )
            }

            is PostUIEvent.SaveButtonClicked -> {
                submitPost(
                    title = postUIState.value.title,
                    description = postUIState.value.description
                )
            }
        }
        validatePostUIDataWithRules()
    }
    private fun validatePostUIDataWithRules() {
        val titleResult = postValidator.validateTitle(
            title = postUIState.value.title.trim()
        )

        val descriptionResult = postValidator.validateDescription(
            description = postUIState.value.description.trim()
        )

        postUIState.value = postUIState.value.copy(
            titleError = titleResult.status,
            descriptionError = descriptionResult.status
        )

        allValidationsPassed.value = titleResult.status && descriptionResult.status

    }
    private fun getUserName() {
        val usersCollection = db.collection("users")
        val query = usersCollection.whereEqualTo("email", emailId.value)

        query
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    // Assign values from Firestore document to ProfileUIState object
                    postUIState.value.Author =
                        "${document.getString("firstName") ?: ""} ${document.getString("lastName") ?: ""}"
                    // Print the values for verification
                    Log.d(TAG, "Assigned values: ${postUIState.value.Author}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
    private fun submitPost(title: String, description: String) {
        getUserData()
        postInProgress.value = true
        val title_ = title
        val description_ = description
        val bitmap = postUIState.value.bitmap
        val userName = postUIState.value.Author
        var isImageUploaded = false
        var isImageFetched = false

        Log.d("POST Values", "$title $description $bitmap $userName")

        val storageRef = Firebase.storage.reference
        val imageRef = storageRef.child("postImages/${bitmap}")

        val baos = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val imageData = baos.toByteArray()

        imageRef.putBytes(imageData).addOnSuccessListener {
            Log.d("ImageUploadSuccess: ", "Successful")
            fetchImageName(title_=title_,description_=description_,userName=userName)
        }.addOnFailureListener{
            Log.d("ImageUploadFailure: ", "Failed")
        }
    }
    private fun fetchImageName(title_:String,description_:String,userName:String) {
        val storageRef = Firebase.storage.reference
            val imageFolderRef = storageRef.child("postImages/")
            imageFolderRef.listAll()
                .addOnSuccessListener { listResult ->
                    // Filter out directories, if any
                    val imageItems = listResult.items.filter { !it.name.endsWith("/") }

                    if (imageItems.isNotEmpty()) {
                        // Sort items based on name (assuming name contains creation time)
                        val latestImageItem = imageItems.maxByOrNull { it.name }

                        // Get the name of the latest image
                        latestImageName.value = latestImageItem?.name ?: ""

                        // Use the latest image name as needed
                        Log.d("LatestImageName: ", latestImageName.value)
                        savePost(title_, description_, latestImageName.value, userName)

                        } else {
                            Log.d("LatestImageName", "No images found in the folder")
                        }
                    }
                        .addOnFailureListener { exception ->
                            // Handle failure
                            Log.e("LatestImageName", "Failed to list items: $exception")
                        }
    }

    private fun savePost(title_:String, description_:String, latestImageName:String, userName:String){

            val db = Firebase.firestore
            // Define the data to be set in the new document
            val userData = hashMapOf(
                "Title" to title_,
                "Description" to description_,
                "ImageURI" to latestImageName,
                "CreatedBy" to userName
            )
            Log.d(TAG, "inside the new query")
            // Set the data in the new document
            db.collection("postData").
            add(userData)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                    StudyBuddiesAppRouter.navigateTo(Screen.HomeScreen)

                }
                .addOnFailureListener {exception ->
                    Log.w(TAG, "Error writing document $exception")
                }
    }

    private val emailId: MutableLiveData<String> = MutableLiveData()
    private fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
        getUserName()
    }
}





