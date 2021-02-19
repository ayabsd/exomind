package com.ab.exomind.ui.views.listUsers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ab.exomind.R
import com.ab.exomind.databinding.UserItemBinding
import com.ab.exomind.model.User
import com.ab.exomind.ui.views.listUsers.viewModel.UserViewModel

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private lateinit var allUsers: List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: UserItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.user_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allUsers[position])

    }

    override fun getItemCount(): Int {
        return if (::allUsers.isInitialized) allUsers.size else 0
    }

    fun updateAlbumList(users: List<User>) {
        this.allUsers = users
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: UserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = UserViewModel()

        fun bind(user: User) {
            viewModel.bind(user)
            binding.viewModel = viewModel

        }
    }
}