package com.wix.detox.espresso;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;

public class DetoxViewActions {
    public static ViewAction click() {
        final ViewAction clickAction = ViewActions.click();
        return new ViewActionRNTimersIdleSuppressionWrapper(clickAction);
    }
}
