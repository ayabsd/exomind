package com.ab.exomind.ui.views.listPhotos.view

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.ab.exomind.R
import com.ab.exomind.databinding.FragmentListPhotoBinding
import com.ab.exomind.model.Album
import com.ab.exomind.ui.views.factory.ViewModelFactory
import com.ab.exomind.ui.views.listPhotos.viewmodel.ListPhotoViewModel
import com.google.android.material.snackbar.Snackbar


class ListPhotoFragment : Fragment() {

    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: FragmentListPhotoBinding
    private lateinit var viewModel: ListPhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_list_photo,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity))
            .get(ListPhotoViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        getDataFromUserListFragment()

        viewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
            if (errorMessage != null) showError(errorMessage)
            else hideError()
        })

        numberOfItemsInRecycle()
        return binding.root
    }


    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }


    private fun getDataFromUserListFragment() {

        val album: Album? =
            ListPhotoFragmentArgs.fromBundle(requireArguments()).argFromAlbumFragment

        if (album != null) {
            viewModel.loadPhotos(album.id)
        }

    }

    private fun numberOfItemsInRecycle() {

        if (this.resources
                .configuration.orientation === Configuration.ORIENTATION_PORTRAIT
        )
            binding.photoList.layoutManager = GridLayoutManager(activity, 3)
        else
            binding.photoList.layoutManager = GridLayoutManager(activity, 4)

    }

}