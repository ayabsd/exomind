package com.ab.exomind.ui.views.factory

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.ab.exomind.local.AppDatabase
import com.ab.exomind.ui.views.listUsers.viewModel.UserListViewModel

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            val db =
                Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "posts")
                    .build()
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}