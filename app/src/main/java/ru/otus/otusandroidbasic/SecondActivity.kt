package ru.otus.otusandroidbasic

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    companion object{
        const val EXTRA_TEXT="dfgjkdkgjhdfjkgdfjkgdfjk"
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_second)
        intent.getStringExtra(EXTRA_TEXT)?.let{
            findViewById<TextView>(R.id.textView).text=EXTRA_TEXT
        }
    }
}