package com.ihadzhi.eatlimination.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.EatliminationDatabase
import com.ihadzhi.eatlimination.data.Symptom
import com.ihadzhi.eatlimination.data.SymptomRecord
import com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory
import com.ihadzhi.eatlimination.databinding.SymptomRecordFragmentBinding
import com.ihadzhi.eatlimination.viewmodel.SymptomRecordViewModel
import java.util.concurrent.Executors

class SymptomRecordFragment : BaseFragment() {
    private var viewModel: SymptomRecordViewModel? = null
    private var dataBinding: SymptomRecordFragmentBinding? = null
    private var symptom: Symptom? = null
    private var selectedCategory: SymptomCategory? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.symptom_record_fragment, container, false)
        hideNavigation()
        setHasOptionsMenu(true)
        symptom = SymptomRecordFragmentArgs.fromBundle(requireArguments()).symptom
        dataBinding?.symptom = symptom
        if (savedInstanceState != null) {
            val category = savedInstanceState.getInt(CATEGORY_PARAM, -1)
            if (category >= 0) {
                selectedCategory = SymptomCategory.values()[category]
            }
        } else {
            selectedCategory = SymptomCategory.green
        }
        return dataBinding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SymptomRecordViewModel::class.java)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (selectedCategory != null) {
            outState.putInt(CATEGORY_PARAM, selectedCategory?.ordinal ?: -1)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(getString(R.string.new_recording_title, "High blood pressure"))
        dataBinding?.symptomImage?.setImageDrawable(requireContext().getDrawable(R.drawable.hbp))
        dataBinding?.symptomDescription?.text = "Quick zephyrs blow, vexing daft Jim. Sphinx of black quartz, judge my vow. Two driven jocks help fax my big quiz. Five quacking zephyrs jolt my wax bed. The five boxing wizards jump quickly."
        dataBinding?.greenStatus?.setOnClickListener { changesSymptomStatus(SymptomCategory.green) }
        dataBinding?.yellowStatus?.setOnClickListener { changesSymptomStatus(SymptomCategory.yellow) }
        dataBinding?.redStatus?.setOnClickListener { changesSymptomStatus(SymptomCategory.red) }
        dataBinding?.saveAction?.setOnClickListener { createRecord(dataBinding?.newValue?.text.toString()) }
        dataBinding?.newValue?.onFocusChangeListener = OnFocusChangeListener { _: View?, hasFocus: Boolean ->
            if (!hasFocus) {
                hideSoftKeyboard()
            }
        }
    }

    private fun validate(newValue: String): Boolean {
        val recordValue = dataBinding?.newValue?.text.toString()
        return if (!TextUtils.isEmpty(recordValue)) {
            true
        } else {
            hideLoadingIndicator()
            showError(R.string.value_empty)
            false
        }
    }

    private fun createRecord(newValue: String) {
        showLoadingIndicator()
        if (validate(newValue)) {
            val db = EatliminationDatabase.getInstance(activity)
            val dao = db.symptomRecordDao()
            Executors.newSingleThreadScheduledExecutor().execute {
                dao.insert(SymptomRecord(selectedCategory, newValue, symptom?.id ?: -1))
                hideLoadingIndicator()
                NavHostFragment.findNavController(this).navigate(SymptomRecordFragmentDirections.backToSymptoms())
            }
        }
    }

    private fun changesSymptomStatus(category: SymptomCategory) {
        when (category) {
            SymptomCategory.green -> {
                dataBinding?.greenStatus?.background = getDrawable(R.drawable.symptom_status_green_background)
                dataBinding?.redStatus?.background = getDrawable(R.drawable.red_status_inactive)
                dataBinding?.yellowStatus?.background = getDrawable(R.drawable.yellow_status_inactive)
            }
            SymptomCategory.yellow -> {
                dataBinding?.greenStatus?.background = getDrawable(R.drawable.green_status_inactive)
                dataBinding?.redStatus?.background = getDrawable(R.drawable.red_status_inactive)
                dataBinding?.yellowStatus?.background = getDrawable(R.drawable.symptom_status_yellow_background)
            }
            SymptomCategory.red -> {
                dataBinding?.greenStatus?.background = getDrawable(R.drawable.green_status_inactive)
                dataBinding?.redStatus?.background = getDrawable(R.drawable.symptom_status_red_background)
                dataBinding?.yellowStatus?.background = getDrawable(R.drawable.yellow_status_inactive)
            }
            else -> {
            }
        }
        selectedCategory = category
    }

    private fun getDrawable(@DrawableRes drawable: Int): Drawable? {
        return requireContext().getDrawable(drawable)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(SymptomRecordFragmentDirections.backToSymptoms())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val CATEGORY_PARAM = "selectedCategory"
    }
}