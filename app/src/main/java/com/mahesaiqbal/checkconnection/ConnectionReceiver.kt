package com.mahesaiqbal.checkconnection

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectionReceiver : BroadcastReceiver() {

    companion object {
        var connectionReceiverListener: ConnectionReceiverListener? = null
        val isConnected: Boolean
            get() {
                val cm = MyApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

                val activeNetwork = cm.activeNetworkInfo

                return (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
            }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnected = checkConnection(context)

        if (connectionReceiverListener != null) {
            connectionReceiverListener!!.onNetworkConnectionChanged(isConnected)
        }
    }

    private fun checkConnection(context: Context?): Boolean {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo

        return (activeNetwork != null && activeNetwork.isConnectedOrConnecting)
    }

    interface ConnectionReceiverListener {
        fun onNetworkConnectionChanged(isConnected: Boolean)
    }
}