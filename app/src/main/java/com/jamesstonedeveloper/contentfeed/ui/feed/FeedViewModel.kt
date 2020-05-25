package com.jamesstonedeveloper.contentfeed.ui.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.data.repository.PostsRepository
import com.jamesstonedeveloper.contentfeed.utils.SingleLiveEvent
import io.realm.RealmChangeListener
import io.realm.RealmResults

class FeedViewModel : ViewModel() {
    val postsList: MutableLiveData<List<Post>> = MutableLiveData()
    val addPostClicked: SingleLiveEvent<Boolean> = SingleLiveEvent()
    private val postsRepository = PostsRepository()
    private val postsRealmListener = RealmChangeListener<RealmResults<Post>> {
        results -> postsList.postValue(results)
    }

    init {
        postsRepository.getPostsFromDB()
            .addChangeListener(postsRealmListener)
        postsRealmListener.onChange(postsRepository.getPostsFromDB())
    }

    override fun onCleared() {
        super.onCleared()
        postsRepository.getPostsFromDB().removeAllChangeListeners()
    }

    fun goToAddPost() {
        addPostClicked.call()
    }

}