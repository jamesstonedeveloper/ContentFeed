package com.jamesstonedeveloper.contentfeed.data.api

import com.google.firebase.firestore.FirebaseFirestore
import com.jamesstonedeveloper.contentfeed.data.entities.Post

class PostsAPI {
    private val database = FirebaseFirestore.getInstance()

    fun uploadNewPost(post: Post, apiCallback: APIResponseCallback) {
        database.collection("posts")
            .add(post)
            .addOnSuccessListener {
                apiCallback.onSuccess()
            }
            .addOnFailureListener {
                apiCallback.onFailure("Failed to upload post")
            }
    }

}