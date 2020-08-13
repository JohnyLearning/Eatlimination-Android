package com.ihadzhi.eatlimination.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.Food;
import com.ihadzhi.eatlimination.data.SymptomRecord;
import com.ihadzhi.eatlimination.databinding.FragmentHomeBinding;
import com.ihadzhi.eatlimination.databinding.FragmentRecordingsBinding;
import com.ihadzhi.eatlimination.viewmodel.ViewRecordingsViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ViewRecordingsFragment extends BaseFragment {

    private FragmentRecordingsBinding dataBinding;
    private ViewRecordingsViewModel viewRecordingsViewModel;
    private ViewRecordingsAdapter viewRecordingsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_recordings, container, false);
        setHasOptionsMenu(true);
        viewRecordingsViewModel = ViewModelProviders.of(getActivity()).get(ViewRecordingsViewModel.class);
        viewRecordingsAdapter = new ViewRecordingsAdapter(getActivity(), food -> {
            // TODO: define action for food selection
        });
        setTitle(getString(R.string.recordings_title, "High blood pressure"));
        setHasOptionsMenu(true);
        showBackButton();
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideBackButton();
        hideNavigation();
        viewRecordingsViewModel.getRecordings().observe(getActivity(), records -> {
            if (records != null && records.size() > 0) {
                setupRecordsContent(records);
            }
        });
    }

    private void addFoodsAction() {
        NavHostFragment.findNavController(this).navigate(HomeFragmentDirections.actionHomeFragmentToFoodSearchFragment());
    }

    private void setupRecordsContent(@NotNull List<SymptomRecord> records) {
        if (records != null && records.size() > 0) {
            viewRecordingsAdapter.setRecords(records);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            dataBinding.recordsList.setLayoutManager(layoutManager);
            dataBinding.recordsList.setAdapter(viewRecordingsAdapter);
        }
    }

}
