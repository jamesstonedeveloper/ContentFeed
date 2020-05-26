package com.jamesstonedeveloper.contentfeed.ui.feed

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.databinding.FeedFragmentBinding
import com.jamesstonedeveloper.contentfeed.base.BaseFragment

class FeedFragment : BaseFragment<FeedFragmentBinding>() {
    private val viewModel: FeedViewModel by viewModels()
    private val feedListAdapter = FeedListAdapter()

    override fun inflateBinding(layoutInflater: LayoutInflater): FeedFragmentBinding = FeedFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()
        toolbar?.setDisplayHomeAsUpEnabled(false)
        toolbar?.setDisplayShowHomeEnabled(false)
        binding.feedRv.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.feedRv.adapter = feedListAdapter
        binding.feedSwipeRefreshLayout.setOnRefreshListener {
            viewModel.startSync()
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        binding.viewModel = viewModel

        viewModel.postsList.observe(this, Observer {
            feedListAdapter.submitList(it)
            binding.feedRv.smoothScrollToPosition(0)
        })

        viewModel.addPostClicked.observe(this, Observer {
            findNavController().navigate(R.id.action_feedFragment_to_addPostFragment)
        })

        viewModel.showRefreshing.observe(this, Observer {
            binding.feedSwipeRefreshLayout.isRefreshing = it
        })

        viewModel.syncFailed.observe(this, Observer {
            Snackbar.make(binding.root, R.string.server_connection_failure, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.snackbar_retry)) {
                    viewModel.startSync()
                }.show()
        })
    }



}