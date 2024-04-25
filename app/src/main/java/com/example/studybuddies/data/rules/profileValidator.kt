package com.example.studybuddies.data.rules

import com.google.firebase.Timestamp


object ProfileValidator {

    data class ValidationResult(
        val status: Boolean = false
    )
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            firstName.isNotEmpty() && firstName.length >= 2
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            lastName.isNotEmpty() && lastName.length >= 2
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = Regex("^\\S+@\\S+\\.\\S+$")
        return ValidationResult(email.matches(emailRegex))
    }
    fun validateDOB(dateOfBirth: Timestamp?): ValidationResult {
        return ValidationResult(true)
    }

    fun validateProfilePictureURL(profilePictureURL: String): ValidationResult {
        return ValidationResult(profilePictureURL.isNotEmpty())
    }
}

