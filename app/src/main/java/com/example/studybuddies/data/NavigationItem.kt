package com.example.studybuddies.data

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.studybuddies.navigation.Screen

data class NavigationItem(
    val title: String,
    val description: String,
    val itemId: Screen,
    val icon: ImageVector
)