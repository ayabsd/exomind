package com.ab.exomind.ui.views.base

import androidx.lifecycle.ViewModel
import com.ab.exomind.injection.component.DaggerViewModelInjector
import com.ab.exomind.injection.component.ViewModelInjector
import com.ab.exomind.injection.module.NetworkModule
import com.ab.exomind.ui.views.listAlbum.viewmodel.AlbumListByUserViewModel
import com.ab.exomind.ui.views.listAlbum.viewmodel.AlbumViewModel
import com.ab.exomind.ui.views.listPhotos.viewmodel.ListPhotoViewModel
import com.ab.exomind.ui.views.listPhotos.viewmodel.PhotoViewModel
import com.ab.exomind.ui.views.listUsers.viewModel.UserListViewModel
import com.ab.exomind.ui.views.listUsers.viewModel.UserViewModel

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {

        inject()
    }

    private fun inject() {
        when (this) {
            is UserListViewModel -> injector.inject(this)
            is UserViewModel -> injector.inject(this)
            is AlbumListByUserViewModel -> injector.inject(this)
            is AlbumViewModel -> injector.inject(this)
            is ListPhotoViewModel -> injector.inject(this)
            is PhotoViewModel -> injector.inject(this)

        }
    }
}