package ru.otus.otusandroidbasic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.model.FilmItem
import ru.otus.otusandroidbasic.viewholders.FavoriteFilmItemViewHolder

class FavoriteFilmAdapter(
    private val items: List<FilmItem>,
    private val clickListener: FavoriteFilmsClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoriteFilmItemViewHolder(
            layoutInflater.inflate(
                R.layout.item_favorite_film,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = items.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, adapterPosition: Int) {
        if (holder is FavoriteFilmItemViewHolder) {
            val item = items[adapterPosition]
            holder.imageViewLike.setImageResource(
                when (item.isCheck) {
                    true -> R.drawable.ic_baseline_favorite_24
                    else -> R.drawable.ic_baseline_favorite_border_24
                }
            )
            holder.bind(item)
            holder.imageViewLike.setOnClickListener {
                clickListener.onFavoriteClick(item, holder.adapterPosition)
            }
        }
    }

    interface FavoriteFilmsClickListener {
        fun onFavoriteClick(filmItem: FilmItem, adapterPosition: Int)
    }
}