package com.example.medireservs

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
import com.example.medireservs.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var edUsername: EditText
    private lateinit var edPassword: EditText
    private lateinit var btn: Button
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        edUsername = findViewById(R.id.editTextLoginUsername)
        edPassword = findViewById(R.id.editTextLoginPassword)
        btn = findViewById(R.id.buttonLogin)
        tv = findViewById(R.id.textViewNewUser)

        btn.setOnClickListener(View.OnClickListener { //startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            val username = edUsername.getText().toString()
            val password = edPassword.getText().toString()
            val db = Database(applicationContext, "MediReservs", null, 1)
            if (username.length == 0 || password.length == 0) {
                Toast.makeText(applicationContext, "Silakan isi semua detail!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (db.login(username, password) == 1) {
                    Toast.makeText(applicationContext, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    val sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE)
                    val editor = sharedPreferences.edit()
                    editor.putString("username", username)
                    editor.apply()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Username dan Password tidak valid",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        tv.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    RegisterActivity::class.java
                )
            )
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}