package com.example.Wk4_Broadcasts.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.Wk4_Broadcasts.R

class PlayBand : Service(){

    lateinit var mediaPlayer: MediaPlayer
    lateinit var myBinder: PlayBandBinder

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG_X", "onCreate - Service")
        mediaPlayer = MediaPlayer.create(this, R.raw.rickroll)
        mediaPlayer.isLooping = true
    }

//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Log.d("TAG_X", "onStart - Service")
//
//
//        mediaPlayer = MediaPlayer.create(this, R.raw.sheriff_theme)
//        mediaPlayer.isLooping = true
//        return super.onStartCommand(intent, flags, startId)
//
//    }


    override fun onBind(intent: Intent?): IBinder? {
        Log.d("TAG_X", "onBind - Service")
        mediaPlayer.start()
        myBinder = PlayBandBinder()
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        mediaPlayer.stop()
        return super.onUnbind(intent)
    }

    fun playPressed(){
        if (!mediaPlayer.isPlaying)
            mediaPlayer.start()
    }

    fun pausePressed(){
        if(mediaPlayer.isPlaying)
            mediaPlayer.pause()
    }

    fun stopPressed(){
        mediaPlayer.reset()
    }

    override fun onDestroy() {
        Log.d("TAG_X", "onDestroy - Service")

        mediaPlayer.stop()
        super.onDestroy()
    }


    inner class PlayBandBinder: Binder() {
        fun getMusicService() : PlayBand {
            return this@PlayBand
        }
    }
}