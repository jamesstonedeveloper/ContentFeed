package com.jamesstonedeveloper.contentfeed.ui.addpost

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.base.BaseFragment
import com.jamesstonedeveloper.contentfeed.databinding.AddPostFragmentBinding
import com.jamesstonedeveloper.contentfeed.databinding.AddPostFragmentBindingImpl

class AddPostFragment : BaseFragment<AddPostFragmentBinding>() {
    private val viewModel: AddPostViewModel by viewModels()
    override fun inflateBinding(layoutInflater: LayoutInflater): AddPostFragmentBinding = AddPostFragmentBinding.inflate(layoutInflater)

    override fun observeViewModel() {
        super.observeViewModel()
        binding.addPostViewModel = viewModel
        
        viewModel.showUploadIndicator.observe(this, Observer {

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