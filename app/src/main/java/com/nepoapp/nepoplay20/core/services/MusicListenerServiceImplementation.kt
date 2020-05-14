package com.nepoapp.nepoplay20.core.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.nepoapp.nepoplay20.core.binders.MusicBinder
import com.nepoapp.nepoplay20.core.listeners.MusicListenerService
import java.io.FileInputStream
import java.lang.Exception
import java.lang.reflect.Executable
/**
 * Created in 13/05/2020 as 21:32
 *   by Lucas Nepomuceno Santos
 *
 * Recommend read about service: https://developer.android.com/guide/components/services
 * **/
class MusicListenerServiceImplementation :
    Service(),
    MusicListenerService {

    private lateinit var mediaPlayer: MediaPlayer
    private var isPaused: Boolean = false
    private var currentMusic: String? = null

    companion object {

        private val TAG = this::class.java.simpleName

        val FLAG_ACTION = "FLAG_ACTION"
        val FLAG_FILE = "FLAG_FILE"
        val FLAG_ACTION_PLAY = "FLAG_ACTION_PLAY"
        val FLAG_ACTION_STOP = "FLAG_ACTION_STOP"
        val FLAG_ACTION_PAUSE = "FLAG_ACTION_PAUSE"
    }

    /** first call **/
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer()
    }

    /** when system init an bind with activity **/
    override fun onBind(intent: Intent?): IBinder? {

        // listener of communication with actions view
        val listener: MusicListenerService = this
        return MusicBinder(listener)
    }

    /**  second call, when start service **/
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if (intent != null) {
            var actions = intent.getStringExtra(FLAG_ACTION)
            var filePath = intent.getStringExtra(FLAG_FILE)

            when (actions) {
                FLAG_ACTION_PLAY -> play(filePath)
                FLAG_ACTION_STOP -> pause()
                FLAG_ACTION_PAUSE -> stop()
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override var currentSong: String? = null
        get() = currentMusic

    override var totalTime: Int = 0
        get() = if (mediaPlayer.isPlaying || isPaused) mediaPlayer.duration else 0

    override var currentTime: Int = 0
        get() = if (mediaPlayer.isPlaying || isPaused) mediaPlayer.currentPosition else 0

    override fun play(path: String) {

        if (path.isBlank()) return;

        if (!mediaPlayer.isPlaying && !isPaused) {
            try {

                val fileInput = FileInputStream(path)

                mediaPlayer.reset()
                mediaPlayer.setDataSource(fileInput.fd) // open file
                mediaPlayer.prepare()
                mediaPlayer.start()

            } catch (e: Exception) {
                Log.i(
                    TAG,
                    "Occurred an problem in reproduction music, validate file selected : $path"
                )
                return
            }
        }

        currentMusic = path
        isPaused = false
    }

    override fun pause() {
        try {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                isPaused = true
            }
        } catch (e: Exception) {
            Log.i(TAG, "Occurred an problem in pause current music...")
            return
        }
    }

    override fun stop() {
        try {

            when((mediaPlayer.isPlaying || isPaused)){
                true ->{
                    isPaused = false
                    mediaPlayer.stop()
                    mediaPlayer.reset()
                }
                false ->{
                    Log.i(TAG, "impossible stop current music...")
                }

            }

        } catch (e: Exception) {
            Log.i(TAG, "Occurred an problem in stop current music...")
            return
        }
    }

}