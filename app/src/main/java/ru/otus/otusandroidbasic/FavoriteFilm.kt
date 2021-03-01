package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilm : AppCompatActivity() {
    companion object {
        const val FAVORITE_FILM = "FAVORITE_FILM"

    }

    private val recyclerViewLike by lazy { findViewById<RecyclerView>(R.id.recyclerViewLike) }
    private val favoritesEmpty by lazy { findViewById<TextView>(R.id.favorites_empty) }
    private var LikedFilmsItem = arrayListOf<FilmItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_films)
        intent.getParcelableArrayListExtra<FilmItem>(FAVORITE_FILM)?.let {
            LikedFilmsItem = it
            favoritesEmpty.isVisible = LikedFilmsItem.isEmpty()
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewLike.layoutManager = layoutManager
        recyclerViewLike.adapter = FavoriteFilmAdapter(
            LikedFilmsItem,
            object : FavoriteFilmAdapter.FavoriteFilmsClickListener {

                override fun onFavoriteClick(filmItem: FilmItem, aposition: Int) {
                    if (filmItem.isCheck) {
                        LikedFilmsItem.removeAt(aposition)
                        favoritesEmpty.isVisible = LikedFilmsItem.isEmpty()
                        filmItem.isCheck = false
                    }
                    recyclerViewLike.adapter?.notifyItemRemoved(aposition)
                }
            })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(FAVORITE_FILM, LikedFilmsItem)
    }

    override fun onBackPressed() {
        setResult(
            RESULT_OK,
            Intent().apply {
                putParcelableArrayListExtra(FAVORITE_FILM, LikedFilmsItem)
            },
        )
        finish()
    }
}
