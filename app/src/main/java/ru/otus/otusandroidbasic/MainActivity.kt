package ru.otus.otusandroidbasic

import android.content.Intent
import android.graphics.Color.BLACK
import android.graphics.Color.BLUE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.otus.otusandroidbasic.FilmDetails.Companion.EXTRA_Data


class MainActivity : AppCompatActivity() {
    companion object {
        const val IDFILM = "selected_film"
        const val LIKE= "like"
        const val COMMENT= "comment"
       // const val REQUEST_FOR_COMMENT = 1
    }

    private val textView1 by lazy { findViewById<TextView>(R.id.textView) }
    private val textView2 by lazy { findViewById<TextView>(R.id.textView2) }
    private val textView3 by lazy { findViewById<TextView>(R.id.textView3) }
    private val button1 by lazy { findViewById<View>(R.id.button) }
    private val button2 by lazy { findViewById<View>(R.id.button2) }
    private val button3 by lazy { findViewById<View>(R.id.button3) }
    private val button4 by lazy { findViewById<View>(R.id.button4) }
    var IdBtn: Int = 0
    var like = false
    var comment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) {
            IdBtn = savedInstanceState.getInt(IDFILM)
        }
        changeTextColors(IdBtn)
        onClickListener()

    }

    override fun onResume() {
        super.onResume()
        intent.getStringExtra(LIKE).let{ like=true}
        intent.getStringExtra(COMMENT).let{ comment="dsdsddsdscs"}
        Log.i("Result", "Liked: $like  comment: $comment")
    }

    private fun onClickListener() {
        button1.setOnClickListener {
            IdBtn = 1
            changeTextColors(IdBtn)
            val intent = Intent(this, FilmDetails::class.java)
            intent.putExtra(
                EXTRA_Data, FilmData(
                    IdBtn,
                    R.drawable.g,
                    R.string.G_text,
                    R.string.gentl
                )
            )
            startActivity(intent)
        }
        button2.setOnClickListener {
            IdBtn = 2
            changeTextColors(IdBtn)
            val intent = Intent(this, FilmDetails::class.java)
            intent.putExtra(
                EXTRA_Data, FilmData(
                    IdBtn,
                    R.drawable.l,
                    R.string.L_text,
                    R.string.cart
                )
            )
            startActivity(intent)
        }
        button3.setOnClickListener {
            IdBtn = 3
            changeTextColors(IdBtn)
            val intent = Intent(this, FilmDetails::class.java)
            intent.putExtra(
                EXTRA_Data, FilmData(
                    IdBtn,
                    R.drawable.r,
                    R.string.R_text,
                    R.string.rock
                )
            )
            startActivity(intent)
        }

        button4.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, R.string.friendInvite)
            intent.type = "text/plain"
            startActivity(intent)
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


}

