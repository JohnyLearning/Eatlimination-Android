package com.ihadzhi.eatlimination.ui;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ihadzhi.eatlimination.R;
import com.ihadzhi.eatlimination.viewmodel.FoodsIncludedViewModel;

public class FoodsIncludedFragment extends BaseFragment {

    private FoodsIncludedViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.foods_included_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showBackButton();
        showNavigation();
        mViewModel = ViewModelProviders.of(this).get(FoodsIncludedViewModel.class);
        // TODO: Use the ViewModel
    }

}