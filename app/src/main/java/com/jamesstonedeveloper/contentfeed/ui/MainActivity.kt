package com.jamesstonedeveloper.contentfeed.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.data.api.PostsAPI
import com.jamesstonedeveloper.contentfeed.data.api.SyncCallback
import com.jamesstonedeveloper.contentfeed.utils.InternetUtils
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InternetUtils(this)
        startSync()
        setContentView(R.layout.activity_main)
    }

    private fun startSync() {
        PostsAPI().syncAllPosts(object : SyncCallback {
            override fun onSyncSuccess() {}

            override fun onSyncFailure(message: String) {}
        })
    }

}
