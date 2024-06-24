package com.example.medireservs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HealthArticlesActivity : AppCompatActivity() {
    private val health_details = arrayOf(
        arrayOf("Cara Sikat Gigi yang Benar", "", "", "", "Detail Selengkapnya"),
        arrayOf("Maag VS Gerd", "", "", "", "Detail Selengkapnya"),
        arrayOf("Tips Atasi Batuk Pilek", "", "", "", "Detail Selengkapnya"),
        arrayOf("Penanganan Alergi", "", "", "", "Detail Selengkapnya"),
        arrayOf("Pencegahan Stunting", "", "", "", "Detail Selengkapnya")
    )

    private val images = intArrayOf(
        R.drawable.health1,
        R.drawable.health2,
        R.drawable.health3,
        R.drawable.health4,
        R.drawable.health5
    )

    private lateinit var item: HashMap<String, String>
    private lateinit var list: ArrayList<HashMap<String, String>>
    private lateinit var sa: SimpleAdapter
    private lateinit var btnBack: Button
    private lateinit var lst: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_health_articles)

        lst = findViewById(R.id.listViewHA)
        btnBack = findViewById(R.id.buttonHABack)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@HealthArticlesActivity,
                    HomeActivity::class.java
                )
            )
        })

        list = ArrayList()
        for (i in health_details.indices) {
            item = HashMap()
            item!!["line1"] = health_details[i][0]
            item!!["line2"] = health_details[i][1]
            item!!["line3"] = health_details[i][2]
            item!!["line4"] = health_details[i][3]
            item!!["line5"] = health_details[i][4]
            list.add(item)
        }

        sa = SimpleAdapter(
            this, list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )

        lst.setAdapter(sa)

        lst.setOnItemClickListener(OnItemClickListener { adapterView, view, i, l ->
            val it = Intent(this@HealthArticlesActivity, HealthArticlesDetailsActivity::class.java)
            it.putExtra("text1", health_details[i][0])
            it.putExtra("text2", images[i])
            startActivity(it)
        })
    }
}