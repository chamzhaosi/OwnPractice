package com.example.facebookloginpage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final String MY_PREF_NAME = "usersAccount";
    private OkHttpClient client = new OkHttpClient();
    private EditText edtUserEmail, edtPassword;
    private TextView forgetPassword;
    private Button btnLogin, btnOtrAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        setOnClickButton();
        checkUserLoggedIn();
    }

    private void findView(){
        edtUserEmail = findViewById(R.id.edtUserEmail);
        edtPassword = findViewById(R.id.edtPassword);
        forgetPassword = findViewById(R.id.forgetPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnOtrAcc = findViewById(R.id.btnOtrAcc);
    }

    private void setOnClickButton(){
        btnLogin.setOnClickListener(veiw -> {

            Request request = new Request.Builder()
                    .url("https://api.jsonbin.io/b/60dc19c09328b059d7b32d9d/1")
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    String res = response.body().string();

                    runOnUiThread(()->{
                        boolean verify = false;
                        String inputUserEmail = edtUserEmail.getText().toString();
                        String inputPassword = edtPassword.getText().toString();

                        try{
                            JSONObject usersObject = new JSONObject(res);
                            JSONArray usersArray = usersObject.getJSONArray("account");

                            for (int i = 0; i<usersArray.length(); i++){
                                JSONObject user = usersArray.getJSONObject(i);

                                if(user.getString("email").equals(inputUserEmail)){
                                    if(user.getString("password").equals(inputPassword)){
                                        verify = true;
                                        saveToSharedPreference(user);
                                        Intent intent = new Intent(MainActivity.this, UsersInfoActivty.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                        break; // stop the for loop
                                    }
                                }
                            }if(verify==false) {
                                Toast.makeText(MainActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    });
                }
            });
        });
    }

    public void saveToSharedPreference(JSONObject user){
        try{
            SharedPreferences pref = this.getSharedPreferences(MainActivity.MY_PREF_NAME, Context.MODE_PRIVATE);
            pref.edit()
                    .putString("id", user.getString("id"))
                    .putString("name",user.getString("username"))
                    .putString("email", user.getString("email"))
                    .putString("colorBackground", user.getString("colorBackground"))
                    .putString("photo",user.getString("photo"))
                    .apply();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkUserLoggedIn(){
        SharedPreferences pres = getSharedPreferences(MY_PREF_NAME, MODE_PRIVATE);
        String name = pres.getString("name",null);
        if (name != "" && name != null){
            Intent intent = new Intent(this, UsersInfoActivty.class);
            startActivity(intent);
            finish();
        }
    }

}