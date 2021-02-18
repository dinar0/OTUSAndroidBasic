package ru.otus.otusandroidbasic

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilm: AppCompatActivity() {
     companion object {
        const val FAVORITE_FILM = "FAVORITE_FILM"

    }
    private val recyclerViewLike by lazy { findViewById<RecyclerView>(R.id.recyclerViewLike) }
    private val favoritesEmpty by lazy { findViewById<TextView>(R.id.favorites_empty) }
    private var LikedFilmsItem = arrayListOf<FilmItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_films)
          intent.getParcelableArrayListExtra<FilmItem>(FAVORITE_FILM)?.let{
             if(it.isEmpty()) favoritesEmpty.isVisible=true
               else favoritesEmpty.isVisible=false
                 LikedFilmsItem=it

     }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewLike.layoutManager = layoutManager
        recyclerViewLike.adapter = FavoriteFilmAdapter(LikedFilmsItem, object : FavoriteFilmAdapter.FavoriteFilmsClickListener {

            override fun onFavoriteClick(filmItem: FilmItem,aposition :Int) {
                if (filmItem.isCheck) {
                    LikedFilmsItem.removeAt(aposition)
                    if(LikedFilmsItem.isEmpty()) favoritesEmpty.isVisible=true
                    else favoritesEmpty.isVisible=false
                    filmItem.isCheck = false
                }


                recyclerViewLike.adapter?.notifyItemRemoved(aposition)
            }
        })
    }
    /*  private fun favoritesIsEmpty() {
        if (LikedFilms==null) {
            favoritesEmpty.isVisible = true
        } else {
            favoritesEmpty.isVisible = false
        }
    }*/
}
