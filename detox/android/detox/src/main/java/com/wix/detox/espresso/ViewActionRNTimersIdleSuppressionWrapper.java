package com.wix.detox.espresso;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import com.wix.detox.ReactNativeSupport;

import org.hamcrest.Matcher;

public class ViewActionRNTimersIdleSuppressionWrapper implements ViewAction {
    private final ViewAction espressoViewAction;

    ViewActionRNTimersIdleSuppressionWrapper(ViewAction clickAction) {
        this.espressoViewAction = clickAction;
    }

    @Override
    public Matcher<View> getConstraints() {
        return espressoViewAction.getConstraints();
    }

    @Override
    public String getDescription() {
        return espressoViewAction.getDescription();
    }

    @Override
    public void perform(UiController uiController, View view) {
        ReactNativeSupport.pauseRNTimersIdlingResource();
        try {
            espressoViewAction.perform(uiController, view);
        } finally {
            ReactNativeSupport.resumeRNTimersIdlingResource();
        }
        uiController.loopMainThreadUntilIdle();
    }
}
