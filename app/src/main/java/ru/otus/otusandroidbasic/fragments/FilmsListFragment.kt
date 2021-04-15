package ru.otus.otusandroidbasic.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.adapters.FilmsAdapter
import ru.otus.otusandroidbasic.dataFilmsList.DataSource
import ru.otus.otusandroidbasic.model.FilmItem

class FilmsListFragment : Fragment() {
    companion object {
        lateinit var recyclerView: RecyclerView
        const val TAG = "FilmsListFragment"
    }

    private var filmsList = DataSource.filmsList
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_films_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recyclerView)
        if (this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.layoutManager = GridLayoutManager(view.context, 3)
        } else {
            recyclerView.layoutManager = GridLayoutManager(view.context, 2)
        }

        recyclerView.adapter = FilmsAdapter(filmsList, object : FilmsAdapter.FilmsClickListener {
            override fun onDetalsClick(filmItem: FilmItem) {
                (activity as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragmentContainer,
                        FilmDetailsFragment.newInstance(filmItem),
                        FilmDetailsFragment.TAG
                    )
                    .addToBackStack(null)
                    .commit()
            }

            override fun onFavoriteClick(filmItem: FilmItem, position: Int) {
                if (filmItem.isCheck) {
                    filmItem.isCheck = false
                    DataSource.likedFilms.remove(filmItem)
                    Snackbar.make(
                        view,
                        "${getString(filmItem.resTit)} delete",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction(R.string.cancel) {
                            DataSource.likedFilms.add(filmItem)
                            filmItem.isCheck = true
                            FilmsListFragment.recyclerView.adapter?.notifyItemChanged(position)
                        }.show()
                } else {
                    filmItem.isCheck = true
                    DataSource.likedFilms.add(filmItem)
                    Snackbar.make(
                        view,
                        "${getString(filmItem.resTit)} add",
                        Snackbar.LENGTH_SHORT
                    )
                        .setAction(R.string.cancel) {
                            DataSource.likedFilms.remove(filmItem)
                            filmItem.isCheck = false
                            recyclerView.adapter?.notifyItemChanged(position)
                        }.show()
                }
                recyclerView.adapter?.notifyItemChanged(position)
            }
        })
    }
}
