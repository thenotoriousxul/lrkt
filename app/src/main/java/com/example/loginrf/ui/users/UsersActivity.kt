package com.example.loginrf.ui.users

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginrf.databinding.ActivityUsersBinding
import com.example.loginrf.ui.auth.AuthViewModel
import com.example.loginrf.ui.users.adapter.UsersAdapter
import android.content.Intent




class UsersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUsersBinding
    private val viewModel: AuthViewModel by viewModels()
    private lateinit var adapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        loadUsers()
    }

    private fun setupRecyclerView() {
        adapter = UsersAdapter { user ->
            val intent = Intent(this, UserDetailsActivity::class.java).apply {
                putExtra(UserDetailsActivity.EXTRA_USER_ID, user.id)
            }
            startActivity(intent)
        }
        binding.recyclerUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerUsers.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.usersResult.observe(this) { result ->
            result.fold(
                onSuccess = { users ->
                    adapter.submitList(users)
                },
                onFailure = { exception ->
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }

    private fun loadUsers() {
        viewModel.getUsers()
    }
}