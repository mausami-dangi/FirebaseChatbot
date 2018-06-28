package com.example.mausami.firebasechatbot.retrofit;

import com.example.mausami.firebasechatbot.MainActivity;
import com.example.mausami.firebasechatbot.helpers.SharedPreferencesHelper;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by mausami on 15/05/2018.
 */

public interface APIInterface {



    @POST("/auth/login/")
    Call<ResponseBody> login(@Body JsonObject json);



//    @Headers({
//            "Authorization: Token "
//    })
    @POST("/m/chat/")
    Call<ResponseBody> sendNotification(@Body JsonObject json,@Header("Authorization") String contentRange);

//    @Headers({
//            "Content-Type: application/json",
//            "Authorization: key=AAAAGJRobSc:APA91bE_j5VvaFXcKYC-rb0a6rl6Kqv13VzbpKwvR7V23u6YaR2RGFs5DGxsFg7qqC_TvH0Vzsj7iWFJW8hgkyw84U-04SGM-2qGQAE8-kcSQsP0RjUGnwnfsURb5nVar6U1XLyXiF8w"
//    })
//    @POST("/fcm/send")
//    Call<ResponseBody> sendNotification(@Body JsonObject json);
}
