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

class RegisterActivity : AppCompatActivity() {
    private lateinit var edUsername: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var edConfirm: EditText
    private lateinit var btn: Button
    private lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        edUsername = findViewById(R.id.editTextUsernameReg)
        edPassword = findViewById(R.id.editTextPasswordReg)
        edEmail = findViewById(R.id.editTextEmailReg)
        edConfirm = findViewById(R.id.editTextConfirmPassReg)
        btn = findViewById(R.id.buttonReg)
        tv = findViewById(R.id.textViewExistingUser)

        tv.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@RegisterActivity,
                    LoginActivity::class.java
                )
            )
        })

        btn.setOnClickListener(View.OnClickListener {
            val username = edUsername.getText().toString()
            val email = edEmail.getText().toString()
            val password = edPassword.getText().toString()
            val confirm = edConfirm.getText().toString()
            val db = Database(applicationContext, "MediReservs", null, 1)
            if (username.length == 0 || email.length == 0 || password.length == 0 || confirm.length == 0) {
                Toast.makeText(applicationContext, "Silakan isi semua detail!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (password.compareTo(confirm) == 0) {
                    if (isValid(password)) {
                        db.register(username, email, password)
                        Toast.makeText(
                            applicationContext,
                            "Data telah tersimpan",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Password harus mengandung minimal 8 karakter, terdiri dari huruf, angka, dan simbol khusus.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password dan Konfirmasi Password tidak cocok",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        fun isValid(passwordhere: String): Boolean {
            var f1 = 0
            var f2 = 0
            var f3 = 0
            if (passwordhere.length < 8) {
                return false
            } else {
                for (p in 0 until passwordhere.length) {
                    if (Character.isLetter(passwordhere[p])) {
                        f1 = 1
                    }
                }
                for (r in 0 until passwordhere.length) {
                    if (Character.isLetter(passwordhere[r])) {
                        f2 = 1
                    }
                }
                for (s in 0 until passwordhere.length) {
                    val c = passwordhere[s]
                    if (c.code >= 33 && c.code <= 46 || c.code == 64) {
                        f3 = 1
                    }
                }
                if (f1 == 1 && f2 == 1 && f3 == 1) return true
                return false
            }
        }
    }
}