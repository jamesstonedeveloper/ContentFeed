package com.jamesstonedeveloper.contentfeed.ui.addpost

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jamesstonedeveloper.contentfeed.R
import com.jamesstonedeveloper.contentfeed.data.api.APIResponseCallback
import com.jamesstonedeveloper.contentfeed.data.api.PostsAPI
import com.jamesstonedeveloper.contentfeed.data.entities.Post
import com.jamesstonedeveloper.contentfeed.utils.StringUtils

class AddPostViewModel : ViewModel() {
    private val postsAPI = PostsAPI()
    val titleInput: ObservableField<String> = ObservableField()
    val contentInput: ObservableField<String> = ObservableField()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    val successMessage: MutableLiveData<String> = MutableLiveData()
    val showUploadIndicator: MutableLiveData<Boolean> = MutableLiveData()
    
    fun uploadPost() {
        if (isPostValid()) {
            showUploadIndicator.postValue(true)
            postsAPI.uploadNewPost(Post(title = titleInput.get(), body = contentInput.get()), object: APIResponseCallback {
                override fun onSuccess(message: String) {
                    showUploadIndicator.postValue(false)
                    successMessage.postValue(StringUtils.instance.getString(R.string.add_post_success))
                }

                override fun onFailure(message: String) {
                    showUploadIndicator.postValue(false)
                    errorMessage.postValue(message)
                }

            })
        }
    }

    private fun isPostValid(): Boolean {
        if (titleInput.get().isNullOrBlank()) {
            errorMessage.postValue(StringUtils.instance.getString(R.string.prompt_enter_title) ?: "")
            return false
        }
        if (contentInput.get().isNullOrBlank()) {
            errorMessage.postValue(StringUtils.instance.getString(R.string.prompt_enter_content) ?: "")
            return false
        }
        return true
    }
}