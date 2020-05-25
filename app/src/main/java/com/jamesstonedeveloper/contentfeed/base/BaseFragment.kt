package com.jamesstonedeveloper.contentfeed.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<VB: ViewDataBinding>: Fragment() {
    lateinit var binding: VB

    abstract fun inflateBinding(layoutInflater: LayoutInflater): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =inflateBinding(inflater)
        setUpViews()
        observeViewModel()
        return binding.root
    }

    protected open fun observeViewModel() {}
    protected open fun setUpViews(){}

}