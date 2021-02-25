package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class FilmDetailsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_Data = "EXTRA_Data"
        const val EXTRA_Comment = "EXTRA_Comment"
    }
    var resTit: Int = 0
    var resTxt: Int = 0
    var resImg: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmdetails)
        intent.getParcelableExtra<FilmItem>(EXTRA_Data)?.let {
            resTit = it.resTit
            resTxt = it.resTxt
            resImg = it.resImg
            findViewById<TextView>(R.id.title).setText(it.resTit)
            findViewById<TextView>(R.id.text).setText(it.resTxt)
            findViewById<ImageView>(R.id.imageView).setImageResource(it.resImg)
        }
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            setResult(
                RESULT_OK,
                Intent().apply {
                    putExtra(
                        EXTRA_Comment,
                        FilmItem(
                            resImg, resTxt, resTit,
                            // isCheck=(findViewById<View>(R.id.likeCheckBox) as CheckBox).isChecked,
                            comment = findViewById<EditText>(R.id.comment).text.toString(),
                        ),
                    )
                },
            )
            finish()
        }
    }
}