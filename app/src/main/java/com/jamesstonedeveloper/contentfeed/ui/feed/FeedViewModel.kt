package com.jamesstonedeveloper.contentfeed.ui.feed

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamesstonedeveloper.contentfeed.data.api.PostsAPI
import com.jamesstonedeveloper.contentfeed.data.api.SyncCallback
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.data.repository.PostsRepository
import com.jamesstonedeveloper.contentfeed.utils.RealmUtils
import com.jamesstonedeveloper.contentfeed.utils.SingleLiveEvent
import io.realm.RealmChangeListener
import io.realm.RealmResults

class FeedViewModel : ViewModel() {
    val postsList: MutableLiveData<List<Post>> = MutableLiveData()
    val addPostClicked: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val syncFailed: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val showRefreshing: MutableLiveData<Boolean> = MutableLiveData()
    private val postsRepository = PostsRepository()
    private val postsRealmListener = RealmChangeListener<RealmResults<Post>> { results ->
        postsList.postValue(RealmUtils().copyListFromRealm(results))
    }

    private fun getPostsFromDB() {
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

    fun startSync() {
        showRefreshing.postValue(true)
        getPostsFromDB()
        PostsAPI().syncAllPosts(object : SyncCallback {
            override fun onSyncSuccess() {
                showRefreshing.postValue(false)
            }

            override fun onSyncFailure(message: String) {
                syncFailed.call()
                showRefreshing.postValue(false)
            }
        })
    }

}