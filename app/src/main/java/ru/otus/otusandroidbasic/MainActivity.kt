package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.otus.otusandroidbasic.FilmDetails.Companion.EXTRA_Data

//import ru.otus.otusandroidbasic.FilmDetails.Companion.EXTRA_Data



class MainActivity : AppCompatActivity() {
    companion object {
        const val IDFILM = "selected_film"
        const val TAG_FILMINFO = "TAG_FILMINFO"
        const val REQUEST_FOR_COMMENT = 1
    }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val inviteBtn by lazy { findViewById<View>(R.id.invite) }

    private val items= mutableListOf(
        FilmItem(R.drawable.g, R.string.G_text, R.string.gentl),
        FilmItem(R.drawable.l, R.string.L_text, R.string.cart),
        FilmItem(R.drawable.r, R.string.R_text, R.string.rock),
        FilmItem(R.drawable.revolver, R.string.Revolver_text, R.string.revolver),
        FilmItem(R.drawable.snatch, R.string.snatch_text, R.string.snatch)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       /* if (savedInstanceState != null) {
            IdBtn = savedInstanceState.getInt(IDFILM)
        }*/
        initRecyclerView()
        initClickListeners()
      /*  changeTextColors(IdBtn)
        onClickListener()*/
    }

    private fun initClickListeners() {
        inviteBtn.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, R.string.invite)
            intent.type = "text/plain"
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager=layoutManager
        recyclerView.adapter = FilmAdapter(items, object : FilmAdapter.FilmsClickListener {
            override fun onDetalsClick(filmItem: FilmItem) {
                val intent = Intent(this@MainActivity, FilmDetails::class.java)
                intent.putExtra(EXTRA_Data, filmItem)
                startActivityForResult(intent, REQUEST_FOR_COMMENT)
            }

            override fun onFavoriteClick(filmItem: FilmItem) {

            }

        })
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == items.size) {
                    // load data from server
                    repeat(4) {
                        items.add(FilmItem(R.drawable.g, R.string.G_text, R.string.gentl))
                    }

                    recyclerView.adapter?.notifyItemRangeInserted(items.size - 4, 4)
                }
            }
        })

    }

  /*  private fun onClickListener() {
        button1.setOnClickListener {

        }
        button2.setOnClickListener {
            IdBtn = 2
            changeTextColors(IdBtn)
            val intent = Intent(this, FilmDetails::class.java)
            intent.putExtra(
                EXTRA_Data, FilmItem(
                    idBtn=IdBtn,

                )
            )
            startActivityForResult(intent, REQUEST_FOR_COMMENT)
        }
        button3.setOnClickListener {
            IdBtn = 3
            changeTextColors(IdBtn)
            val intent = Intent(this, FilmDetails::class.java)
            intent.putExtra(
                EXTRA_Data, FilmItem(
                    idBtn=IdBtn,

                )
            )
            startActivityForResult(intent, REQUEST_FOR_COMMENT)
        }

        button4.setOnClickListener {

        }

    }

    private fun changeTextColors(idFilm: Int) {
        textView1.setTextColor(BLACK)
        textView2.setTextColor(BLACK)
        textView3.setTextColor(BLACK)
        when (idFilm) {
            1 -> textView1.setTextColor(BLUE)
            2 -> textView2.setTextColor(BLUE)
            3 -> textView3.setTextColor(BLUE)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(IDFILM, IdBtn)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_FOR_COMMENT) {
            val FilmComment = data?.getParcelableExtra<FilmItem>(FilmDetails.EXTRA_Comment)
            FilmComment?.let {
                //  Toast.makeText(this, "like ${it.check}, comment ${it.comment}", Toast.LENGTH_LONG).show()
                Log.i(TAG_FILMINFO, "like ${it.isCheck}, comment ${it.comment}")
            }
        }
    }*/

    override fun onBackPressed() {
     val dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        dialog .setMessage(getString(R.string.exit_text))
        dialog .setTitle(R.string.exit_title)
        dialog .setNeutralButton(R.string.cancel, { dialog, which -> dialog.dismiss() })
        dialog .setPositiveButton(R.string.ok, { dialog, _ -> finish() })
        dialog.create().show()
    }
}

