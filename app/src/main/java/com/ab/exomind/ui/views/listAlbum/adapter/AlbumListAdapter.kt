package com.ab.exomind.ui.views.listAlbum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ab.exomind.R
import com.ab.exomind.databinding.AlbumItemBinding
import com.ab.exomind.model.Album
import com.ab.exomind.ui.views.listAlbum.viewmodel.AlbumViewModel
import com.ab.exomind.ui.views.listUsers.adapter.UserListAdapter

/**
 * Created by Aya Boussaadia on 22,February,2021
 */
class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.ViewHolder>() {
    private lateinit var listener: OnAlbumClickListener
    private lateinit var allAlbums: List<Album>

    interface OnAlbumClickListener {
        fun onAlbumClicked(album: Album, view: View)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AlbumItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.album_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allAlbums[position])
        holder.itemView.setOnClickListener {
            listener.onAlbumClicked(allAlbums[position], holder.itemView)
        }

    }

    override fun getItemCount(): Int {
        return if (::allAlbums.isInitialized) allAlbums.size else 0
    }


    fun updateAlbumList(albums: List<Album>) {
        this.allAlbums = albums
        notifyDataSetChanged()

    }

    fun setListener(listener: AlbumListAdapter.OnAlbumClickListener) {
        this.listener = listener
    }

    class ViewHolder(private val binding: AlbumItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = AlbumViewModel()

        fun bind(album: Album) {
            viewModel.bind(album)
            binding.viewModel = viewModel

        }
    }
}