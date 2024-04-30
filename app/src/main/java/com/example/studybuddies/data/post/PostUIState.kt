package com.example.studybuddies.data.post

import android.graphics.Bitmap

data class PostUIState(
    var title:String = "",
    var description:String = "",
    var bitmap: Bitmap? = null,
    var Author: String = "",

    var titleError:Boolean = false,
    var descriptionError: Boolean = false,
    var bitmapError: Bitmap? = null

    )