package ru.otus.otusandroidbasic

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ru.otus.otusandroidbasic.MainActivity.Companion.COMMENT
import ru.otus.otusandroidbasic.MainActivity.Companion.LIKE

class FilmDetails : AppCompatActivity() {
    companion object {
        const val EXTRA_Data = "EXTRA_Data"
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
        button.setOnClickListener { view: View? ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(LIKE, (findViewById<View>(R.id.likeCheckBox) as CheckBox).isChecked)
            intent.putExtra(COMMENT, (findViewById<View>(R.id.comment) as EditText).text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}