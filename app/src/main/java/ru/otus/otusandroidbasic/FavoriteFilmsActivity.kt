package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilmsActivity : AppCompatActivity() {
    companion object {
        const val FAVORITE_FILMS = "FAVORITE_FILMS"
    }
    private val recyclerViewLike by lazy { findViewById<RecyclerView>(R.id.recyclerViewLike) }
    private val favoritesEmpty by lazy { findViewById<TextView>(R.id.favoritesEmpty) }
    private var likedFilmsItem = arrayListOf<FilmItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_films)
        intent.getParcelableArrayListExtra<FilmItem>(FAVORITE_FILMS)?.let {
            likedFilmsItem = it
            favoritesEmpty.isVisible = likedFilmsItem.isEmpty()
        }
        initRecyclerView()
    }
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewLike.layoutManager = layoutManager
        recyclerViewLike.adapter = FavoriteFilmAdapter(
            likedFilmsItem,
            object : FavoriteFilmAdapter.FavoriteFilmsClickListener {
                override fun onFavoriteClick(filmItem: FilmItem, aposition: Int) {
                    if (filmItem.isCheck) {
                        likedFilmsItem.removeAt(aposition)
                        favoritesEmpty.isVisible = likedFilmsItem.isEmpty()
                        filmItem.isCheck = false
                    }
                    recyclerViewLike.adapter?.notifyItemRemoved(aposition)
                }
            })
        val ItemDecoration=DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recyclerViewLike.addItemDecoration(ItemDecoration)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(FAVORITE_FILMS, likedFilmsItem)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    override fun finish() {
        setResult(
            RESULT_OK,
            Intent().apply {
                putParcelableArrayListExtra(FAVORITE_FILMS, likedFilmsItem)
            },
        )
        super.finish()
    }
}
