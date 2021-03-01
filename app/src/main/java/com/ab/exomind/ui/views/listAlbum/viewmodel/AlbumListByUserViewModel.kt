package com.ab.exomind.ui.views.listAlbum.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.ab.exomind.R
import com.ab.exomind.local.UserDAO
import com.ab.exomind.model.Album
import com.ab.exomind.network.ApiService
import com.ab.exomind.ui.views.base.BaseViewModel
import com.ab.exomind.ui.views.listAlbum.adapter.AlbumListAdapter
import com.ab.exomind.ui.views.listAlbum.view.AlbumListByUserFragmentDirections
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 21,February,2021
 */

class AlbumListByUserViewModel(private val userDao: UserDAO) : BaseViewModel() {
    @Inject
    lateinit var api: ApiService
    val albumAdapter: AlbumListAdapter = AlbumListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    private lateinit var subscription: Disposable

    private val listener = object : AlbumListAdapter.OnAlbumClickListener {
        override fun onAlbumClicked(album: Album, view: View) {


            Navigation.findNavController(view).navigate(AlbumListByUserFragmentDirections
                    .actionAlbumListFragmentToPhotoListFragment(album)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }


     fun loadAlbums(id: String) {
        subscription = Observable.fromCallable {
            userDao.getUserAlbums(id)
        }
            .concatMap { dbPostList ->
                if (
                    dbPostList.isEmpty()
                )
                    api.getAlbums(id).concatMap { apiPostList ->
                        userDao.insertAllAlbums(*apiPostList.toTypedArray())
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


    private fun onRetrievePostListSuccess(albums: List<Album>) {
        albumAdapter.updateAlbumList(albums)
        albumAdapter.setListener(listener =listener )


    }

    private fun onRetrieveAlbumListError() {
        errorMessage.value = R.string.user_error_retrieve
    }


}