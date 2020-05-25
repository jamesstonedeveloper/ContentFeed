package com.jamesstonedeveloper.contentfeed.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jamesstonedeveloper.contentfeed.R
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        setContentView(R.layout.activity_main)
    }
}
