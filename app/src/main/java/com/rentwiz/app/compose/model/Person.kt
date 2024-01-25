package com.rentwiz.app.compose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: Int,
    val name: String,
    val age: Int
): Parcelable
