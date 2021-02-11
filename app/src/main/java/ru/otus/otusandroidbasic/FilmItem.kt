package ru.otus.otusandroidbasic

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilmItem(
    val idBtn: Int,
    val resImg: Int,
    val resTxt: Int,
    val resTit: Int,
    val isCheck: Boolean= false,
    val comment: String?= null)
    : Parcelable{}


