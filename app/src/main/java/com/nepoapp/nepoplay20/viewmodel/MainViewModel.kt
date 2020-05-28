package com.nepoapp.nepoplay20.viewmodel

import android.content.ComponentName
import android.content.ServiceConnection
import android.database.Cursor
import android.os.IBinder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nepoapp.nepoplay20.core.listeners.MusicListenerService
import com.nepoapp.nepoplay20.core.repositories.CursorRepository
import com.nepoapp.nepoplay20.core.repositories.MusicRepository

class MainViewModel : ViewModel() {

    lateinit var updateListMusic : (data: Cursor?) -> Cursor?
    lateinit var currentMusic : String

    private lateinit var cursorRepository : CursorRepository
    private lateinit var musicRepository : MusicRepository

    fun initViewModel(cursorRepository : CursorRepository,
                      musicRepository : MusicRepository){
        this@MainViewModel.cursorRepository = cursorRepository
        this@MainViewModel.musicRepository = musicRepository
    }

    fun stop(){
        musicRepository.stop {

        }
    }
    fun play(){
        musicRepository.play(currentMusic)
    }




}