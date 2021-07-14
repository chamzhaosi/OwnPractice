package com.example.facebookloginpage.SQLiteDatabashHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookloginpage.R;

public class AddContactActivity extends AppCompatActivity {
    private EditText edtName, edtNumber;
    private Button btnSave;
    private ContactDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        helper = new ContactDBHelper(this);
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
                Contact contact = new Contact(name, number);
                helper.insertContact(contact);
                finish();
            }
        });
    }


}