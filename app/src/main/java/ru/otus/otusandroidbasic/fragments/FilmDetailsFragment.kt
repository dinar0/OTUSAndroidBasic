package ru.otus.otusandroidbasic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import ru.otus.otusandroidbasic.R
import ru.otus.otusandroidbasic.model.FilmItem

class FilmDetailsFragment : Fragment() {

    companion object {
        const val TAG = "filmDetailsFragment"
        const val EXTRA_TITLE = "EXTRA_TITLE"
        fun newInstance(filmItem: FilmItem) = FilmDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(EXTRA_TITLE, filmItem)
            }
        }
    }

    private lateinit var film: FilmItem
    private lateinit var imageView: ImageView
    private lateinit var toolbar: Toolbar
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_film_details, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        imageView = view.findViewById(R.id.imageView)
        textView = view.findViewById(R.id.text)
        val bundle = arguments
        film = bundle?.getParcelable(EXTRA_TITLE)!!
        imageView.setImageResource(film.resImg)
        toolbar.title = getText(film.resTit)
        textView.text = getText(film.resTxt)
        return view
    }
}