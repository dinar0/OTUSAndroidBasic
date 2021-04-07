package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import ru.otus.otusandroidbasic.dataFilmsList.DataSource.likedFilms
import ru.otus.otusandroidbasic.fragments.FavoriteFilmsFragment
import ru.otus.otusandroidbasic.fragments.FilmDetailsFragment
import ru.otus.otusandroidbasic.fragments.FilmsListFragment
import ru.otus.otusandroidbasic.model.FilmItem


class MainActivity : AppCompatActivity(),
    FilmsListFragment.FilmsClickedListener,
    FavoriteFilmsFragment.FavoriteFilmsClickedListener {
    private val fragmentContainer by lazy { findViewById<FrameLayout>(R.id.fragmentContainer) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initBottomNavigation()
        initClickListeners()
        if (supportFragmentManager.backStackEntryCount == 0) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FilmsListFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun initBottomNavigation() {
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            supportFragmentManager.popBackStack()
            when (item.itemId) {
                R.id.menu_film_list -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainer, FilmsListFragment(), FilmsListFragment.TAG)
                        .addToBackStack("FilmsListFragment")
                        .commit()
                }
                R.id.menu_favorite -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            FavoriteFilmsFragment(),
                            FavoriteFilmsFragment.FAVORITE_FILMS
                        )
                        .addToBackStack("FavoriteFilmsFragment")
                        .commit()
                }
            }
            true
        }
    }

    private fun initClickListeners() {
        val inviteBtn by lazy { findViewById<View>(R.id.invite) }
        inviteBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, R.string.invite)
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {
            showExitDialog()
        }
    }

    private fun showExitDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setMessage(getString(R.string.exit_text))
        dialog.setTitle(R.string.exit_title)
        dialog.setNeutralButton(R.string.cancel, { dialog, which -> dialog.dismiss() })
        dialog.setPositiveButton(R.string.ok, { dialog, _ -> finish() })
        dialog.create().show()
    }

    //inFilmListFragment
    override fun onDetalsClicked(filmItem: FilmItem) {
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragmentContainer,
                FilmDetailsFragment.newInstance(filmItem),
                FilmDetailsFragment.TAG
            )
            .addToBackStack(null)
            .commit()
    }

    //inFilmListFragment
    override fun onFavoriteClicked(filmItem: FilmItem, position: Int) {
        if (filmItem.isCheck) {
            filmItem.isCheck = false
            likedFilms.remove(filmItem)
            Snackbar.make(
                fragmentContainer,
                "${getString(filmItem.resTit)} delete",
                Snackbar.LENGTH_SHORT
            )
                .setAction(R.string.cancel) {
                    likedFilms.add(filmItem)
                    filmItem.isCheck = true
                    FilmsListFragment.recyclerView.adapter?.notifyItemChanged(position)
                }.show()
        } else {
            filmItem.isCheck = true
            likedFilms.add(filmItem)
            Snackbar.make(
                fragmentContainer,
                "${getString(filmItem.resTit)} add",
                Snackbar.LENGTH_SHORT
            )
                .setAction(R.string.cancel) {
                    likedFilms.remove(filmItem)
                    filmItem.isCheck = false
                    FilmsListFragment.recyclerView.adapter?.notifyItemChanged(position)
                }.show()
        }
        FilmsListFragment.recyclerView.adapter?.notifyItemChanged(position)
    }

    // inFavoriteFilmsFragment
    override fun onFavoriteClick(filmItem: FilmItem, adapterPosition: Int) {
        if (filmItem.isCheck) {
            filmItem.isCheck = false
            likedFilms.remove(filmItem)
            FavoriteFilmsFragment.recyclerViewLike.adapter?.notifyItemRemoved(adapterPosition)
            Snackbar.make(
                fragmentContainer,
                "${getString(filmItem.resTit)} delete",
                Snackbar.LENGTH_SHORT
            )
                .setAction(R.string.cancel) {
                    likedFilms.add(filmItem)
                    filmItem.isCheck = true
                    FavoriteFilmsFragment.recyclerViewLike.adapter?.notifyItemChanged(
                        adapterPosition
                    )
                }.show()
        } else {
            filmItem.isCheck = true
            likedFilms.add(filmItem)
        }
    }
}

