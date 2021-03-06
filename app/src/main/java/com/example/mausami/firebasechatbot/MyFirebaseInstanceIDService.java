package com.example.mausami.firebasechatbot;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.mausami.firebasechatbot.helpers.SharedPreferencesHelper;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by mausami on 15/05/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);

    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPreferencesHelper.putString(SharedPreferencesHelper.FIREBASE_TOKEN,refreshedToken,getApplicationContext());

    }
}
