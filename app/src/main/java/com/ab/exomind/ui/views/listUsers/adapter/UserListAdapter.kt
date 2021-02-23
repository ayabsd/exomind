package com.ab.exomind.ui.views.listUsers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ab.exomind.R
import com.ab.exomind.databinding.UserItemBinding
import com.ab.exomind.model.User
import com.ab.exomind.ui.views.listUsers.viewModel.UserViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Aya Boussaadia on 19,February,2021
 */

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.ViewHolder>(), Filterable {
    private lateinit var listener: OnUserClickListener


    interface OnUserClickListener {
        fun onUserClicked(user: User, view: View)
    }

    private lateinit var allUsers: List<User>
    var userFilterList = ArrayList<User>()


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
        holder.bind(userFilterList[position])
        holder.itemView.setOnClickListener {
            listener.onUserClicked(userFilterList[position], holder.itemView)
        }

    }

    override fun getFilter(): Filter {
        userFilterList.clear()
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    userFilterList.addAll(allUsers)
                } else {
                    val resultList = ArrayList<User>()
                    for (row in allUsers) {
                        if (row.username.toLowerCase(Locale.ROOT).contains(
                                charSearch.toLowerCase(
                                    Locale.ROOT
                                )
                            )
                        ) {
                            resultList.add(row)
                        }
                    }
                    userFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = userFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                if (results?.values != null) {
                    userFilterList = results?.values as ArrayList<User>
                    notifyDataSetChanged()
                }
            }

        }
    }

    override fun getItemCount(): Int {
        return userFilterList.size
    }

    fun setListener(listener: UserListAdapter.OnUserClickListener) {
        this.listener = listener
    }

    fun updateAlbumList(users: List<User>) {
        this.allUsers = users
        userFilterList.addAll(users)
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