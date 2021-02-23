package com.ab.exomind.ui.views.listPhotos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ab.exomind.R
import com.ab.exomind.databinding.PhotoItemBinding
import com.ab.exomind.model.Photo
import com.ab.exomind.ui.views.listPhotos.viewmodel.PhotoViewModel

/**
 * Created by Aya Boussaadia on 23,February,2021
 */

class PhotoListAdapter : RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {
    private lateinit var allPhotos: List<Photo>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PhotoItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allPhotos[position])


    }

    override fun getItemCount(): Int {
        return if (::allPhotos.isInitialized) allPhotos.size else 0
    }


    fun updatePhotoList(photos: List<Photo>) {
        this.allPhotos = photos
        notifyDataSetChanged()

    }


    class ViewHolder(private val binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
          private val viewModel = PhotoViewModel()

        fun bind(photo: Photo) {
         viewModel.bind(photo)
            binding.viewModelPhoto = viewModel

        }
    }
}