package com.ihadzhi.eatlimination.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ihadzhi.eatlimination.R
import com.ihadzhi.eatlimination.data.Symptom
import com.ihadzhi.eatlimination.databinding.SymptomsFragmentBinding
import com.ihadzhi.eatlimination.viewmodel.SymptomsViewModel

class SymptomsFragment : BaseFragment() {
    private var symptomsViewModel: SymptomsViewModel? = null
    private var symptomsAdapter: SymptomsAdapter? = null
    private var dataBinding: SymptomsFragmentBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.symptoms_fragment, container, false)
        setHasOptionsMenu(true)
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitle(R.string.symptoms_title)
        showBackButton()
        showNavigation()
        symptomsViewModel = ViewModelProvider(this).get(SymptomsViewModel::class.java)
        symptomsAdapter = SymptomsAdapter(requireActivity(),
                { symptom: Symptom? ->
                    NavHostFragment.findNavController(this)
                            .navigate(SymptomsFragmentDirections.actionSymptomsFragmentToViewRecordingsFragment(symptom!!))
                },
                { symptom: Symptom? ->
                    NavHostFragment.findNavController(this)
                            .navigate(SymptomsFragmentDirections.actionSymptomsFragmentToSymptomRecordFragment(symptom!!))
                })
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        dataBinding?.symptomsList?.layoutManager = layoutManager
        dataBinding?.symptomsList?.adapter = symptomsAdapter
        symptomsViewModel?.symptoms?.observe(requireActivity(), Observer<List<Symptom?>> { symptoms: List<Symptom?>? -> symptomsAdapter?.setSymptoms(symptoms) })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(SymptomsFragmentDirections.actionSymptomsFragmentToHomeFragment())
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun newInstance(): SymptomsFragment {
            return SymptomsFragment()
        }
    }
}