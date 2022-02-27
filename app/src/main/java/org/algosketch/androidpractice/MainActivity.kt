package org.algosketch.androidpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myCustomView = findViewById<CustomView>(R.id.my_custom_view)
        myCustomView.setOnBackListener {
            println("우와아아앙")
            finish()
        }
    }
}