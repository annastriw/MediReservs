package com.example.medireservs

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderDetailsActivity : AppCompatActivity() {

    private lateinit var orderDetails: Array<Array<String?>>
    private lateinit var item: HashMap<String, String>
    private lateinit var list: ArrayList<HashMap<String, String>>
    private lateinit var sa: SimpleAdapter
    private lateinit var lst: ListView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_order_details)

        btn = findViewById(R.id.buttonODBack)
        lst = findViewById(R.id.listViewOD)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars: Insets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn.setOnClickListener {
            startActivity(Intent(this@OrderDetailsActivity, HomeActivity::class.java))
        }

        val db = Database(applicationContext, "MediReservs", null, 1)
        val sharedPreferences: SharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "").toString()
        val dbData = db.getOrderData(username)

        orderDetails = Array(dbData.size) { arrayOfNulls(5) }

        for (i in orderDetails.indices) {
            val arrData = dbData[i].toString()
            val strData = arrData.split(Regex("\\$")).toTypedArray()
            orderDetails[i][0] = strData[0]
            orderDetails[i][1] = strData[1]
            orderDetails[i][2] = "Rp" + strData[6]
            if (strData[7] == "medicine") {
                orderDetails[i][3] = "Tgl: " + strData[4]
            } else {
                orderDetails[i][3] = "Tgl: " + strData[4] + " " + strData[5]
            }
            orderDetails[i][4] = strData[7]
        }

        list = ArrayList()
        for (i in orderDetails.indices) {
            item = HashMap()
            item["line1"] = orderDetails[i][0] ?: ""
            item["line2"] = orderDetails[i][1] ?: ""
            item["line3"] = orderDetails[i][2] ?: ""
            item["line4"] = orderDetails[i][3] ?: ""
            item["line5"] = orderDetails[i][4] ?: ""
            list.add(item)
        }

        sa = SimpleAdapter(
            this, list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        lst.adapter = sa
    }
}
