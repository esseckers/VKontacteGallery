package com.vkontactegallery.view.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.fsm.transit.core.ITransitManager;
import com.vkontactegallery.tranzit.FragmentAction;


public abstract class AbstractActivity extends AppCompatActivity {

    protected ITransitManager<FragmentAction> transitManager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayoutRes());
        createTransitManager();
        if (getTransitManager() != null) {
            getTransitManager().setCurrentContainer(getMainFragmentContainerRes());
        }
        if (savedInstanceState == null) {
            Class<? extends Fragment> clazz = getFragmentForStart();
            if (clazz != null && getTransitManager() != null) {
                getTransitManager().switchFragment(clazz, getIntent().getExtras(), false);
            }
        }
        initView();
    }

    protected void initView() {
    }

    public ITransitManager<FragmentAction> getTransitManager() {
        return transitManager;
    }

    protected Class<? extends Fragment> getFragmentForStart() {
        return null;
    }

    protected abstract void createTransitManager();

    protected abstract int getActivityLayoutRes();

    protected abstract int getMainFragmentContainerRes();

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1 && getTransitManager() != null)
            getTransitManager().back();
        else
            finish();
    }
}
