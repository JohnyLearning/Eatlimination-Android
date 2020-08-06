package com.ihadzhi.eatlimination.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.databinding.SymptomsFragmentBinding;
import com.ihadzhi.eatlimination.viewmodel.SymptomsViewModel;

public class SymptomsFragment extends Fragment {

    private SymptomsViewModel symptomsViewModel;
    private SymptomsAdapter symptomsAdapter;
    private SymptomsFragmentBinding dataBinding;

    public static SymptomsFragment newInstance() {
        return new SymptomsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.symptoms_fragment, container, false);
        setHasOptionsMenu(true);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        symptomsViewModel = ViewModelProviders.of(this).get(SymptomsViewModel.class);
        symptomsAdapter = new SymptomsAdapter(getActivity(), symptoms -> {

        });
        symptomsViewModel.getSymptoms().observe(getActivity(), symptoms -> {
            symptomsAdapter.setSymptoms(symptoms);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            dataBinding.symptomsList.setLayoutManager(layoutManager);
            dataBinding.symptomsList.setAdapter(symptomsAdapter);
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(SymptomsFragmentDirections.backToHomeFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}