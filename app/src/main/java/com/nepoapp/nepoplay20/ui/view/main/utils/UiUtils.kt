package com.nepoapp.nepoplay20.ui.view.main.utils

import android.view.View
import com.nepoapp.nepoplay20.databinding.ActivityMainBinding

object UiUtils {

    fun uiMusicPlaying(bind : ActivityMainBinding){
        bind.buttonPlay.visibility = View.GONE
        bind.buttonPause.visibility = View.VISIBLE
        bind.buttonStop.visibility = View.VISIBLE
    }

    fun uiMusicPaused(bind : ActivityMainBinding){
        bind.buttonPlay.visibility = View.VISIBLE
        bind.buttonPause.visibility = View.GONE
        bind.buttonStop.visibility = View.VISIBLE
    }

    fun uiMusicStopped(bind : ActivityMainBinding){
        bind.buttonPlay.visibility = View.VISIBLE
        bind.buttonPause.visibility = View.GONE
        bind.buttonStop.visibility = View.GONE
    }
}