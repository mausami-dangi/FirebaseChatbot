package com.example.mausami.firebasechatbot;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.mausami.firebasechatbot.model.Message;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.io.File;

/*
 * Created by Mausami on 19 Jun 2018.
 */
public abstract class DemoMessagesActivity extends AppCompatActivity {

    protected ImageLoader imageLoader;
    public static MessagesListAdapter<Message> messagesAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, String url) {
                if (url != null){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if (url.contains("http")){
                            Picasso.with(DemoMessagesActivity.this).load(url).into(imageView);
                        }
                        else {
                            File f = new File(url);
                            Picasso.with(DemoMessagesActivity.this).load(f).into(imageView);
                        }
                    }
                }
            }
        };
    }
}
