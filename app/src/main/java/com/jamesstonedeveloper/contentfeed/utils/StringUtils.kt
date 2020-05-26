package com.jamesstonedeveloper.contentfeed.utils

import android.content.Context

class StringUtils() {
    constructor(newContext: Context) : this() {
        context = newContext
        instance = this
    }

    companion object {
        var instance = StringUtils()
    }

    private var context: Context? = null

    fun getString(intID: Int): String? {
        context?.let {
           return it.resources.getString(intID)
        }
        return null
    }
}