package com.nepoapp.nepoplay20.core.repositories

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Handler
import android.os.IBinder
import com.nepoapp.nepoplay20.core.binders.MusicBinder
import com.nepoapp.nepoplay20.core.listeners.MusicListenerService
/**
 * Created in 27/05/2020 as 19:26
 *   by Lucas Nepomuceno Santos
 *
 * **/
class MusicRepository() : ServiceConnection {
    private var service : MusicListenerService? = null
    private var handler: Handler = Handler()
    private lateinit var progress: Runnable

    fun getNameCurrentSong() = service?.currentSong ?: ""
    fun getElapsedTime() = service?.currentTime ?: 0
    fun getTimeTotal() = service?.totalTime ?: 0

    fun play(nameMusic : String):Boolean{
        handler.removeCallbacks(progress)

        return if (nameMusic.isNotBlank()){
            service?.play(nameMusic)
            true
        }else
            false
    }

    fun stop(stopListener : (progress : Int) -> Unit){
        service?.stop()
        stopListener.invoke(0)
    }

    fun pause(){
        service?.pause()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        this@MusicRepository.service = null
    }

    override fun onServiceConnected(name: ComponentName?, bind: IBinder?) {
        this@MusicRepository.service = ( bind as MusicBinder).musicListerService
    }
}