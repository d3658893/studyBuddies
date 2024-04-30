package com.example.studybuddies.data.post

import android.graphics.Bitmap

sealed class PostUIEvent{

    data class TitleChanged(val title:String): PostUIEvent()
    data class DescriptionChanged(val description: String) : PostUIEvent()
    data class ImageChanged(val image: Bitmap) : PostUIEvent()

    object SaveButtonClicked : PostUIEvent()
}