package com.example.loginrf.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.loginrf.databinding.UserDetailsActivityBinding
import com.example.loginrf.ui.auth.AuthViewModel

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: UserDetailsActivityBinding
    private val viewModel: AuthViewModel by viewModels()

    companion object {
        const val EXTRA_USER_ID = "extra_user_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = intent.getIntExtra(EXTRA_USER_ID, -1)
        if (userId == -1) {
            Toast.makeText(this, "Error: ID de usuario no válido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupObservers()
        setupListeners()

        // Cargar el usuario
        viewModel.getUserById(userId)
    }

    private fun setupObservers() {
        viewModel.userResult.observe(this) { result ->
            result.fold(
                onSuccess = { user ->
                    binding.tvUserId.text = user.id.toString()
                    binding.tvUserName.text = user.name
                    binding.tvUserEmail.text = user.email
                    binding.tvUserCreated.text = user.created_at
                },
                onFailure = { exception ->
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                    finish()
                }
            )
        }
    }

    private fun setupListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}