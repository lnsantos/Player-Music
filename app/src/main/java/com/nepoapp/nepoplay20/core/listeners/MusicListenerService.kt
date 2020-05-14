package com.nepoapp.nepoplay20.core.listeners

interface MusicListenerService {

    var currentSong : String?
    var totalTime   : Int
    var currentTime : Int

    fun play(path : String)
    fun pause()
    fun stop()


}