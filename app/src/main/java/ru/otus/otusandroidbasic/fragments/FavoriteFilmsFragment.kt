package ru.otus.otusandroidbasic.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.adapters.FavoriteFilmAdapter
import ru.otus.otusandroidbasic.dataFilmsList.DataSource.likedFilms
import ru.otus.otusandroidbasic.model.FilmItem

class FavoriteFilmsFragment : Fragment() {
    companion object {
        const val FAVORITE_FILMS = "FAVORITE_FILMS"
        lateinit var recyclerViewLike: RecyclerView
        // private val recyclerViewLike by lazy { view.findViewById<RecyclerView>(R.id.recyclerViewLike) }

        // private var likedFilmsItem = arrayListOf<FilmItem>()
    }

    lateinit var favoritesEmpty: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_films, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerViewLike = view.findViewById(R.id.recyclerViewLike)
        favoritesEmpty = view.findViewById(R.id.favoritesEmpty)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerViewLike.layoutManager = LinearLayoutManager(view.context)
        } else {
            recyclerViewLike.layoutManager = LinearLayoutManager(view.context)
        }
        recyclerViewLike.adapter =
            likedFilms?.let {
                FavoriteFilmAdapter(it, object : FavoriteFilmAdapter.FavoriteFilmsClickListener {
                    override fun onFavoriteClick(filmItem: FilmItem, adapterPosition: Int) {
                        (activity as? FavoriteFilmsClickedListener)?.onFavoriteClick(
                            filmItem,
                            adapterPosition
                        )
                        CheckFavoriteListisEmpty()
                    }
                })
            }
    }

    interface FavoriteFilmsClickedListener {
        fun onFavoriteClick(filmItem: FilmItem, adapterPosition: Int)
    }

    override fun onResume() {
        super.onResume()
        CheckFavoriteListisEmpty()
    }

    private fun CheckFavoriteListisEmpty() {
        if (likedFilms.isEmpty()) {
            favoritesEmpty.visibility = VISIBLE
        } else {
            favoritesEmpty.visibility = INVISIBLE
        }
    }
}