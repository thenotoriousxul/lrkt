package com.example.loginrf

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.loginrf.databinding.ActivityMainBinding
import com.example.loginrf.ui.auth.LoginActivity
import com.example.loginrf.ui.auth.RegisterActivity
import com.example.loginrf.ui.users.UsersActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Botones con ViewBinding
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnUsers.setOnClickListener {
            startActivity(Intent(this, UsersActivity::class.java))
        }

    }
}
