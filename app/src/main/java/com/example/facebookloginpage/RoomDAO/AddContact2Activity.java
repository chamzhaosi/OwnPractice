package com.example.facebookloginpage.RoomDAO;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookloginpage.R;

public class AddContact2Activity extends AppCompatActivity {
    private EditText edtName, edtNumber;
    private Button btnSave;
//    private ContactDBHelper helper;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
//        helper = new ContactDBHelper(this);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
        findView();
        setListeners();
    }

    public void findView(){
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        btnSave = findViewById(R.id.btnSave);
    }

    public void setListeners(){
        btnSave.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String number = edtNumber.getText().toString();

            if (name.isEmpty() || number.isEmpty()){
                Toast.makeText(this, "Name or Phone Number cannot be empty!!", Toast.LENGTH_SHORT).show();
            }else {
                Contact2 contact2 = new Contact2(name, number);
//                helper.insertContact(contact);
                insertContact(contact2);
                finish();
            }
        });
    }

    public void insertContact(Contact2 contact2){
        new Thread(()-> contactDAO.insertContact(contact2)).start();
    }

}