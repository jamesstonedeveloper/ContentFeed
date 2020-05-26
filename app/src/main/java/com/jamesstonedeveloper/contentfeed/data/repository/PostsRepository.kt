package com.jamesstonedeveloper.contentfeed.data.repository

import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.utils.RealmUtils
import io.realm.RealmResults

class PostsRepository {
    private val realmUtils = RealmUtils()

    fun getPostsFromDB(): RealmResults<Post> {
        return realmUtils.getAllAsRealmList(Post::class.java)
    }

}