package ru.otus.otusandroidbasic.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.model.FilmItem

class FavoriteFilmItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageViewLike: ImageView = itemView.findViewById(R.id.imageViewLike)
    val titleFilm: TextView = itemView.findViewById(R.id.textView)

    fun bind(item: FilmItem) {
        imageView.setImageResource(item.resImg)
        titleFilm.setText(item.resTit)
    }
}