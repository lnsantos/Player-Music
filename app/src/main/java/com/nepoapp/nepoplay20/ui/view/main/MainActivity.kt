package com.nepoapp.nepoplay20.ui.view.main

import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.SimpleCursorAdapter
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.loader.app.LoaderManager
import com.nepoapp.nepoplay20.R
import com.nepoapp.nepoplay20.core.repositories.CursorRepository
import com.nepoapp.nepoplay20.core.repositories.MusicRepository
import com.nepoapp.nepoplay20.core.services.MusicListenerServiceImplementation
import com.nepoapp.nepoplay20.databinding.ActivityMainBinding
import com.nepoapp.nepoplay20.viewmodel.MainViewModel

class MainActivity :
    AppCompatActivity(),
    AdapterView.OnItemClickListener{

    private lateinit var binding   : ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    private val musicRepository : MusicRepository = MusicRepository()
    private val cursorRepository : CursorRepository = CursorRepository(this){
        adapter.swapCursor(it)
    }

    private val adapter : SimpleCursorAdapter by lazy {
        SimpleCursorAdapter(
            this,
            android.R.layout.simple_list_item_2,
            null,
            arrayOf(
                MediaStore.MediaColumns.DISPLAY_NAME,
                MediaStore.MediaColumns.DATA,
                MediaStore.MediaColumns._ID
            ),
            intArrayOf(
                android.R.id.text1,
                android.R.id.text2
            ),
            0
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        viewModel.initViewModel(cursorRepository,musicRepository)

        binding.listview.adapter = adapter
        binding.listview.onItemClickListener = this
    }

    override fun onResume() {
        super.onResume()
        if (adapter.count == 0){
            val permission = android.Manifest.permission.READ_EXTERNAL_STORAGE
            if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
                LoaderManager.getInstance(this).initLoader(0,null, cursorRepository)
            }
        }else ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
            0
        )

        val intent = Intent(this,MusicListenerServiceImplementation::class.java)
        startService(intent)
        bindService(intent, musicRepository, 0)
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val cursor = parent?.getItemAtPosition(position) as? Cursor
        cursor?.let {
            val nameMusic = it.getString(it.getColumnIndex(MediaStore.Files.FileColumns.DATA))
            binding.currentMusic.text = nameMusic
            viewModel.play()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        val validateReadExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED
        if (validateReadExternalStorage){
            LoaderManager.getInstance(this).initLoader(0,null,cursorRepository.getContext())
        }else finish()
    }

}
