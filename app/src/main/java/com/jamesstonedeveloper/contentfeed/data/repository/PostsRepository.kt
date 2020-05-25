package com.jamesstonedeveloper.contentfeed.data.repository

import com.jamesstonedeveloper.contentfeed.data.entities.Post

class PostsRepository {

    fun getPostsFromDB(): List<Post> {
        val dummyList = mutableListOf<Post>()
        for (i in 0..10) {
            val dummyString: String = i.toString()
            dummyList.add(Post(dummyString, dummyString, dummyString))
        }
        return dummyList
    }

}