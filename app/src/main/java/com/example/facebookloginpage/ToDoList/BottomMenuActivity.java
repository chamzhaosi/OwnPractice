package com.example.facebookloginpage.ToDoList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.facebookloginpage.R;

import java.util.ArrayList;

public class BottomMenuActivity extends AppCompatActivity {
    private LinearLayout btnListUndo, btnListAdd, btnListDone;
    private ArrayList<LinearLayout> btnColor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);
        findView();
        setListeners();
        btnListUndo.performClick();
    }

    private void findView(){
        btnListAdd = findViewById(R.id.btnListAdd);
        btnListDone = findViewById(R.id.btnlistDone);
        btnListUndo = findViewById(R.id.btnListUndo);

        btnColor.add(btnListDone);
        btnColor.add(btnListAdd);
        btnColor.add(btnListUndo);
    }

    private void setListeners(){
        btnListUndo.setOnClickListener(view -> {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.FrameContainer, new ListUndoFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#a8bee3"));

            for (LinearLayout btn : btnColor){
                if (btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        btnListAdd.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FrameContainer, new ListAddFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#a8bee3"));

            for (LinearLayout btn : btnColor){
                if (btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });

        btnListDone.setOnClickListener(view -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.FrameContainer, new ListDoneFragment())
                    .commit();

            view.setBackgroundColor(Color.parseColor("#a8bee3"));

            for (LinearLayout btn : btnColor){
                if (btn.getId() != view.getId()){
                    btn.setBackgroundColor(Color.parseColor("#ffffff"));
                }
            }
        });
    }
}