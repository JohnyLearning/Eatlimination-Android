package com.ihadzhi.eatlimination.ui

import androidx.annotation.StringRes

interface ContainerInteractor {
    fun hideBottomNavigation()
    fun showBottomNavigation()
    fun showLoadingIndicator()
    fun hideLoadingIndicator()
    fun hideSoftKeyboard()
    fun errorGeneric()
    fun error(@StringRes errorMessage: Int)
}