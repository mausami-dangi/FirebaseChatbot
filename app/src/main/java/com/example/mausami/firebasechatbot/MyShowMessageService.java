package com.example.mausami.firebasechatbot;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.mausami.firebasechatbot.helpers.SharedPreferencesHelper;
import com.example.mausami.firebasechatbot.model.Message;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import static com.example.mausami.firebasechatbot.DemoMessagesActivity.messagesAdapter;
//import com.example.mausami.firebasechatbot.model.Message;
//import com.example.mausami.firebasechatbot.model.User;
//
//import static com.example.mausami.firebasechatbot.MainActivity.adapter;

public class MyShowMessageService extends Service {
    public MyShowMessageService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        try {
            String from = SharedPreferencesHelper.getString(SharedPreferencesHelper.MESSAGE_FROM, getApplicationContext());
            String messageBody = SharedPreferencesHelper.getString(SharedPreferencesHelper.MESSAGE_BODY, getApplicationContext());
            String messageString = SharedPreferencesHelper.getString(SharedPreferencesHelper.MESSAGE_STRING,getApplicationContext());
            messagesAdapter.addToStart(MessagesFixtures.receiveTextMessage(messageString),true);


        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}


