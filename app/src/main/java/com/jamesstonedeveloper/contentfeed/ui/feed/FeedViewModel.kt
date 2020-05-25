package com.jamesstonedeveloper.contentfeed.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.data.repository.PostsRepository

class FeedViewModel : ViewModel() {
    val postsList: MutableLiveData<List<Post>> = MutableLiveData()
    private val postsRepository = PostsRepository()

    init {
        loadPostsList()
    }

    private fun loadPostsList() {
        postsList.postValue(postsRepository.getPostsFromDB())
    }
}