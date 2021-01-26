package com.ihadzhi.eatlimination.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.Symptom
import com.ihadzhi.eatlimination.data.SymptomRecord
import com.ihadzhi.eatlimination.databinding.FragmentRecordingsBinding
import com.ihadzhi.eatlimination.viewmodel.ViewRecordingsViewModel

class ViewRecordingsFragment : BaseFragment() {
    private var dataBinding: FragmentRecordingsBinding? = null
    private var viewRecordingsViewModel: ViewRecordingsViewModel? = null
    private var viewRecordingsAdapter: ViewRecordingsAdapter? = null
    private var symptom: Symptom? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recordings, container, false)
        symptom = ViewRecordingsFragmentArgs.fromBundle(requireArguments()).symptom
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(getString(R.string.recordings_title, symptom!!.name))
        setHasOptionsMenu(true)
        showBackButton()
        hideNavigation()
        viewRecordingsViewModel = ViewModelProvider(this).get(ViewRecordingsViewModel::class.java)
        viewRecordingsViewModel?.getRecordings(symptom?.id ?: -1)?.
            removeObservers(requireActivity())
        viewRecordingsViewModel?.getRecordings(symptom?.id ?: -1)?.
            observe(requireActivity(), Observer { records: List<SymptomRecord>? ->
                if (records?.isNotEmpty() == true) {
                    setupRecordsContent(records)
                }
            })
    }

    private fun setupRecordsContent(records: List<SymptomRecord>) {
        if (records?.isNotEmpty()) {
            viewRecordingsAdapter?.setRecords(records)
            val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            dataBinding!!.recordsList.layoutManager = layoutManager
            dataBinding!!.recordsList.adapter = viewRecordingsAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(ViewRecordingsFragmentDirections.backToSymptomsFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}