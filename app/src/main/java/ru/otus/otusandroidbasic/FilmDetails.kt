package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class FilmDetails : AppCompatActivity() {
    companion object {
        const val EXTRA_Data = "EXTRA_Data"
        const val EXTRA_Comment = "EXTRA_Comment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filmdetails)
        intent.getParcelableExtra<FilmData>(EXTRA_Data)?.let {
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
                        FilmComment(
                            (findViewById<View>(R.id.likeCheckBox) as CheckBox).isChecked,
                            findViewById<EditText>(R.id.comment).text.toString(),
                        ),
                    )
                },
            )
            finish()
        }
    }
}