package com.vkontactegallery.push;

import android.app.IntentService;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vkontactegallery.R;
import com.vkontactegallery.TheApplication;
import com.vkontactegallery.VKUtils;

import java.io.IOException;


/**
 * Created by Ivan Danilov on 23.12.2015.
 */
public class RegistrationIntentService extends IntentService {
    // abbreviated tag name
    private static final String TAG = "RegIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // Make a call to Instance API
        InstanceID instanceID = InstanceID.getInstance(this);
        String senderId = getResources().getString(R.string.gcm_defaultSenderId);
        try {
            // request token that will be used by the server to send push notifications
            String token = instanceID.getToken(senderId, GoogleCloudMessaging.INSTANCE_ID_SCOPE);
            Log.d(TAG, "GCM Registration Token: " + token);

            // pass along this data
            sendRegistrationToServer(token, Settings.Secure.getString(TheApplication.getInstance().getContentResolver(),
                    Settings.Secure.ANDROID_ID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRegistrationToServer(String token, String deviceId) {
        new VKRequest(VKUtils.ACCOUNT_REGISTER_DEVICE_METHOD_NAME, VKUtils.vkGetRegisterDeviceParams(token, deviceId))
                .executeSyncWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        Intent registrationComplete = new Intent("registrationComplete");
                        LocalBroadcastManager.getInstance(RegistrationIntentService.this).sendBroadcast(registrationComplete);
                    }

                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }
                });
    }
}
