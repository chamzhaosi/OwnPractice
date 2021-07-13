package com.example.facebookloginpage.contactRecycleview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.facebookloginpage.R;

public class EditContactViewActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    private TextView displayName, displayNumber;
    private Button btnEdit, btnDelete;
    private Contact contact = new Contact();
    private ContactDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_view);
        readExtra();
        helper = new ContactDBHelper(this);
        findViews();
        setListeners();
    }

    private void findViews(){
        displayName = findViewById(R.id.displayName);
        displayNumber = findViewById(R.id.displayNumber);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        displayName.setText(contact.getName());
        displayNumber.setText(contact.getPhoneNumber());
    }

    private void setListeners(){
        btnEdit.setOnClickListener(view -> editContactDetail());
        btnDelete.setOnClickListener(view -> deleteContact());
    }

    private void editContactDetail(){
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.edit_dialog);

        EditText dialogEdtName = dialog.findViewById(R.id.dialogEdtName);
        EditText dialogEdtNumber = dialog.findViewById(R.id.dialogEdtNumber);
        Button dialogBtnCancle = dialog.findViewById(R.id.dialogBtnCancle);
        Button dialogBtnSave = dialog.findViewById(R.id.dialogBtnSave);

        dialogEdtName.setText(contact.getName());
        dialogEdtNumber.setText(contact.getPhoneNumber());

        dialogBtnCancle.setOnClickListener(view-> dialog.dismiss());

        dialogBtnSave.setOnClickListener(view->{
            String newName = dialogEdtName.getText().toString();
            String newNumber = dialogEdtNumber.getText().toString();

            if (newName.isEmpty()){
                dialogEdtName.setError("Name connot be empty!");
            }else if (newNumber.isEmpty()){
                dialogEdtNumber.setError("Phone Number connot be empty!");
            }else {
                dialogEdtName.setError(null);
                dialogEdtNumber.setError(null);
                contact.setName(newName);
                contact.setPhoneNumber(newNumber);
                helper.updateContact(contact);
                displayName.setText(contact.getName());
                displayNumber.setText(contact.getPhoneNumber());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void deleteContact(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delect Contact")
                .setMessage("Are you sure you want delete this contact?")
                .setPositiveButton("Yes", (dialogInterface,i)->{
                    helper.deleteContact(contact.getId());
                    dialogInterface.dismiss();
                    finish();
                })
                .setNegativeButton("No", (dialogInterface, i)-> dialogInterface.dismiss())
                .create()
                .show();
    }

    private void readExtra(){
        Bundle extras = getIntent().getExtras();
        contact.setId(extras.getInt(KEY_ID));
        contact.setName(extras.getString(KEY_NAME));
        contact.setPhoneNumber(extras.getString(KEY_NUMBER));
    }
}