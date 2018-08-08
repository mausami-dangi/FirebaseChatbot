package com.example.mausami.firebasechatbot;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import com.example.mausami.firebasechatbot.helpers.SharedPreferencesHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;


/**
 * Created by mausami on 15/05/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FDM_SERVICE";
    String messageStr = null,payloadStr = null,pageStr = null,stepStr = null,sessionStr = null,textStr,messageString;

    @SuppressLint("WrongThread")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Log data to Log Cat
        Log.i(TAG, "From: " + remoteMessage.getFrom());
        Log.i(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        try {
            JSONObject jsonObject = new JSONObject(remoteMessage.getNotification().getBody());
            textStr = jsonObject.getString("text");
            try {
                JSONObject textJsonObject = new JSONObject(textStr);
                messageStr = textJsonObject.getString("message");
                payloadStr = textJsonObject.getString("payload");

                String lineSep = System.getProperty("line.separator");
                messageString = messageStr;
                messageString = messageString.replaceAll("</br>", lineSep);

                // Remove all HTML tags
                messageString = messageString.replaceAll("\\<.*?>","");

                try {
                    JSONObject payloadJsonObject = new JSONObject(payloadStr);
                    pageStr = payloadJsonObject.getString("page");
                    stepStr = payloadJsonObject.getString("step");
                    sessionStr = payloadJsonObject.getString("session");
                } catch (Throwable tx) {
                    Log.e("My App", "Could not parse malformed JSON: \"" + textStr + "\"");
                }
            } catch (Throwable tx) {
                Log.e("My App", "Could not parse malformed JSON: \"" + textStr + "\"");
            }
        } catch (Throwable tx) {
            Log.e("My App", "Could not parse malformed JSON: \"" + remoteMessage.getNotification().getBody() + "\"");
        }

        SharedPreferencesHelper.putString(SharedPreferencesHelper.MESSAGE_STRING,messageString,getApplicationContext());
        SharedPreferencesHelper.putString(SharedPreferencesHelper.PAYLOAD_STRING,payloadStr,getApplicationContext());
        SharedPreferencesHelper.putString(SharedPreferencesHelper.PAGE_STRING,pageStr,getApplicationContext());
        SharedPreferencesHelper.putString(SharedPreferencesHelper.STEP_STRING,stepStr,getApplicationContext());
//        SharedPreferencesHelper.putString(SharedPreferencesHelper.STEP_STRING,stepStr,getApplicationContext());
           SharedPreferencesHelper.putString(SharedPreferencesHelper.SESSION_STRING,sessionStr,getApplicationContext());


      //  createNotification(remoteMessage.getNotification().getTitle(), messageString);

        showMessage(remoteMessage.getFrom(), messageString);

    }

    private void createNotification(String title, String messageBody) {

        Intent intent = new Intent( this , MainActivity. class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent resultIntent = PendingIntent.getActivity( this , 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder( this, "CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageString)
                .setAutoCancel( true )
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());

        return;
    }

    private void showMessage(String from, String messageBody) {
        SharedPreferencesHelper.putString(SharedPreferencesHelper.MESSAGE_BODY, from, getApplicationContext());
        startService(new Intent(getApplicationContext(),MyShowMessageService.class));
    }
}
