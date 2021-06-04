package com.example.hoaison.demodaggerhilt.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.hoaison.demodaggerhilt.R
import com.example.hoaison.demodaggerhilt.adapter.UserListAdapter
import com.example.hoaison.demodaggerhilt.databinding.ActivityMainBinding
import com.example.hoaison.demodaggerhilt.model.User
import com.example.hoaison.demodaggerhilt.utils.Status
import com.example.hoaison.demodaggerhilt.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    private val viewModel: UserViewModel by viewModels()
    private lateinit var adapter: UserListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getUserList(token = "ee2a1995f29b5ceb71ecf1908a3f0156", page = 1, limit = 30)

        viewModel.userListResponse.observe(this, {result ->
            when(result.status) {
                Status.SUCCESS -> {
                    binding.progressLoadUser.layoutProgress.visibility = View.GONE
                    getUserListSuccess(result.data)
                    binding.listView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressLoadUser.layoutProgress.visibility = View.VISIBLE
                    binding.listView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressLoadUser.layoutProgress.visibility = View.GONE
                    Log.e(TAG, result.message.toString())
                }
            }
        })
    }

    private fun getUserListSuccess(userList: List<User>?) {
        layoutManager = GridLayoutManager(this, 3)
        binding.listView.layoutManager = layoutManager
        adapter = userList?.let { UserListAdapter(this, it) }!!
        binding.listView.adapter = adapter
    }
}