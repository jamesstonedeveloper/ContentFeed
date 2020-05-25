package com.jamesstonedeveloper.contentfeed.ui.addpost

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.base.BaseFragment
import com.jamesstonedeveloper.contentfeed.databinding.AddPostFragmentBinding
import com.jamesstonedeveloper.contentfeed.databinding.AddPostFragmentBindingImpl

class AddPostFragment : BaseFragment<AddPostFragmentBinding>() {
    override fun inflateBinding(layoutInflater: LayoutInflater): AddPostFragmentBinding = AddPostFragmentBinding.inflate(layoutInflater)
}