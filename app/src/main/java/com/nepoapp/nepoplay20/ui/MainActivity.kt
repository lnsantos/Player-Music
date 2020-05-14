package com.nepoapp.nepoplay20.ui

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nepoapp.nepoplay20.R
import com.nepoapp.nepoplay20.databinding.ActivityMainBinding
import com.nepoapp.nepoplay20.ui.adapters.MusicAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var adapter : MusicAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        adapter = MusicAdapter(this)

        setupRecyclerView(adapter)

    }

    private fun setupRecyclerView(viewAdapter : MusicAdapter) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = viewAdapter
        }
    }


}
