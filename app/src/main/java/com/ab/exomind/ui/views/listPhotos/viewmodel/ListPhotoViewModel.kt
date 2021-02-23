package com.ab.exomind.ui.views.listPhotos.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.ab.exomind.R
import com.ab.exomind.local.UserDAO
import com.ab.exomind.model.Album
import com.ab.exomind.model.Photo
import com.ab.exomind.model.User
import com.ab.exomind.network.ApiService
import com.ab.exomind.ui.views.base.BaseViewModel
import com.ab.exomind.ui.views.listPhotos.adapter.PhotoListAdapter
import com.ab.exomind.ui.views.listUsers.adapter.UserListAdapter
import com.ab.exomind.ui.views.listUsers.view.UserFragmentDirections
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 23,February,2021
 */

class ListPhotoViewModel(private val userDao: UserDAO) : BaseViewModel() {
    @Inject
    lateinit var api: ApiService
    val photoAdapter: PhotoListAdapter = PhotoListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private lateinit var subscription: Disposable




    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

     fun loadPhotos(idAlbum : Int) {
        subscription = Observable.fromCallable {
            userDao.getAlbumPhoto(idAlbum)
        }
            .concatMap { dbPostList ->
                if (
                    dbPostList.isEmpty()
                )
                    api.getPhotos(idAlbum).concatMap { apiPostList ->
                        userDao.insertAllPhotos(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveAlbumListStart() }
            .doOnTerminate { onRetrieveAlbumListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrieveAlbumListError() }
            )
    }

    private fun onRetrieveAlbumListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrieveAlbumListFinish() {
        loadingVisibility.value = View.GONE
    }


    private fun onRetrievePostListSuccess(photos: List<Photo>) {
        photoAdapter.updatePhotoList(photos)

    }

    private fun onRetrieveAlbumListError() {
        errorMessage.value = R.string.user_error_retrieve
    }


}