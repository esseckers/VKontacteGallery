package com.vkontactegallery.view.activity;

import com.vkontactegallery.R;
import com.vkontactegallery.tranzit.MainTransitManager;

public class MainActivity extends AbstractActivity {

    @Override
    protected void createTransitManager() {
        transitManager = new MainTransitManager(this);
    }

    @Override
    protected int getActivityLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected int getMainFragmentContainerRes() {
        return R.id.fragment_container;
    }
}
