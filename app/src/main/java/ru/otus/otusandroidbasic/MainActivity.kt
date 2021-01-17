package ru.otus.otusandroidbasic

import android.content.Intent
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import ru.otus.otusandroidbasic.R.layout.activity_main

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA1="EXTRA_COLOR"
        const val EXTRA2="EXTRA_COLOR2"
        const val EXTRA3="EXTRA_COLOR3"
    }
    private val textView1 by lazy { findViewById<TextView>(R.id.textView) }
    private val textView2 by lazy { findViewById<TextView>(R.id.textView2) }
    private val textView3 by lazy { findViewById<TextView>(R.id.textView3) }

    private val button1 by lazy {findViewById<View>(R.id.button) }
    private val button2 by lazy { findViewById<View>(R.id.button2)}
    private val button3 by lazy { findViewById<View>(R.id.button3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button1.setOnClickListener {
            textView1.setTextColor(BLUE)
            textView2.setTextColor(BLACK)
            textView3.setTextColor(BLACK)
            val intent=Intent(this, SecondActivity::class.java)
            intent.putExtra(SecondActivity.EXTRA_TEXT ,"dvgfgdfgdfgfdgdfgf")
            startActivity(intent)
        }
        button2.setOnClickListener {
            textView2.setTextColor(BLUE)
            textView1.setTextColor(BLACK)
            textView3.setTextColor(BLACK)
        }
        button3.setOnClickListener {
            textView3.setTextColor(BLUE)
            textView2.setTextColor(BLACK)
            textView1.setTextColor(BLACK)
        }
       savedInstanceState?.getInt(EXTRA1)?.let{
           textView1.setTextColor(it)

        }
        savedInstanceState?.getInt(EXTRA2)?.let{
            textView2.setTextColor(it)
        }
        savedInstanceState?.getInt(EXTRA3)?.let{
            textView3.setTextColor(it)
        }
    }

   override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(EXTRA1,findViewById<TextView>(R.id.textView).currentTextColor)
        outState.putInt(EXTRA2,findViewById<TextView>(R.id.textView2).currentTextColor)
        outState.putInt(EXTRA3,findViewById<TextView>(R.id.textView3).currentTextColor)
    }
}
