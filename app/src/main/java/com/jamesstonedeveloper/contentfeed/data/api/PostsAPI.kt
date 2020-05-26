package com.jamesstonedeveloper.contentfeed.data.api

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.utils.InternetUtils
import com.jamesstonedeveloper.contentfeed.utils.PostsOutdatedManager
import com.jamesstonedeveloper.contentfeed.utils.RealmUtils
import com.jamesstonedeveloper.contentfeed.utils.StringUtils
import java.util.*

class PostsAPI {
    private val database = FirebaseFirestore.getInstance()

    fun uploadNewPost(post: Post, apiCallback: APIResponseCallback) {
        if (InternetUtils.instance.isConnected()) {
            val ref = database.collection("posts").document()
            post.id = ref.id
            ref.set(post)
                    .addOnCompleteListener {
                        if (it.exception != null) {
                            apiCallback.onFailure(StringUtils.instance.getString(R.string.upload_post_failed) ?: "")
                            return@addOnCompleteListener
                        }
                        if (it.isSuccessful) {
                            apiCallback.onSuccess()
                        } else {
                            apiCallback.onFailure(StringUtils.instance.getString(R.string.upload_post_failed) ?: "")
                        }
                    }
        } else {
            apiCallback.onFailure(StringUtils.instance.getString(R.string.no_internet_connection) ?: "")
        }

    }

    fun syncAllPosts(syncErrorCallback: SyncCallback) {
        if (InternetUtils.instance.isConnected()) {
            database.collection("posts")
                    .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                        if (firebaseFirestoreException != null) {
                            syncErrorCallback.onSyncFailure(StringUtils.instance.getString(R.string.server_connection_failure) ?: "")
                            return@addSnapshotListener
                        }

                        val updatedAtTime = Date().time
                        val syncedList: MutableList<Post> = mutableListOf()
                        for (document in querySnapshot!!) {
                            val post: Post = document.toObject(Post::class.java)
                            post.updatedAt = updatedAtTime
                            syncedList.add(post)
                        }
                        RealmUtils().writeListToRealm(syncedList)

                        PostsOutdatedManager().deleteOutdatedPosts(updatedAtTime)
                        syncErrorCallback.onSyncSuccess()
                    }
        } else {
            syncErrorCallback.onSyncFailure(StringUtils.instance.getString(R.string.no_internet_connection) ?: "")
        }
    }

}