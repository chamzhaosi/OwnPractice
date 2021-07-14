package com.example.facebookloginpage.RoomDAO;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.facebookloginpage.R;
import com.example.facebookloginpage.SQLiteDatabashHelper.Contact;

public class EditContactView2Activity extends AppCompatActivity {
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_NUMBER = "number";
    private TextView displayName, displayNumber;
    private Button btnEdit, btnDelete;
    private Contact2 contact2 = new Contact2();
//    private ContactDBHelper helper;
    private ContactDAO contactDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact_view);
        readExtra();
//        helper = new ContactDBHelper(this);
        contactDAO = ContactDatabase.getInstance(this).contactDAO();
        findViews();
        setListeners();
    }

    private void findViews(){
        displayName = findViewById(R.id.displayName);
        displayNumber = findViewById(R.id.displayNumber);
        btnEdit = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        displayName.setText(contact2.getName());
        displayNumber.setText(contact2.getPhoneNumber());
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

        dialogEdtName.setText(contact2.getName());
        dialogEdtNumber.setText(contact2.getPhoneNumber());

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
                contact2.setName(newName);
                contact2.setPhoneNumber(newNumber);
//                helper.updateContact(contact);
                updateContact(contact2);
                displayName.setText(contact2.getName());
                displayNumber.setText(contact2.getPhoneNumber());
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
//                    helper.deleteContact(contact.getId());
                    deleteContact(contact2);
                    dialogInterface.dismiss();
                    finish();
                })
                .setNegativeButton("No", (dialogInterface, i)-> dialogInterface.dismiss())
                .create()
                .show();
    }

    private void readExtra(){
        Bundle extras = getIntent().getExtras();
        contact2.setId(extras.getInt(KEY_ID));
        contact2.setName(extras.getString(KEY_NAME));
        contact2.setPhoneNumber(extras.getString(KEY_NUMBER));
    }

    private void updateContact(Contact2 contact2){
        new Thread(()-> contactDAO.updateContact(contact2)).start();
    }

    private void deleteContact(Contact2 contact2){
        new Thread(()-> contactDAO.deleteContact(contact2)).start();
    }
}