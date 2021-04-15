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
import com.google.android.material.snackbar.Snackbar
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.adapters.FavoriteFilmAdapter
import ru.otus.otusandroidbasic.dataFilmsList.DataSource.likedFilms
import ru.otus.otusandroidbasic.model.FilmItem

class FavoriteFilmsFragment : Fragment() {
    companion object {
        const val FAVORITE_FILMS = "FAVORITE_FILMS"
        lateinit var recyclerViewLike: RecyclerView
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
                        if (filmItem.isCheck) {
                            filmItem.isCheck = false
                            likedFilms.remove(filmItem)
                            FavoriteFilmsFragment.recyclerViewLike.adapter?.notifyItemRemoved(adapterPosition)
                            Snackbar.make(
                                view,
                                "${getString(filmItem.resTit)} delete",
                                Snackbar.LENGTH_SHORT
                            )
                                .setAction(R.string.cancel) {
                                    likedFilms.add(filmItem)
                                    filmItem.isCheck = true
                                    FavoriteFilmsFragment.recyclerViewLike.adapter?.notifyItemChanged(
                                        adapterPosition
                                    )
                                }.show()
                        } else {
                            filmItem.isCheck = true
                            likedFilms.add(filmItem)
                        }
                        CheckFavoriteListisEmpty()
                    }
                })
            }
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