package com.jamesstonedeveloper.contentfeed.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jamesstonedeveloper.contentfeed.ui.MainActivity

abstract class BaseFragment<VB: ViewDataBinding>: Fragment() {
    lateinit var binding: VB
    var toolbar: ActionBar? = null

    abstract fun inflateBinding(layoutInflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolbar = (requireActivity() as MainActivity).supportActionBar
        binding =inflateBinding(inflater)
        setUpViews()
        observeViewModel()
        return binding.root
    }

    protected open fun observeViewModel() {}
    protected open fun setUpViews(){}

}