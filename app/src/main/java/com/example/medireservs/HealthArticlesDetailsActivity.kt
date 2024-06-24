package com.example.medireservs

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HealthArticlesDetailsActivity : AppCompatActivity() {
    private lateinit var tv1: TextView
    private lateinit var img: ImageView
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_health_articles_details)

        // Inisialisasi view dengan findViewById
        btnBack = findViewById(R.id.buttonHADBack)
        tv1 = findViewById(R.id.textViewHADtitle)
        img = findViewById(R.id.imageViewHAD)

        // Mengambil data dari intent
        val intent = intent
        val title = intent.getStringExtra("text1")
        val imageResId = intent.getIntExtra("text2", -1)

        // Set data ke TextView dan ImageView
        tv1.setText(title)
        if (imageResId != -1) {
            img.setImageResource(imageResId)
        }

        // Atur padding untuk window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set listener untuk tombol kembali
        btnBack.setOnClickListener(View.OnClickListener { v: View? -> finish() }) // Menyelesaikan activity saat tombol ditekan
    }
}
