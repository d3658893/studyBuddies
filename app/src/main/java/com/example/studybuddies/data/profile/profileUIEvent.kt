package com.example.studybuddies.data.profile

import com.google.firebase.Timestamp

sealed class ProfileUIEvent {
    data class FirstNameChanged(val firstName: String) : ProfileUIEvent()
    data class LastNameChanged(val lastName: String) : ProfileUIEvent()
    data class EmailChanged(val email: String) : ProfileUIEvent()
    data class PhoneChanged(val phone: String) : ProfileUIEvent()
    data class BioChanged(val bio: String) : ProfileUIEvent()
    data class DOBChanged(val dob: Timestamp) : ProfileUIEvent()
    data class UniversityChanged(val uni: String) : ProfileUIEvent()
    data class CourseChanged(val course: String) : ProfileUIEvent()
    object SaveButtonClicked : ProfileUIEvent()
    object UpdateProfileClicked : ProfileUIEvent()
    object DeleteProfileClicked : ProfileUIEvent()
}
