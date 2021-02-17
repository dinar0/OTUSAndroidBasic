package ru.otus.otusandroidbasic

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class FavoriteFilmItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageViewLike: ImageView = itemView.findViewById(R.id.imageViewLike)
    val titleFilm: TextView = itemView.findViewById(R.id.textView)
    //val favoritesEmpty : TextView = itemView.findViewById(R.id.favorites_empty)

    fun bind(item: FilmItem) {
        imageView.setImageResource(item.resImg)
        titleFilm.setText(item.resTit)

        /* imageViewLike.setImageResource(
             when (item.isCheck) {
                 true -> R.drawable.ic_baseline_favorite_24
                 else -> R.drawable.ic_baseline_favorite_border_24
             })*/
    }
}