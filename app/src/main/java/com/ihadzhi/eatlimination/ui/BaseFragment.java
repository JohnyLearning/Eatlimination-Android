package com.ihadzhi.eatlimination.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    private ContainerInteractor containerInteractor;

    public void showBackButton() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void hideBackButton() {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ContainerInteractor) {
            containerInteractor = (ContainerInteractor) context;
        }
    }

    public void showNavigation() {
        containerInteractor.showBottomNavigation();
    }

    public void hideNavigation() {
        containerInteractor.hideBottomNavigation();
    }
}
