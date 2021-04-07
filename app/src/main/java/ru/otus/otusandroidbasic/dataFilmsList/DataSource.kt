package ru.otus.otusandroidbasic.dataFilmsList

import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.model.FilmItem

object DataSource {
    var filmsList: MutableList<FilmItem> = arrayListOf(
        FilmItem(R.drawable.g, R.string.G_text, R.string.gentl),
        FilmItem(R.drawable.l, R.string.L_text, R.string.cart),
        FilmItem(R.drawable.r, R.string.R_text, R.string.rock),
        FilmItem(R.drawable.revolver, R.string.Revolver_text, R.string.revolver),
        FilmItem(R.drawable.snatch, R.string.snatch_text, R.string.snatch)
    )
    var likedFilms: MutableList<FilmItem> = arrayListOf()
}