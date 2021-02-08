package com.ihadzhi.eatlimination.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ihadzhi.eatlimination.R
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import android.content.DialogInterface
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_start.*

open class StartActivity : AppCompatActivity(), ContainerInteractor {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(bottom_navigation, navController)
    }

    override fun hideBottomNavigation() {
        bottom_navigation.visibility = View.GONE
    }

    override fun showBottomNavigation() {
        bottom_navigation.visibility = View.VISIBLE
    }

    override fun showLoadingIndicator() {
        progress_bar.visibility = View.VISIBLE
        progress_bar.animate()
    }

    override fun hideLoadingIndicator() {
        progress_bar.visibility = View.GONE
    }

    override fun hideSoftKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
    }

    override fun errorGeneric() {
        error(R.string.generic_error)
    }

    override fun error(errorMessage: Int) {
        val alertDialog = AlertDialog.Builder(this).create()
        alertDialog.setMessage(getString(errorMessage))
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok_button)) { dialog: DialogInterface?, which: Int -> alertDialog.dismiss() }
        alertDialog.setCanceledOnTouchOutside(true)
        alertDialog.show()
    }
}