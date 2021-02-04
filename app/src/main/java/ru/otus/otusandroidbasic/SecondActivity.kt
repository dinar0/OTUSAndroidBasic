package ru.otus.otusandroidbasic

import android.content.res.Resources
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity: AppCompatActivity() {
    companion object {
        const val EXTRA_Data = "EXTRA_Data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
         intent.getParcelableExtra<SomeData>(EXTRA_Data)?.let{
             findViewById<TextView>(R.id.textView).setText(it.resTxt)
             findViewById<ImageView>(R.id.imageView).setImageResource(it.resImg)

        // }
    }
}
}