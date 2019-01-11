package com.mahesaiqbal.checkconnection

import android.app.Application

class MyApplication : Application() {

    companion object {
        @get:Synchronized
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun setConnectionListener(listener: ConnectionReceiver.ConnectionReceiverListener) {
        ConnectionReceiver.connectionReceiverListener = listener
    }
}