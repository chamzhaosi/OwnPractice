package com.example.facebookloginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class UsersInfoActivty extends AppCompatActivity {
    private ImageView imgPhoto;
    private TextView tvName, tvEmail, tvColor;
    private LinearLayout infoLayout;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_info_activty);
        findView();
        setListeners();
        initializeInterface();
    }

    private void findView(){
        imgPhoto = findViewById(R.id.imgPhoto);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvColor = findViewById(R.id.tvColor);
        infoLayout = findViewById(R.id.infoLaylout);
        btnLogout = findViewById(R.id.btnLogout);
    }

    private void setListeners(){
        btnLogout.setOnClickListener(view ->{
            SharedPreferences pref = getSharedPreferences(MainActivity.MY_PREF_NAME, MODE_PRIVATE);
            pref.edit().clear().apply();
            Intent i = new Intent(this, UsersInfoActivty.class);
            startActivity(i);
            finish();
        });
    }

    private void initializeInterface(){
        SharedPreferences pref = getSharedPreferences(MainActivity.MY_PREF_NAME, Context.MODE_PRIVATE);
        String name = pref.getString("name", "");
        String email = pref.getString("email", "");
        String color = pref.getString("colorBackground", "#ffffff");
        String photo = pref.getString("photo", "");

        tvName.setText(name);
        tvEmail.setText(email);
        tvColor.setText(color);
        Picasso.get().load(photo).into(imgPhoto);

        String colorCode = "#ffffff";

        if (color.equals("blue")){
            colorCode = "#57b7f7";
        }else if (color.equals("red")){
            colorCode = "#b54338";
        }else if (color.equals("pulpe")){
            colorCode = "#583bb8";
        }

        infoLayout.setBackgroundColor(Color.parseColor(colorCode));
    }


}