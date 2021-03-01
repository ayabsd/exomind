package com.ab.exomind.ui.views.listUsers.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.ab.exomind.R
import com.ab.exomind.local.UserDAO
import com.ab.exomind.model.User
import com.ab.exomind.network.ApiService
import com.ab.exomind.ui.views.base.BaseViewModel
import com.ab.exomind.ui.views.listUsers.adapter.UserListAdapter
import com.ab.exomind.ui.views.listUsers.view.UserFragmentDirections
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Aya Boussaadia on 19,February,2021
 */


class UserListViewModel(private val userDao: UserDAO) : BaseViewModel() {
    @Inject
    lateinit var api: ApiService
    val userListAdapter: UserListAdapter = UserListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadUsers() }
    private lateinit var subscription: Disposable
    private val listener = object : UserListAdapter.OnUserClickListener {
        override fun onUserClicked(user: User, view: View) {
           Navigation.findNavController(view).navigate(
                UserFragmentDirections
                    .actionUserListFragmentToAlbumListFragment(user)
            )
        }
    }

    init {
        loadUsers()
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

    private fun loadUsers() {
        subscription = Observable.fromCallable {
            userDao.allUsers
        }
            .concatMap { dbPostList ->
                if (
                    dbPostList.isEmpty()
                )
                    api.getUsers().concatMap { apiPostList ->
                        userDao.insertAllUsers(*apiPostList.toTypedArray())
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
        userListAdapter.setListener(listener)

    }

    private fun onRetrieveAlbumListError() {
        errorMessage.value = R.string.user_error_retrieve
    }

    fun resetList(filterStr: String?) {
        userListAdapter.filter.filter(filterStr)

    }

     fun onDesstroy() {

    }


}