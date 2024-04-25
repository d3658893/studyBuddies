package com.example.studybuddies.data.profile
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.studybuddies.data.rules.ProfileValidator
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class UserProfileViewModel : ViewModel() {

    private val TAG = UserProfileViewModel::class.simpleName
    val db = Firebase.firestore
    var profileUIState = mutableStateOf(ProfileUIState())
    var allValidationsPassed = mutableStateOf(false)
    var profileUpdateInProgress = mutableStateOf(false)
    val universitiesItemList = listOf<String>(
                    "Teesside University",
                    "Oxford University",
                    "University of Birmingham",
                    "School of Music & Art, London"
                )
    val coursesItemList = listOf<String>(
        "Masters in Computer Science",
        "Master in Data Science",
        "Masters in Artificial Intelligence",
        "Masters in Cyber Security",
        "Masters in IT"
    )
    var setModelInProgress by mutableStateOf(false)
    var checkboxChecked by mutableStateOf(false)
    var isSuccessful : Boolean = false

    fun initializeFields() {
        // Perform initialization logic here
        // For example:
//        loadProfile()
//        textFieldValuee = "Updated Text"
//        checkboxChecked = true
    }

    fun onEvent(event: ProfileUIEvent) {
        when (event) {
            is ProfileUIEvent.FirstNameChanged -> {
                profileUIState.value = profileUIState.value.copy(firstName = event.firstName)
            }
            is ProfileUIEvent.LastNameChanged -> {
                profileUIState.value = profileUIState.value.copy(lastName = event.lastName)
            }
            is ProfileUIEvent.EmailChanged -> {
                profileUIState.value = profileUIState.value.copy(email = event.email)
            }

            is ProfileUIEvent.BioChanged -> {
                profileUIState.value = profileUIState.value.copy(bio = event.bio)
            }
            is ProfileUIEvent.SaveButtonClicked -> {
                saveProfileChanges()
            }
            ProfileUIEvent.DeleteProfileClicked -> {
                deleteProfileChanges()
            }

            ProfileUIEvent.UpdateProfileClicked -> {
                updateProfileChanges()
            }

            is ProfileUIEvent.CourseChanged -> {
                profileUIState.value = profileUIState.value.copy(course = event.course)

            }
            is ProfileUIEvent.DOBChanged -> {
                profileUIState.value = profileUIState.value.copy(dateOfBirth = event.dob)

            }
            is ProfileUIEvent.UniversityChanged -> {
                profileUIState.value = profileUIState.value.copy(university = event.uni)
            }

            is ProfileUIEvent.PhoneChanged -> {
                profileUIState.value = profileUIState.value.copy(phone = event.phone)
            }
        }
        validateLoginUIDataWithRules()

    }
    private val isUserLoggedIn: MutableLiveData<Boolean> = MutableLiveData()

    fun checkForActiveSession() {
        if (FirebaseAuth.getInstance().currentUser != null) {
            Log.d(TAG, "Valid session")
            isUserLoggedIn.value = true
        } else {
            Log.d(TAG, "User is not logged in")
            isUserLoggedIn.value = false
        }
    }

    private val emailId: MutableLiveData<String> = MutableLiveData()

    private fun getUserData() {
        FirebaseAuth.getInstance().currentUser?.also {
            it.email?.also { email ->
                emailId.value = email
            }
        }
    }
    fun loadProfile(userProfileUIState:ProfileUIState) {
        getUserData()
        val usersCollection = db.collection("users")
        val query = usersCollection.whereEqualTo("email", emailId.value)
        setModelInProgress = true

        query
            .get()
            .addOnSuccessListener { result ->

                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                for (document in result) {
                    // Assign values from Firestore document to ProfileUIState object
                    userProfileUIState.firstName = document.getString("firstName") ?: ""
                    userProfileUIState.lastName = document.getString("lastName") ?: ""
                    userProfileUIState.email = document.getString("email") ?: ""
                    userProfileUIState.bio = document.getString("bio") ?: ""
                    userProfileUIState.phone = document.getString("phone") ?: ""
                    userProfileUIState.university = document.getString("university") ?: ""
                    userProfileUIState.course = document.getString("course") ?: ""

                    // Print the values for verification
                    Log.d(TAG, "Assigned values: $profileUIState")
                }
                setModelInProgress = false
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
    private fun validateLoginUIDataWithRules() {
        val emailResult = ProfileValidator.validateEmail(profileUIState.value.email.trim())
        val firstNameResult = ProfileValidator.validateFirstName(profileUIState.value.firstName)
        val lastNameResult = ProfileValidator.validateLastName(profileUIState.value.lastName)
        val bioResult = ProfileValidator.validateLastName(profileUIState.value.bio)
//        val dobResult = ProfileValidator.validateDOB(profileUIState.value.dateOfBirth)

        profileUIState.value = profileUIState.value.copy(
            emailError = emailResult.status,
            firstNameError = firstNameResult.status,
            lastNameError = lastNameResult.status,
//            dateOfBirthError = !dobResult.status,
            bioError = bioResult.status
        )

        allValidationsPassed.value = emailResult.status && firstNameResult.status && lastNameResult.status && bioResult.status

    }
    private fun saveProfileChanges() =
        // Check if there are any validation errors
        if (allValidationsPassed.value) {
            // Proceed with saving profile changes
            profileUpdateInProgress.value = true
            // Example: Saving profile changes to Firebase Firestore
            // Add a new document with a generated ID
            val userId = FirebaseAuth.getInstance().currentUser!!.uid

            // Update user profile data in the database
            val users = hashMapOf(
                "firstName" to profileUIState.value.firstName,
                "lastName" to profileUIState.value.lastName,
                "email" to profileUIState.value.email,
                "phone" to profileUIState.value.phone,
                "bio" to profileUIState.value.bio,
                "course" to profileUIState.value.course,
                "university" to profileUIState.value.university,
                "university" to profileUIState.value.university,
                "deleted" to "0",
            )


            // Define a reference to the collection you want to query
            val usersCollection = db.collection("users")

            // Define a query to filter documents based on a specific field value
            val query = usersCollection.whereEqualTo("email", profileUIState.value.email)

            // Perform the query
            query.get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        // Update each matching document
                        val docRef = usersCollection.document(document.id)

                        docRef.update(users as Map<String, Any>)
                            .addOnSuccessListener {
                                Log.d(TAG, "DocumentSnapshot successfully updated!")
                                profileUpdateInProgress.value = false
                                StudyBuddiesAppRouter.navigateTo(Screen.HomeScreen)

                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error updating document", e)
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error getting documents", e)
                }

//            db.collection("users")
//                .document(userId)
//                .set(users)
//                .addOnSuccessListener {documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: $documentReference.id")
//
////                    Toast.makeText(this,"Successfully Added", Toast.LENGTH_SHORT).show()
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }
            // Once update is complete, set profileUpdateInProgress to false
            profileUpdateInProgress.value = false

            // and navigate to a success screen or perform other actions as needed
        } else {
            // Handle validation errors (e.g., display error messages to the user)
//            Log.e(TAG, "Validation errors encountered: ${validationErrors.value}")
        }
    private fun deleteProfileChanges() {
        // Check if there are any validation errors
        if (allValidationsPassed.value) {
            // Proceed with saving profile changes
            profileUpdateInProgress.value = true
            // Example: Saving profile changes to Firebase Firestore
            val user = FirebaseAuth.getInstance().currentUser
            // Update user profile data in the database
            // Once update is complete, set profileUpdateInProgress to false
            // and navigate to a success screen or perform other actions as needed
        } else {
            // Handle validation errors (e.g., display error messages to the user)
//            Log.e(TAG, "Validation errors encountered: ${validationErrors.value}")
        }
    }
    private fun updateProfileChanges() {
        // Check if there are any validation errors
        if (allValidationsPassed.value) {
            // Proceed with saving profile changes
            profileUpdateInProgress.value = true
            // Example: Saving profile changes to Firebase Firestore
            val user = FirebaseAuth.getInstance().currentUser
            // Update user profile data in the database
            // Once update is complete, set profileUpdateInProgress to false
            // and navigate to a success screen or perform other actions as needed
        } else {
            // Handle validation errors (e.g., display error messages to the user)
//            Log.e(TAG, "Validation errors encountered: ${validationErrors.value}")
        }
    }


}
