package com.example.studybuddies.data.profile

import com.google.firebase.Timestamp

data class ProfileUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var bio: String = "",
    var phone: String = "",
    var dateOfBirth: Timestamp? = null,
    var university: String = "",
    var course: String = "",
    var createdAt: Timestamp? = null, // Nullable Timestamp
    var createdBy: String = "",
    var updatedAt: Timestamp? = null, // Nullable Timestamp
    var updatedBy: String = "",
    var isDeleted: Boolean = false,

    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var bioError: Boolean = false,
    var dateOfBirthError: Boolean = false,
)


