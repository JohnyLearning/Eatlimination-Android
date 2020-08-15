package com.ihadzhi.eatlimination.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.databinding.SymptomRecordFragmentBinding;
import com.ihadzhi.eatlimination.databinding.SymptomsFragmentBinding;
import com.ihadzhi.eatlimination.viewmodel.SymptomRecordViewModel;
import com.ihadzhi.eatlimination.viewmodel.SymptomsViewModel;

public class SymptomRecordFragment extends BaseFragment {

    private SymptomRecordViewModel viewModel;
    private SymptomRecordFragmentBinding dataBinding;

    public static SymptomRecordFragment newInstance() {
        return new SymptomRecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.symptom_record_fragment, container, false);
        hideNavigation();
        setHasOptionsMenu(true);
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SymptomRecordViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getString(R.string.new_recording_title, "High blood pressure"));
        dataBinding.symptomImage.setImageDrawable(getContext().getDrawable(R.drawable.hbp));
        dataBinding.symptomDescription.setText("Quick zephyrs blow, vexing daft Jim. Sphinx of black quartz, judge my vow. Two driven jocks help fax my big quiz. Five quacking zephyrs jolt my wax bed. The five boxing wizards jump quickly.");
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