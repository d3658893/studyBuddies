package com.example.studybuddies.data.rules

object postValidator {

    fun validateTitle(title: String): ValidationResult {
        return ValidationResult(
            (title.isNotEmpty() && title.length >= 5)
        )

    }
    fun validateDescription(description: String): ValidationResult {
        return ValidationResult(
            (description.isNotEmpty() && description.length >= 2)
        )
    }

    data class ValidationResult(
        //test
        val status: Boolean = false
    )

}
