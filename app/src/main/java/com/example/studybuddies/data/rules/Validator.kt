package com.example.studybuddies.data.rules

object Validator {


    fun validateFirstName(fName: String): ValidationResult {
        return ValidationResult(
            (!fName.isNullOrEmpty() && fName.length >= 2)
        )

    }

    fun validateLastName(lName: String): ValidationResult {
        return ValidationResult(
            (!lName.isNullOrEmpty() && lName.length >= 2)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        val emailRegex = Regex("^\\S+@\\S+\\.\\S+$")
        return if (email.matches(emailRegex)) {
            ValidationResult(true)
        }else{
            ValidationResult(false)
        }
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrEmpty() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicyAcceptance(statusValue:Boolean):ValidationResult{
        return ValidationResult(
            statusValue
        )
    }
    data class ValidationResult(
        //test
        val status: Boolean = false
    )

}










