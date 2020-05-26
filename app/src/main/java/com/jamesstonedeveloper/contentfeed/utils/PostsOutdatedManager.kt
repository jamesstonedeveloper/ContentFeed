package com.jamesstonedeveloper.contentfeed.utils

import com.jamesstonedeveloper.contentfeed.data.entities.Post

class PostsOutdatedManager {

    fun deleteOutdatedPosts(recentUpdatedTime: Long) {
            val posts: List<Post> = RealmUtils().getAllAsRealmList(Post::class.java)
            for (post in posts) {
                if (post.updatedAt != recentUpdatedTime) {
                    RealmUtils().deleteObjectFromRealm(post)
                }
            }


    }
}