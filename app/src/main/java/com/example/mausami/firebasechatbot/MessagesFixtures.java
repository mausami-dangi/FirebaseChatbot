package com.example.mausami.firebasechatbot;

import com.example.mausami.firebasechatbot.model.Message;
import com.example.mausami.firebasechatbot.model.User;

/*
 * Created by Mausami on 19 Jun 2018.
 */
public final class MessagesFixtures {

    private MessagesFixtures() {
        throw new AssertionError();
    }

    public static Message sendTextMessage(String text, String token) {
        return new Message("MSG001", sendUser(token), text);
    }

    public static Message sendImageMessage(String url,String token) {
        Message message = new Message("MSG001", sendUser(token), null);
        message.setImage(new Message.Image(url));
        return message;
    }

    public static Message receiveTextMessage(String text) {
        return new Message("RECEIVE-001", receiveUser(), text);
    }

    private static User sendUser(String token) {
        return new User(token, "Mosu", "", true);
    }

    private static User receiveUser(){
        return new User("AECOM-BOT1","AECOM1","https://engineering.unl.edu/images/staff/Kayla_Person-small.jpg",true);

    }
}
