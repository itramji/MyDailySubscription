package com.bornbytes.mydailysubscription.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bornbytes.mydailysubscription.api.RemoteDataSource
import com.bornbytes.mydailysubscription.repository.BaseRepository

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding, R : BaseRepository> : Fragment() {

    abstract fun getViewModelClass(): Class<VM>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    abstract fun getFragmentRepository(): R

    lateinit var binding: VB
    lateinit var viewModel: VM
    val remoteDataSource = RemoteDataSource()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModelClass())
        return binding.root
    }
}
