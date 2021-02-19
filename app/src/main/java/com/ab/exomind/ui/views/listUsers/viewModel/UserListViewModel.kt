package com.ab.exomind.ui.views.listUsers.viewModel

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.ab.exomind.R
import com.ab.exomind.local.UserDAO
import com.ab.exomind.model.User
import com.ab.exomind.network.ApiService
import com.ab.exomind.ui.views.base.BaseViewModel
import com.ab.exomind.ui.views.listUsers.adapter.UserListAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 19,February,2021
 */


class UserListViewModel(private val userDao: UserDAO):BaseViewModel() {
    @Inject
    lateinit var api: ApiService
    val userListAdapter: UserListAdapter = UserListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUsers() }
    private lateinit var subscription: Disposable

    val isLoading = ObservableBoolean()

    fun onRefresh() {
        isLoading.set(true)
        //loadPhotos()
    }

    private fun onReady() = isLoading.set(false)


  /*  private val listener = object: AlbumListAdapter.OnAlbumClickListener {
        override fun onCustomItemClicked(album: Album , view : View) {
            Navigation.findNavController(view).navigate(AlbumListFragmentDirections
                .actionAlbumFragmentToPhotoFragment(album))
        }
    }*/
    init {
        loadUsers()
    }
    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

  private fun loadUsers() {
        subscription = Observable.fromCallable {
            userDao.all
        }
            .concatMap { dbPostList ->
                if (
                    dbPostList.isEmpty()
                )
                    api.getUsers().concatMap { apiPostList ->
                        userDao.insertAll(*apiPostList.toTypedArray())
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

   private fun onRetrievePostListSuccess(userList: List<User>) {
        userListAdapter.updateAlbumList(userList)
    }

    private fun onRetrieveAlbumListError() {
        errorMessage.value = R.string.user_error_retrieve
    }


}