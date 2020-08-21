package com.ihadzhi.eatlimination.ui;

import androidx.annotation.StringRes;

public interface ContainerInteractor {

    void hideBottomNavigation();

    void showBottomNavigation();

    void showLoadingIndicator();

    void hideLoadingIndicator();

    void hideSoftKeyboard();

    void errorGeneric();

    void error(@StringRes int errorMessage);

}
