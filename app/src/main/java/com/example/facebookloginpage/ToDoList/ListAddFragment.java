package com.example.facebookloginpage.ToDoList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.facebookloginpage.R;


public class ListAddFragment extends Fragment {
    private View view;
    private EditText edtSubjectLine, edtDueDate, edtDetail;
    private Button btnSaveTask;
    private ToDoListDAO toDoListDAO;
    private boolean addAnother = false;

    public ListAddFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_add, container, false);
        view = inflater.inflate(R.layout.fragment_list_add, container, false);
        toDoListDAO = ToDoListDatabase.getInstance(view.getContext()).ToDoListDAO();
        findview();
        setListeners();
        return view;
    }

    public void findview(){
        edtSubjectLine = view.findViewById(R.id.edtSubjectLine);
        edtDueDate = view.findViewById(R.id.edtDueDate);
        edtDetail = view.findViewById(R.id.edtDetail);
        btnSaveTask = view.findViewById(R.id.btnSaveTask);
    }

    public void setListeners(){
        do{
            btnSaveTask.setOnClickListener(view-> {
                String subjectLine = edtSubjectLine.getText().toString();
                String dueDate = edtDueDate.getText().toString();
                String detail = edtDetail.getText().toString();

                if (subjectLine.isEmpty() || dueDate.isEmpty() || detail.isEmpty()){
                    Toast.makeText(view.getContext(), "Subject Line, Due Date or Detail cannot be empty!!", Toast.LENGTH_SHORT).show();
                }else {
                    ToDoList toDoList = new ToDoList(subjectLine, detail, dueDate);
                    insertOneTask(toDoList);
                }

                AlertDialog.Builder diglog = new AlertDialog.Builder(view.getContext());
                diglog.setTitle("Notice");
                diglog.setMessage("Do you want save another task?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addAnother = true;
                                edtSubjectLine.setText("");
                                edtDueDate.setText("");
                                edtDetail.setText("");
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(view.getContext(), BottomMenuActivity.class);
                                startActivity(intent);
                                getActivity().finish(); // to stop the back function (in-build android)
                            }
                        }).create().show();
            });
        }while (addAnother==true);
    }


    public void insertOneTask(ToDoList toDoList){
        new Thread(new Runnable() {
            @Override
            public void run() {
                toDoListDAO.insertTask(toDoList);
            }
        }).start();
    }
}