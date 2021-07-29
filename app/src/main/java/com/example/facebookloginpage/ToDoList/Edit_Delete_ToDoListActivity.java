package com.example.facebookloginpage.ToDoList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facebookloginpage.R;

public class Edit_Delete_ToDoListActivity extends AppCompatActivity {
    public static final String KEY_ID = "id";
    public static final String KEY_SUBJECT_LINE = "subject line";
    public static final String KEY_DUE_DATE = "due date";
    public static final String KEY_DETAIL = "detail";
    private TextView edtSubjectLine_EDActivity, edtDueDate_EDActivity, edtDetail_EDActivity;
    private Button btnSave_EDActivity, btnDelete_EDActivity;
    private ToDoList toDoList = new ToDoList();
    private ToDoListDAO toDoListDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_delete_to_do_list);
        readExtra();
        toDoListDAO = ToDoListDatabase.getInstance(this).ToDoListDAO();
        findview();
        setListeners();
    }

    public void findview(){
        edtSubjectLine_EDActivity = findViewById(R.id.edtSubjectLine_EDActivity);
        edtDueDate_EDActivity = findViewById(R.id.edtDueDate_EDActivity);
        edtDetail_EDActivity =  findViewById(R.id.edtDetail_EDActivity);
        btnSave_EDActivity = findViewById(R.id.btnSave_EDActivity);
        btnDelete_EDActivity = findViewById(R.id.btnDelete_EDActivity);

        edtSubjectLine_EDActivity.setText(toDoList.getSubject());
        edtDueDate_EDActivity.setText(toDoList.getDueDate());
        edtDetail_EDActivity.setText(toDoList.getDetail());
    }

    public void setListeners(){
        btnSave_EDActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectLine = edtSubjectLine_EDActivity.getText().toString();
                String dueDate = edtDueDate_EDActivity.getText().toString();
                String detail = edtDetail_EDActivity.getText().toString();

                if (subjectLine.isEmpty() || dueDate.isEmpty() || detail.isEmpty()){
                    Toast.makeText(Edit_Delete_ToDoListActivity.this, "Subjec line, Due date or Detail cannot be empty!!", Toast.LENGTH_SHORT).show();
                }else {
                    toDoList.setSubject(subjectLine);
                    toDoList.setDueDate(dueDate);
                    toDoList.setDetail(detail);
                    saveDialog(toDoList);
                }
            }
        });

        btnDelete_EDActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(toDoList);
            }
        });
    }

    public void readExtra(){
        Bundle bundle = getIntent().getExtras();
        toDoList.setId(bundle.getInt(KEY_ID));
        toDoList.setSubject(bundle.getString(KEY_SUBJECT_LINE));
        toDoList.setDueDate(bundle.getString(KEY_DUE_DATE));
        toDoList.setDetail(bundle.getString(KEY_DETAIL));
    }

    public void saveDialog(ToDoList toDoList){
        AlertDialog.Builder dialog = new AlertDialog.Builder(Edit_Delete_ToDoListActivity.this);
        dialog.setTitle("Notice");
        dialog.setMessage("Do you sure to save this task?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        toDoListDAO.updateTask(toDoList);
                    }
                }).start();
                dialog.dismiss();
                Toast.makeText(Edit_Delete_ToDoListActivity.this, "Successful edition!!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }

    public void deleteDialog(ToDoList toDoList){
        AlertDialog.Builder dialog = new AlertDialog.Builder(Edit_Delete_ToDoListActivity.this);
        dialog.setTitle("Notice");
        dialog.setMessage("Do you sure to delete this task?");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        toDoListDAO.deleteTask(toDoList);
                    }
                }).start();
                dialog.dismiss();
                onBackPressed();
            }
        }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }
}