package com.jamesstonedeveloper.contentfeed.data.api

interface APIResponseCallback {
    fun onSuccess(message: String = "")
    fun onFailure(message: String = "")
}

interface SyncCallback {
    fun onSyncSuccess()
    fun onSyncFailure(message: String)
}