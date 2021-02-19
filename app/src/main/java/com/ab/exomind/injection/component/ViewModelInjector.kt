package com.ab.exomind.injection.component

import com.ab.exomind.injection.module.NetworkModule
import com.ab.exomind.ui.views.listUsers.viewModel.UserListViewModel
import com.ab.exomind.ui.views.listUsers.viewModel.UserViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Aya Boussaadia on 19,February,2021
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

    fun inject(userListViewModel: UserListViewModel)
    fun inject(userViewModel: UserViewModel)


    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}