package com.ihadzhi.eatlimination.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.data.EatliminationDatabase;
import com.ihadzhi.eatlimination.data.Symptom;
import com.ihadzhi.eatlimination.data.SymptomRecord;
import com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory;
import com.ihadzhi.eatlimination.data.SymptomRecordDao;
import com.ihadzhi.eatlimination.databinding.SymptomRecordFragmentBinding;
import com.ihadzhi.eatlimination.databinding.SymptomsFragmentBinding;
import com.ihadzhi.eatlimination.viewmodel.SymptomRecordViewModel;
import com.ihadzhi.eatlimination.viewmodel.SymptomsViewModel;

import java.util.concurrent.Executors;

import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.green;
import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.red;
import static com.ihadzhi.eatlimination.data.SymptomRecord.SymptomCategory.yellow;

public class SymptomRecordFragment extends BaseFragment {

    private static final String CATEGORY_PARAM = "selectedCategory";

    private SymptomRecordViewModel viewModel;
    private SymptomRecordFragmentBinding dataBinding;
    private Symptom symptom;
    private SymptomCategory selectedCategory;

    public static SymptomRecordFragment newInstance() {
        return new SymptomRecordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.symptom_record_fragment, container, false);
        hideNavigation();
        setHasOptionsMenu(true);
        symptom = SymptomRecordFragmentArgs.fromBundle(getArguments()).getSymptom();
        dataBinding.setSymptom(symptom);
        if (savedInstanceState != null) {
            int category = savedInstanceState.getInt(CATEGORY_PARAM, -1);
            if (category >= 0) {
                selectedCategory = SymptomCategory.values()[category];
            }
        } else {
            selectedCategory = green;
        }
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(SymptomRecordViewModel.class);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (selectedCategory != null) {
            outState.putInt(CATEGORY_PARAM, selectedCategory.ordinal());
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getString(R.string.new_recording_title, "High blood pressure"));
        dataBinding.symptomImage.setImageDrawable(getContext().getDrawable(R.drawable.hbp));
        dataBinding.symptomDescription.setText("Quick zephyrs blow, vexing daft Jim. Sphinx of black quartz, judge my vow. Two driven jocks help fax my big quiz. Five quacking zephyrs jolt my wax bed. The five boxing wizards jump quickly.");
        dataBinding.greenStatus.setOnClickListener(v -> changesSymptomStatus(green));
        dataBinding.yellowStatus.setOnClickListener(v -> changesSymptomStatus(SymptomCategory.yellow));
        dataBinding.redStatus.setOnClickListener(v -> changesSymptomStatus(red));
        dataBinding.saveAction.setOnClickListener(v -> {
            createRecord(dataBinding.newValue.getText().toString());
        });
        dataBinding.newValue.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                hideSoftKeyboard();
            }
        });
    }

    private boolean validate(String newValue) {
        String recordValue = String.valueOf(dataBinding.newValue.getText());
        if (!TextUtils.isEmpty(recordValue)) {
            return true;
        } else {
            hideLoadingIndicator();
            showError(R.string.value_empty);
            return false;
        }
    }

    private void createRecord(String newValue) {
        showLoadingIndicator();
        if (validate(newValue)) {
            final EatliminationDatabase db = EatliminationDatabase.getInstance(getActivity());
            SymptomRecordDao dao = db.symptomRecordDao();
            Executors.newSingleThreadScheduledExecutor().execute(() -> {
                dao.insert(new SymptomRecord(selectedCategory, newValue, symptom.getId()));
                hideLoadingIndicator();
                NavHostFragment.findNavController(this).navigate(SymptomRecordFragmentDirections.backToSymptoms());
            });
        }
    }

    private void changesSymptomStatus(SymptomCategory category) {
        switch (category) {
            case green:
                dataBinding.greenStatus.setBackground(getDrawable(R.drawable.symptom_status_green_background));
                dataBinding.redStatus.setBackground(getDrawable(R.drawable.red_status_inactive));
                dataBinding.yellowStatus.setBackground(getDrawable(R.drawable.yellow_status_inactive));
                break;
            case yellow:
                dataBinding.greenStatus.setBackground(getDrawable(R.drawable.green_status_inactive));
                dataBinding.redStatus.setBackground(getDrawable(R.drawable.red_status_inactive));
                dataBinding.yellowStatus.setBackground(getDrawable(R.drawable.symptom_status_yellow_background));
                break;
            case red:
                dataBinding.greenStatus.setBackground(getDrawable(R.drawable.green_status_inactive));
                dataBinding.redStatus.setBackground(getDrawable(R.drawable.symptom_status_red_background));
                dataBinding.yellowStatus.setBackground(getDrawable(R.drawable.yellow_status_inactive));
                break;
            default:
                // no-op
        }
        selectedCategory = category;
    }

    private Drawable getDrawable(@DrawableRes int drawable) {
        return getContext().getDrawable(drawable);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavHostFragment.findNavController(this).navigate(SymptomRecordFragmentDirections.backToSymptoms());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}