package com.ab.exomind.ui.views.albumByUser.viewmodel

import androidx.lifecycle.MutableLiveData
import com.ab.exomind.model.Album
import com.ab.exomind.ui.views.base.BaseViewModel

/**
 * Created by Aya Boussaadia on 22,February,2021
 */
class AlbumViewModel : BaseViewModel() {
    private val albumId = MutableLiveData<String>()
    private val title = MutableLiveData<String>()


    fun bind(album: Album) {

        albumId.value = "#" + album.id.toString()
        title.value = album.title
    }

    fun getAlbumId(): MutableLiveData<String> {
        return albumId
    }

    fun getAlbumTitle(): MutableLiveData<String> {
        return title
    }


}