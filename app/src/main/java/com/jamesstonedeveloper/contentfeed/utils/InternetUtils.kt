package com.jamesstonedeveloper.contentfeed.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class InternetUtils() {
    constructor(newContext: Context) : this() {
        context = newContext
        instance = this
        setUpConnectivityManager()
    }

    private fun setUpConnectivityManager() {
        context?.let {
            val connectivityManager =
                it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkRequest = NetworkRequest.Builder()
                .build()
            connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
        }
    }

    private var context: Context? = null
    private var isInternetAvailable: Boolean = false
    private var networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onLost(network: Network?) {
            isInternetAvailable = false
            //record wi-fi disconnect event
        }
        override fun onUnavailable() {
            isInternetAvailable = false
        }
        override fun onLosing(network: Network?, maxMsToLive: Int) {
        }
        override fun onAvailable(network: Network?) {
            isInternetAvailable = true
            //record wi-fi connect event
        }
    }

    companion object{
        var instance = InternetUtils()
    }

    fun isConnected(): Boolean {
       return isInternetAvailable
    }
}