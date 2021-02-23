package com.ab.exomind.ui.views.listAlbum.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ab.exomind.R
import com.ab.exomind.databinding.FragmentAlbumListByUserBinding
import com.ab.exomind.model.User
import com.ab.exomind.ui.views.factory.ViewModelFactory
import com.ab.exomind.ui.views.listAlbum.viewmodel.AlbumListByUserViewModel
import com.google.android.material.snackbar.Snackbar


class AlbumListByUserFragment : Fragment() {
    private var errorSnackbar: Snackbar? = null
    private lateinit var binding: FragmentAlbumListByUserBinding
    private lateinit var viewModel: AlbumListByUserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_album_list_by_user,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this, ViewModelFactory(activity as AppCompatActivity))
            .get(AlbumListByUserViewModel::class.java)
        binding.albumByUserViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        getDataFromUserListFragment()

        viewModel.errorMessage.observe(viewLifecycleOwner, { errorMessage ->
            if (errorMessage != null) showError(errorMessage)
            else hideError()
        })

        binding.albumList.layoutManager = LinearLayoutManager(activity)

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
        val user: User? =
            AlbumListByUserFragmentArgs.fromBundle(requireArguments()).argFromUserFragment

        if (user != null) {
            viewModel.loadAlbums(user.id)
        }


    }

}