package com.ab.exomind.ui.views.listPhotos.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ab.exomind.model.Photo
import com.ab.exomind.ui.views.base.BaseViewModel

/**
 * Created by Aya Boussaadia on 23,February,2021
 */

class PhotoViewModel : BaseViewModel() {

    private val photoTitle = MutableLiveData<String>()
    private val photoImage = MutableLiveData<String>()

    fun bind(photo: Photo){
        photoTitle.value = "Photo " + photo.id
        photoImage.value = photo.url
    }

    fun getPhotoTitle(): MutableLiveData<String> {
        return photoTitle
    }

    fun getPhoto(): MutableLiveData<String> {
        return photoImage
    }
}