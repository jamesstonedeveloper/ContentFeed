package com.jamesstonedeveloper.contentfeed.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.data.api.PostsAPI
import com.jamesstonedeveloper.contentfeed.data.api.SyncCallback
import com.jamesstonedeveloper.contentfeed.databinding.ActivityMainBinding
import com.jamesstonedeveloper.contentfeed.utils.InternetUtils
import com.jamesstonedeveloper.contentfeed.utils.StringUtils
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        InternetUtils(this)
        StringUtils(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.toolbar.setTitleTextColor(getColor(android.R.color.white))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                findNavController(R.id.fragment).navigateUp()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
