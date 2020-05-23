package com.jamesstonedeveloper.contentfeed.ui.feed

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.databinding.FeedFragmentBinding
import com.jamesstonedeveloper.contentfeed.ui.base.BaseFragment

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): FeedFragmentBinding = FeedFragmentBinding.inflate(layoutInflater)


}