package com.example.Wk4_Broadcasts.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.Wk4_Broadcasts.R
import kotlinx.android.synthetic.main.bottom_frame_layout.*

class Bottom_Fragment: Fragment() {

    lateinit var bottom_receiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_frame_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(
                Glide.with(this).asGif()
                    .load("https://media2.giphy.com/media/6b9QApjUesyOs/giphy.gif?cid=790b7611731caa60f740117c920655da38aef8e61fd5ad96&rid=giphy.gif")
                    .into(bottom_imageView))
    }


    override fun onStart() {
        super.onStart()
        bottom_receiver = object: BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                var Give = intent?.getStringExtra("mic")
                bottom_textview.text = Give


            }

        }

        val bottomFilter = IntentFilter("RickRoll")
        val main = activity as MainActivity?

        main?.registerReceiver(bottom_receiver, bottomFilter)


    }

    override fun onDestroy() {

        val main = activity as MainActivity?

        main?.unregisterReceiver(bottom_receiver)
        super.onDestroy()

    }
}