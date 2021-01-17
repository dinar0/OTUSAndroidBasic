package ru.otus.otusandroidbasic

import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import ru.otus.otusandroidbasic.R.layout.activity_main

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.button).setOnClickListener {
            findViewById<TextView>(R.id.textView).setTextColor(BLUE)
            findViewById<TextView>(R.id.textView2).setTextColor(BLACK)
            findViewById<TextView>(R.id.textView3).setTextColor(BLACK)
        }
        findViewById<View>(R.id.button2).setOnClickListener {
            findViewById<TextView>(R.id.textView2).setTextColor(BLUE)
            findViewById<TextView>(R.id.textView).setTextColor(BLACK)
            findViewById<TextView>(R.id.textView3).setTextColor(BLACK)
        }
        findViewById<View>(R.id.button3).setOnClickListener {
            findViewById<TextView>(R.id.textView3).setTextColor(BLUE)
            findViewById<TextView>(R.id.textView2).setTextColor(BLACK)
            findViewById<TextView>(R.id.textView).setTextColor(BLACK)
        }
       savedInstanceState?.getInt("EXTRA_COLOR")?.let{
            findViewById<TextView>(R.id.textView).setTextColor(it)
        }
        savedInstanceState?.getInt("EXTRA_COLOR2")?.let{
            findViewById<TextView>(R.id.textView2).setTextColor(it)
        }
        savedInstanceState?.getInt("EXTRA_COLOR3")?.let{
            findViewById<TextView>(R.id.textView3).setTextColor(it)
        }
    }

   override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("EXTRA_COLOR",findViewById<TextView>(R.id.textView).currentTextColor)
        outState.putInt("EXTRA_COLOR2",findViewById<TextView>(R.id.textView2).currentTextColor)
        outState.putInt("EXTRA_COLOR3",findViewById<TextView>(R.id.textView3).currentTextColor)
    }
}
