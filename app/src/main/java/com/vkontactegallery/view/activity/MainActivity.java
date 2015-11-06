package com.vkontactegallery.view.activity;

import android.app.Fragment;

import com.vkontactegallery.R;
import com.vkontactegallery.transit.MainTransitManager;
import com.vkontactegallery.view.fragment.AlbumsFragment;
import com.vkontactegallery.view.fragment.LoginFragment;

public class MainActivity extends AbstractActivity {

    @Override
    protected Class<? extends Fragment> getFragmentForStart() {
        return LoginFragment.class;
    }

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
