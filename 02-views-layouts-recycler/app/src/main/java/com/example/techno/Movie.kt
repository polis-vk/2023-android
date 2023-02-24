package com.example.techno

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val icon: Int,
    val title: String,
    val description: String,
): Parcelable