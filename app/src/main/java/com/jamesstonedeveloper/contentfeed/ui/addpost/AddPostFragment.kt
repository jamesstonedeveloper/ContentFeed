package com.jamesstonedeveloper.contentfeed.ui.addpost

import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.base.BaseFragment
import com.jamesstonedeveloper.contentfeed.databinding.AddPostFragmentBinding

class AddPostFragment : BaseFragment<AddPostFragmentBinding>() {
    private val viewModel: AddPostViewModel by viewModels()
    override fun inflateBinding(layoutInflater: LayoutInflater): AddPostFragmentBinding = AddPostFragmentBinding.inflate(layoutInflater)

    override fun setUpViews() {
        super.setUpViews()

        toolbar?.setDisplayHomeAsUpEnabled(true)
        toolbar?.setDisplayShowHomeEnabled(true)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        binding.addPostViewModel = viewModel
        
        viewModel.showUploadIndicator.observe(this, Observer {
            binding.addpostProgressBar.visibility = if (it) View.VISIBLE else View.GONE
            binding.addpostBtnUpload.isEnabled = !it
        })

        viewModel.errorMessage.observe(this, Observer {
            val snackBar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
            snackBar.setAction("Dismiss") {
                snackBar.dismiss()
            }
            snackBar.show()
        })

        viewModel.successMessage.observe(this, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        })
    }
}