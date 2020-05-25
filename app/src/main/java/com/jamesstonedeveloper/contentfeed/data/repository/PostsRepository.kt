package com.jamesstonedeveloper.contentfeed.data.repository

import com.jamesstonedeveloper.contentfeed.data.entities.Post

class PostsRepository {

    fun getPostsFromDB(): List<Post> {
        val dummyList = mutableListOf<Post>()
        for (i in 0..10) {
            val dummyString: String = i.toString()
            dummyList.add(Post(dummyString, "Test Post", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sed interdum ante. Suspendisse ultrices sit."))
        }
        return dummyList
    }

}