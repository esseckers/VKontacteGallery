package com.vkontactegallery.view.activity;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vkontactegallery.R;
import com.vkontactegallery.VKUtils;
import com.vkontactegallery.push.RegistrationIntentService;
import com.vkontactegallery.transit.MainTransitManager;
import com.vkontactegallery.view.fragment.LoginFragment;

public class MainActivity extends AbstractActivity implements VKCallback<VKAccessToken> {

    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void initView() {
        VKSdk.login(this, VKUtils.SCOPE);
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter("registrationComplete"));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, this)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onResult(VKAccessToken vkAccessToken) {
        vkAccessToken.save();
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, RegistrationIntentService.class);
                startService(intent);
            }
        });
    }

    @Override
    public void onError(VKError vkError) {

    }

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
