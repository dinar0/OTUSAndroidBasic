package ru.otus.otusandroidbasic

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(private val items: List<FilmItem>, private val clickListener: FilmsClickListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_FILMS = 0
        const val VIEW_TYPE_HEADER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // xml -> View = inflate
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_film, parent, false)
        return if (viewType == VIEW_TYPE_FILMS) {
            val view = layoutInflater.inflate(R.layout.item_film, parent, false)
            FilmsItemViewHolder(view)
        } else {
            val view = layoutInflater.inflate(R.layout.item_film_header, parent, false)
            FilmHeaderViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int) =
        if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_FILMS

    override fun getItemCount() = items.size + 1 // +1 = header

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is FilmsItemViewHolder) {
                val item = items[position - 1]
                holder.bind(item)

                holder.detailBtn.setOnClickListener {
                    clickListener.onDetalsClick(item)
                }

                holder.imageViewLike.setOnClickListener {
                    clickListener.onFavoriteClick(item)
                }

            }
        }

            interface FilmsClickListener {
                fun onDetalsClick(filmItem: FilmItem)
                fun onFavoriteClick(filmItem: FilmItem)

            }
 }