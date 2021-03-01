package ru.otus.otusandroidbasic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FilmItem(

    val resImg: Int,
    val resTxt: Int,
    val resTit: Int,
    var isCheck: Boolean = false,
    val comment: String? = null
): Parcelable



