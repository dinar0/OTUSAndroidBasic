package ru.otus.otusandroidbasic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class FilmAdapter(
    private val items: List<FilmItem>,
    private val clickListener: FilmAdapter.FilmsClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_FILMS = 0
        // const val VIEW_TYPE_HEADER = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        // xml -> View = inflate
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsItemViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))

    }

    /* override fun getItemViewType(position: Int) =
         if (position == 0) VIEW_TYPE_HEADER else VIEW_TYPE_FILMS
 */
    override fun getItemCount() = items.size  // +1 = header

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

            holder.detailBtn.setOnClickListener {
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