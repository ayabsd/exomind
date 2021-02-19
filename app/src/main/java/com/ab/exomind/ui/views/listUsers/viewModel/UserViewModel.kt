package com.ab.exomind.ui.views.listUsers.viewModel

import androidx.lifecycle.MutableLiveData
import com.ab.exomind.model.User
import com.ab.exomind.ui.views.base.BaseViewModel

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

class UserViewModel : BaseViewModel() {
    private val albumId = MutableLiveData<String>()
    private val name = MutableLiveData<String>()
    private val pseudo = MutableLiveData<String>()
    private val email = MutableLiveData<String>()
    private val phone = MutableLiveData<String>()
    private val site = MutableLiveData<String>()


    fun bind(user: User) {
        name.value = user.name
        pseudo.value = user.username
        phone.value = user.phone
        email.value = user.email
        site.value = user.website
    }

    fun getName(): MutableLiveData<String> {
        return name
    }

    fun getPseudo(): MutableLiveData<String> {
        return pseudo
    }

    fun getEmail(): MutableLiveData<String> {
        return email
    }

    fun getPhone(): MutableLiveData<String> {
        return phone
    }

    fun getSite(): MutableLiveData<String> {
        return site
    }


}