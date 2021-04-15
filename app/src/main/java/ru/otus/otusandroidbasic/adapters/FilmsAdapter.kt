package ru.otus.otusandroidbasic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.model.FilmItem
import ru.otus.otusandroidbasic.viewholders.FilmsItemViewHolder

class FilmsAdapter(
    private val items: List<FilmItem>,
    private val clickListener: FilmsClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsItemViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FilmsItemViewHolder) {
            val item = items[position]
            holder.imageViewLike.setImageResource(
                when (item.isCheck) {
                    true -> R.drawable.ic_baseline_favorite_24
                    else -> R.drawable.ic_baseline_favorite_border_24
                }
            )
            holder.bind(item)

            holder.imageView.setOnClickListener {
                clickListener.onDetalsClick(item)
            }
            holder.imageViewLike.setOnClickListener {
                clickListener.onFavoriteClick(item, position)
            }
        }
    }

    interface FilmsClickListener {
        fun onDetalsClick(filmItem: FilmItem)
        fun onFavoriteClick(filmItem: FilmItem, position: Int)
    }
}