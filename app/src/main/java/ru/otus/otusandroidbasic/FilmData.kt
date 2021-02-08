package ru.otus.otusandroidbasic

import android.os.Parcelable
import android.widget.TextView
import kotlinx.android.parcel.Parcelize

@Parcelize
class FilmData(val idBtn: Int, val resImg: Int, val resTxt: Int, val resTit: Int): Parcelable{

}