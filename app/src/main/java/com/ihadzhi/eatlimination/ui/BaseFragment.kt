package com.ihadzhi.eatlimination.ui

import android.content.Context
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    private var containerInteractor: ContainerInteractor? = null
    fun showBackButton() {
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideBackButton() {
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    fun setTitle(@StringRes title: Int) {
        (activity as AppCompatActivity?)?.supportActionBar?.setTitle(title)
    }

    fun setTitle(title: CharSequence?) {
        (activity as AppCompatActivity?)?.supportActionBar?.title = title
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerInteractor = if (context is ContainerInteractor) {
            context
        } else {
            throw IllegalStateException("context should be instance of ContainerInteractor")
        }
    }

    fun showNavigation() {
        containerInteractor?.showBottomNavigation()
    }

    fun hideNavigation() {
        containerInteractor?.hideBottomNavigation()
    }

    fun showLoadingIndicator() {
        containerInteractor?.showLoadingIndicator()
    }

    fun hideLoadingIndicator() {
        containerInteractor?.hideLoadingIndicator()
    }

    fun hideSoftKeyboard() {
        containerInteractor?.hideSoftKeyboard()
    }

    fun showGenericError() {
        containerInteractor?.errorGeneric()
    }

    fun showError(@StringRes errorMessage: Int) {
        containerInteractor?.error(errorMessage)
    }
}