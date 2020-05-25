package com.jamesstonedeveloper.contentfeed.ui.feed

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.databinding.FeedFragmentBinding
import com.jamesstonedeveloper.contentfeed.base.BaseFragment

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    private val viewModel: FeedViewModel by viewModels()
    private val feedListAdapter = FeedListAdapter()

    override fun inflateBinding(layoutInflater: LayoutInflater): FeedFragmentBinding = FeedFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        binding.feedRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.feedRv.adapter = feedListAdapter
    }

    override fun observeViewModel() {
        super.observeViewModel()
        binding.viewModel = viewModel

        viewModel.postsList.observe(this, Observer {
            feedListAdapter.submitList(it)
        })

        viewModel.addPostClicked.observe(this, Observer {
            findNavController().navigate(R.id.action_feedFragment_to_addPostFragment)
        })
    }

}