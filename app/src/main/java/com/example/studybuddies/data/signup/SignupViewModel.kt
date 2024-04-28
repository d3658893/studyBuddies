package com.example.studybuddies.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.studybuddies.data.RegistrationUIState
import com.example.studybuddies.data.rules.Validator
import com.example.studybuddies.navigation.Screen
import com.example.studybuddies.navigation.StudyBuddiesAppRouter
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


class SignupViewModel : ViewModel() {

    private val TAG = SignupViewModel::class.simpleName


    var registrationUIState = mutableStateOf(RegistrationUIState())

    var allValidationsPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)

    fun onEvent(event: SignupUIEvent) {
        when (event) {
            is SignupUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }

            is SignupUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }

            is SignupUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }


            is SignupUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }

            is SignupUIEvent.RegisterButtonClicked -> {
                signUp()
            }

            is SignupUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
        validateDataWithRules()
    }


    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        createUserInFirebase(
            email = registrationUIState.value.email,
            password = registrationUIState.value.password,
            fName = registrationUIState.value.firstName,
            lName = registrationUIState.value.lastName
        )
    }

    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )

        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )

        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email.trim()
        )


        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )

        val privacyPolicyResult = Validator.validatePrivacyPolicyAcceptance(
            statusValue = registrationUIState.value.privacyPolicyAccepted
        )


        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult= $fNameResult")
        Log.d(TAG, "lNameResult= $lNameResult")
        Log.d(TAG, "emailResult= $emailResult")
        Log.d(TAG, "passwordResult= $passwordResult")
        Log.d(TAG, "privacyPolicyResult= $privacyPolicyResult")

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailError = emailResult.status,
            passwordError = passwordResult.status,
            privacyPolicyError = privacyPolicyResult.status
        )


        allValidationsPassed.value = fNameResult.status && lNameResult.status &&
                emailResult.status && passwordResult.status && privacyPolicyResult.status

    }


    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }


    private fun createUserInFirebase(email: String, password: String, fName:String,lName:String) {

        signUpInProgress.value = true
        var isSuccessful : Boolean = false
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email.trim(), password)
            .addOnCompleteListener{task ->
                if(task.isSuccessful){
                    FirebaseAuth
                        .getInstance().currentUser?.sendEmailVerification()
                        ?.addOnSuccessListener {
                            Log.d(TAG, "Inside_OnCompleteListener")
                            Log.d(TAG, " isSuccessful = ${task.isSuccessful}")
                            isSuccessful = task.isSuccessful
                            signUpInProgress.value = false
//                            Toast.makeText(this,"Please Verify your Email!",Toast.LENGTH_LONG).show()
                        }
                        ?.addOnFailureListener {
                            isSuccessful = !task.isSuccessful
                            signUpInProgress.value = false
                        }
                }

            }
            .addOnFailureListener {
                Log.d(TAG, "Inside_OnFailureListener")
                Log.d(TAG, "Exception= ${it.message}")
                Log.d(TAG, "Exception= ${it.localizedMessage}")
            }
        createUserProfile(email,password,fName,lName,isSuccessful)


    }
    private fun createUserProfile(email: String, password: String, fName:String,lName:String,isSuccessful:Boolean) {
        Log.d(TAG,"sds$isSuccessful")

            val db = Firebase.firestore
            // Define the data to be set in the new document
            val userData = hashMapOf(
                "firstName" to fName,
                "lastName" to lName,
                "email" to email,
                "password" to password
            )
            Log.d(TAG, "inside the new query")
            // Set the data in the new document
            db.collection("users").
            //        document().
            add(userData)
                .addOnSuccessListener {
                    Log.d(TAG, "DocumentSnapshot successfully written!")
                    StudyBuddiesAppRouter.navigateTo(Screen.HomeScreen)

                }
                .addOnFailureListener {
                    Log.w(TAG, "Error writing document")
                }
        }



    }
