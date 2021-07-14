package com.example.facebookloginpage.RoomDAO;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookloginpage.R;
import com.example.facebookloginpage.SQLiteDatabashHelper.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.invoke.ConstantCallSite;
import java.util.ArrayList;
import java.util.List;

public class ContactManager2Activity extends AppCompatActivity {
    private FloatingActionButton btnFloat;
    private RecyclerView recyclerView;
//    private ContactDBHelper helper;
    private ContactDAO contactDAO;
    private ContactAdapter2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_manager);
//        helper = new ContactDBHelper(this);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
//        adapter = new ContactAdapter(this, helper.getAllContacts());
        findView();
        setListener();
        initiaRecycleView();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        adapter.updateData(helper.getAllContacts());
        refreshAdapter();
    }

    public void findView (){
        btnFloat = findViewById(R.id.btnFloat);
        recyclerView = findViewById(R.id.recycleView);
    }

    public void setListener(){
        btnFloat.setOnClickListener(view -> {
            Intent i = new Intent(this, AddContact2Activity.class);
            startActivity(i);
        });
    }

    public void initiaRecycleView(){
        List<Contact2> emptyContact = new ArrayList<>();
        adapter = new ContactAdapter2(ContactManager2Activity.this, emptyContact);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void refreshAdapter(){
        new Thread(()-> {
            List<Contact2> contact2 = contactDAO.getAllContacts();
            runOnUiThread(()-> adapter.updateData(contact2));
        }).start();
    }
}