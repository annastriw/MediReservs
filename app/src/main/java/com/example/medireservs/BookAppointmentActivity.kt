package com.example.medireservs

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class BookAppointmentActivity : AppCompatActivity() {
    private lateinit var ed1: EditText
    private lateinit var ed2: EditText
    private lateinit var ed3: EditText
    private lateinit var ed4: EditText
    private lateinit var tv: TextView
    private lateinit var datePickerDialog: DatePickerDialog
    private lateinit var timePickerDialog: TimePickerDialog
    private lateinit var dateButton: Button
    private lateinit var timeButton: Button
    private lateinit var btnBook: Button
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_book_appointment)

        tv = findViewById(R.id.textViewAppTitle)
        ed1 = findViewById(R.id.editTextAppFullName)
        ed2 = findViewById(R.id.editTextAppAddress)
        ed3 = findViewById(R.id.editTextAppContactNumber)
        ed4 = findViewById(R.id.editTextAppFees)
        dateButton = findViewById(R.id.buttonAppDate)
        timeButton = findViewById(R.id.buttonAppTime)
        btnBook = findViewById(R.id.buttonBookAppointment)
        btnBack = findViewById(R.id.buttonAppBack)

        ed1.setKeyListener(null)
        ed2.setKeyListener(null)
        ed3.setKeyListener(null)
        ed4.setKeyListener(null)

        val it = intent
        val title = it.getStringExtra("text1")
        val fullname = it.getStringExtra("text2")
        val address = it.getStringExtra("text3")
        val contact = it.getStringExtra("text4")
        val fees = it.getStringExtra("text5")

        tv.setText(title)
        ed1.setText(fullname)
        ed2.setText(address)
        ed3.setText(contact)
        ed4.setText("Biaya Konsultasi: Rp$fees")

        //datepicker
        initDatePicker()
        dateButton.setOnClickListener(View.OnClickListener { datePickerDialog!!.show() })

        //timepicker
        initTimePicker()
        timeButton.setOnClickListener(View.OnClickListener { timePickerDialog!!.show() })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnBack.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@BookAppointmentActivity,
                    FindDoctorActivity::class.java
                )
            )
        })

        btnBook.setOnClickListener(View.OnClickListener {
            val db = Database(applicationContext, "MediReservs", null, 1)
            val sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE)
            val username = sharedPreferences.getString("username", "").toString()
            if (db.checkAppointmentExists(
                    username,
                    "$title => $fullname",
                    address,
                    contact,
                    dateButton.getText().toString(),
                    timeButton.getText().toString()
                ) == 1
            ) {
                Toast.makeText(
                    applicationContext,
                    "Reservasi Medis sudah terjadwal",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                db.addOrder(
                    username,
                    "$title => $fullname",
                    address,
                    contact,
                    0,
                    dateButton.getText().toString(),
                    timeButton.getText().toString(),
                    fees!!.toFloat(),
                    "Konsultasi"
                )
                Toast.makeText(
                    applicationContext,
                    "Anda telah berhasil melakukan janji temu/reservasi medis",
                    Toast.LENGTH_LONG
                ).show()
                startActivity(Intent(this@BookAppointmentActivity, HomeActivity::class.java))
            }
        })
    }

    private fun initDatePicker() {
        val dateSetListener = OnDateSetListener { datePicker, i, i1, i2 ->
            var i1 = i1
            i1 = i1 + 1
            dateButton!!.text = "$i2/$i1/$i"
        }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]

        val style = AlertDialog.THEME_HOLO_DARK
        datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
        datePickerDialog!!.datePicker.minDate = cal.timeInMillis + 86400000
    }

    private fun initTimePicker() {
        val timeSetListener =
            OnTimeSetListener { timePicker, i, i1 -> timeButton!!.text = "$i:$i1" }
        val cal = Calendar.getInstance()
        val hrs = cal[Calendar.HOUR]
        val mins = cal[Calendar.MINUTE]

        val style = AlertDialog.THEME_HOLO_DARK
        timePickerDialog = TimePickerDialog(this, style, timeSetListener, hrs, mins, true)
    }
}