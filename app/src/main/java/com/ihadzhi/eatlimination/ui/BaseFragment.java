package com.ihadzhi.eatlimination.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
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

    public void setTitle(@StringRes int title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    public void setTitle(CharSequence title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ContainerInteractor) {
            containerInteractor = (ContainerInteractor) context;
        } else {
            throw new IllegalStateException("context should be instance of ContainerInteractor");
        }
    }

    public void showNavigation() {
        containerInteractor.showBottomNavigation();
    }

    public void hideNavigation() {
        containerInteractor.hideBottomNavigation();
    }

    public void showLoadingIndicator() {
        containerInteractor.showLoadingIndicator();
    }

    public void hideLoadingIndicator() {
        containerInteractor.hideLoadingIndicator();
    }
}
