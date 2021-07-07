package com.example.facebookloginpage.contactRecycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.facebookloginpage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactManagerActivity extends AppCompatActivity {
    private FloatingActionButton btnFloat;
    private RecyclerView recyclerView;
    private ContactDBHelper helper;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
        helper = new ContactDBHelper(this);
        adapter = new ContactAdapter(this, helper.getAllContacts());
        findView();
        setListener();
        initiaRecycleView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateData(helper.getAllContacts());
    }

    public void findView (){
        btnFloat = findViewById(R.id.btnFloat);
        recyclerView = findViewById(R.id.recycleView);
    }

    public void setListener(){
        btnFloat.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContactActivity.class);
            startActivity(i);
        });

    }

    public void initiaRecycleView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}