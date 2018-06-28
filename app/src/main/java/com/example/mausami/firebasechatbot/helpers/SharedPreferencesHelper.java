package com.example.mausami.firebasechatbot.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by mausami on 15/05/2018.
 */

public class SharedPreferencesHelper {
    public static String FIREBASE_TOKEN = "FIREBASE_TOKEN";
    public static String MESSAGE_FROM = "MESSAGE_FROM";
    public static String MESSAGE_BODY = "MESSAGE_BODY";
    public static String LOGIN_TOKEN = "LOGIN_TOKEN";
    public static String MESSAGE_STRING = "MESSAGE_STRING";
    public static String PAYLOAD_STRING = "PAYLOAD_STRING";
    public static String PAGE_STRING = "PAGE_STRING";
    public static String STEP_STRING = "STEP_STRING";
    public static String SESSION_STRING = "SESSION_STRING";

    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;

    public static void initSharedPreference(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    public static void putString(String key, String value, Context context){
        if (sharedPreferences == null){
            initSharedPreference(context);
        }
        editor.putString( key, value).commit();
    }

    public static String getString(String key, Context context){
        if (sharedPreferences == null){
            initSharedPreference(context);
        }
        return sharedPreferences.getString(key,"");
    }
}
