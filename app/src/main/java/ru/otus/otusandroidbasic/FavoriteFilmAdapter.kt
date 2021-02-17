package ru.otus.otusandroidbasic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilmAdapter (private val items: List<FilmItem>, private val clickListener: FavoriteFilmsClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // xml -> View = inflate
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteFilmItemViewHolder(layoutInflater.inflate(R.layout.item_favoritefilm, parent, false))

    }

     override fun getItemCount() = items.size  // +1 = header

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteFilmItemViewHolder) {
            val item = items[position]
            holder.imageViewLike.setImageResource(
                when (item.isCheck) {
                    true -> R.drawable.ic_baseline_favorite_24
                    else -> R.drawable.ic_baseline_favorite_border_24
                })

            holder.bind(item)



            holder.imageViewLike.setOnClickListener {

                clickListener.onFavoriteClick(item,position)
            }

        }
    }

    interface FavoriteFilmsClickListener {

        fun onFavoriteClick(filmItem: FilmItem, position: Int)
    }
}