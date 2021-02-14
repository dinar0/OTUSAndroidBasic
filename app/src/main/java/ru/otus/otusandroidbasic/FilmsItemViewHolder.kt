package ru.otus.otusandroidbasic

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FilmsItemViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val imageView: ImageView = itemView.findViewById(R.id.imageView)
    val imageViewLike: ImageView = itemView.findViewById(R.id.imageViewLike)
    val titleFilm: TextView = itemView.findViewById(R.id.textView)
    val detailBtn: Button = itemView.findViewById(R.id.button)

    fun bind(item: FilmItem) {
        imageView.setImageResource(item.resImg)
        titleFilm.setText(item.resTit)
    }
}