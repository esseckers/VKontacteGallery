package com.vkontactegallery;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.vk.sdk.VKSdk;
import com.vkontactegallery.push.RegistrationIntentService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TheApplication extends Application {

    private static final String USER_ID = "user_id";
    private static TheApplication sApplication;
    private SharedPreferences sharedPreferences;

    public static TheApplication getInstance() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        VKSdk.initialize(this);
    }

    public SharedPreferences getPreferences() {
        if (sharedPreferences == null) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        return sharedPreferences;
    }

    public String getUserId() {
        return getPreferences().getString(USER_ID, "");
    }

    public void setUserId(String userId) {
        getPreferences().edit().putString(USER_ID, userId).apply();
    }
}
