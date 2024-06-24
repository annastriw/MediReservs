package com.example.medireservs

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DoctorDetailsActivity : AppCompatActivity() {

    private val doctorDetails1 = arrayOf(
        arrayOf("Nama Dokter: Budi Santoso", "Hospital: RS Banyumanik", "Exp: 10tahun", "No Telp: 081234567890", "200000"),
        arrayOf("Nama Dokter: Siti Aminah", "Hospital: RS Ungaran", "Exp: 8tahun", "No Telp: 081298765432", "180000"),
        arrayOf("Nama Dokter: Rina Kartika", "Hospital: RS Tugu Rejo", "Exp: 12tahun", "No Telp: 081377889900", "220000"),
        arrayOf("Nama Dokter: Hendro Suharto", "Hospital: RS Sultan Agung", "Exp: 15tahun", "No Telp: 081234556677", "250000"),
        arrayOf("Nama Dokter: Aisyah Farida", "Hospital: RS William Booth", "Exp: 7tahun", "No Telp: 081298712345", "190000")
    )

    private val doctorDetails2 = arrayOf(
        arrayOf("Nama Dokter: Andi Prasetyo", "Hospital: RS Kariadi", "Exp: 6tahun", "No Telp: 081334455667", "170000"),
        arrayOf("Nama Dokter: Nur Hasanah", "Hospital: RS Banyumanik", "Exp: 9tahun", "No Telp: 081312345678", "210000"),
        arrayOf("Nama Dokter: Rudi Hartono", "Hospital: RS Sultan Agung", "Exp: 11tahun", "No Telp: 081389012345", "230000"),
        arrayOf("Nama Dokter: Dian Anggraini", "Hospital: RS Ungaran", "Exp: 5tahun", "No Telp: 081234598765", "160000"),
        arrayOf("Nama Dokter: Farid Setiawan", "Hospital: RS Tugu Rejo", "Exp: 7tahun", "No Telp: 081377880123", "180000")
    )

    private val doctorDetails3 = arrayOf(
        arrayOf("Nama Dokter: Maya Sari", "Hospital: RS Kariadi", "Exp: 14tahun", "No Telp: 081398765432", "240000"),
        arrayOf("Nama Dokter: Agus Wijaya", "Hospital: RS William Booth", "Exp: 13tahun", "No Telp: 081299876543", "230000"),
        arrayOf("Nama Dokter: Lina Marlina", "Hospital: RS Banyumanik", "Exp: 10tahun", "No Telp: 081334567891", "200000"),
        arrayOf("Nama Dokter: Bambang Sutrisno", "Hospital: RS Sultan Agung", "Exp: 9tahun", "No Telp: 081323456789", "220000"),
        arrayOf("Nama Dokter: Nurul Hidayati", "Hospital: RS Ungaran", "Exp: 8tahun", "No Telp: 081300987654", "210000")
    )

    private val doctorDetails4 = arrayOf(
        arrayOf("Nama Dokter: Hartono Wijaya", "Hospital: RS William Booth", "Exp: 20tahun", "No Telp: 081312345678", "500000"),
        arrayOf("Nama Dokter: Sari Dewi", "Hospital: RS Kariadi", "Exp: 18tahun", "No Telp: 081345678912", "480000"),
        arrayOf("Nama Dokter: Rizki Purnama", "Hospital: RS Banyumanik", "Exp: 15tahun", "No Telp: 081399876543", "450000"),
        arrayOf("Nama Dokter: Adi Saputra", "Hospital: RS Sultan Agung", "Exp: 12tahun", "No Telp: 081311223344", "420000"),
        arrayOf("Nama Dokter: Widya Kusuma", "Hospital: RS Tugu Rejo", "Exp: 17tahun", "No Telp: 081322334455", "470000")
    )

    private val doctorDetails5 = arrayOf(
        arrayOf("Nama Dokter: Susi Rahayu", "Hospital: RS Kariadi", "Exp: 16tahun", "No Telp: 081344556677", "400000"),
        arrayOf("Nama Dokter: Bambang Sugeng", "Hospital: RS William Booth", "Exp: 14tahun", "No Telp: 081377889900", "380000"),
        arrayOf("Nama Dokter: Endang Lestari", "Hospital: RS Ungaran", "Exp: 12tahun", "No Telp: 081311223355", "350000"),
        arrayOf("Nama Dokter: Dwi Santoso", "Hospital: RS Sultan Agung", "Exp: 10tahun", "No Telp: 081366778899", "320000"),
        arrayOf("Nama Dokter: Maya Andini", "Hospital: RS Banyumanik", "Exp: 13tahun", "No Telp: 081399876545", "370000")
    )

    private lateinit var tv: TextView
    private lateinit var btn: Button
    private lateinit var doctorDetails: Array<Array<String>>
    private lateinit var sa: SimpleAdapter
    private lateinit var list: ArrayList<HashMap<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_doctor_details)

        tv = findViewById(R.id.textViewDDTitle2)
        btn = findViewById(R.id.buttonDDBack)

        val intent = intent
        val title = intent.getStringExtra("title") ?: ""
        tv.text = title

        doctorDetails = when (title) {
            "Dokter Keluarga" -> doctorDetails1
            "Dokter Gizi" -> doctorDetails2
            "Dokter Gigi" -> doctorDetails3
            "Dokter Bedah" -> doctorDetails4
            else -> doctorDetails5
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn.setOnClickListener {
            startActivity(Intent(this@DoctorDetailsActivity, FindDoctorActivity::class.java))
        }

        list = ArrayList()
        for (i in doctorDetails.indices) {
            val item = HashMap<String, String>()
            item["line1"] = doctorDetails[i][0]
            item["line2"] = doctorDetails[i][1]
            item["line3"] = doctorDetails[i][2]
            item["line4"] = doctorDetails[i][3]
            item["line5"] = "Biaya Konsultasi: Rp" + doctorDetails[i][4]
            list.add(item)
        }

        sa = SimpleAdapter(
            this, list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        val lst = findViewById<ListView>(R.id.listViewDD)
        lst.adapter = sa

        lst.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val it = Intent(this@DoctorDetailsActivity, BookAppointmentActivity::class.java)
            it.putExtra("text1", title)
            it.putExtra("text2", doctorDetails[i][0])
            it.putExtra("text3", doctorDetails[i][1])
            it.putExtra("text4", doctorDetails[i][3])
            it.putExtra("text5", doctorDetails[i][4])
            startActivity(it)
        }
    }
}
