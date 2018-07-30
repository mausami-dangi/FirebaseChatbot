package com.example.mausami.firebasechatbot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mausami.firebasechatbot.helpers.SharedPreferencesHelper;
import com.example.mausami.firebasechatbot.retrofit.APIClient;
import com.example.mausami.firebasechatbot.retrofit.APIInterface;
import com.google.gson.JsonObject;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    public EditText edt_emailid,edt_password;
    Button btn_login;
    String loginTokenString;
    String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edt_emailid = (EditText) findViewById(R.id.edt_emailid);
        edt_password = (EditText) findViewById(R.id.edt_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        TOKEN = SharedPreferencesHelper.getString(SharedPreferencesHelper.FIREBASE_TOKEN, getApplicationContext());

        edt_emailid.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // Perform action on Enter key press
                    edt_emailid.clearFocus();
                    edt_password.requestFocus();
                    return true;
                }
                return false;
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_emailid.getText().toString().matches("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                } else if (edt_password.getText().toString().matches("")) {
                    Toast.makeText(LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                } else {

                    apiCall(edt_emailid.getText().toString(), edt_password.getText().toString(),TOKEN);
                }
            }
        });

    }

    private void apiCall(String email, String password, String tokenstr) {


        // Create complete json including notification object
        JsonObject json = new JsonObject();
        json.addProperty("email", email);
        json.addProperty("password",password);
        json.addProperty("token",tokenstr);

        // Call API
        retrofit2.Call<ResponseBody> loadChanges = APIClient.getClient()
                .create(APIInterface.class)
                .login(json);

        // Load Response
        loadChanges.enqueue(new Callback<ResponseBody>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        System.out.println(response.body().toString());
                        JSONObject object = null;
                        try {
                            object = new JSONObject(response.body().string());
                            loginTokenString = object.optString("token");
                            SharedPreferencesHelper.putString(SharedPreferencesHelper.LOGIN_TOKEN,loginTokenString,getApplicationContext());
                            Toast.makeText(LoginActivity.this, "Successfully Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Incorrect Email or Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                Log.i("CALL_STATUS", "CALL Fail");
            }
        });
    }
}
