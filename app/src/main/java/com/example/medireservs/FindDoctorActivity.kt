package com.example.medireservs

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FindDoctorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_find_doctor)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val exit = findViewById<CardView>(R.id.cardFDBack)
        exit.setOnClickListener {
            startActivity(
                Intent(
                    this@FindDoctorActivity,
                    HomeActivity::class.java
                )
            )
        }

        val familyphysician = findViewById<CardView>(R.id.cardFDFamilyPhysician)
        familyphysician.setOnClickListener {
            val it = Intent(this@FindDoctorActivity, DoctorDetailsActivity::class.java)
            it.putExtra("title", "Dokter Keluarga")
            startActivity(it)
        }

        val dietician = findViewById<CardView>(R.id.cardFDDietician)
        dietician.setOnClickListener {
            val it = Intent(this@FindDoctorActivity, DoctorDetailsActivity::class.java)
            it.putExtra("title", "Dokter Gizi")
            startActivity(it)
        }

        val dentist = findViewById<CardView>(R.id.cardFDDentist)
        dentist.setOnClickListener {
            val it = Intent(this@FindDoctorActivity, DoctorDetailsActivity::class.java)
            it.putExtra("title", "Dokter Gigi")
            startActivity(it)
        }

        val surgeon = findViewById<CardView>(R.id.cardFDSurgeon)
        surgeon.setOnClickListener {
            val it = Intent(this@FindDoctorActivity, DoctorDetailsActivity::class.java)
            it.putExtra("title", "Dokter Bedah")
            startActivity(it)
        }

        val cardiologists = findViewById<CardView>(R.id.cardFDCardiologists)
        cardiologists.setOnClickListener {
            val it = Intent(this@FindDoctorActivity, DoctorDetailsActivity::class.java)
            it.putExtra("title", "Dokter Spesialis Jantung")
            startActivity(it)
        }
    }
}