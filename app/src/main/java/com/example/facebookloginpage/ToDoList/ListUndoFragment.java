package com.example.facebookloginpage.ToDoList;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.facebookloginpage.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ListUndoFragment extends Fragment {
    private ToDoListAdapter adapter;
    private RecyclerView recyclerView;
    private View view;
    private TextView tvMessage;
    private ToDoListDAO toDoListDAO;

    public ListUndoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_undo, container, false);
        view = inflater.inflate(R.layout.fragment_list_undo, container, false);
        toDoListDAO = ToDoListDatabase.getInstance(view.getContext()).ToDoListDAO();
        findview();
        initialRecycleList();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshAdapter();
    }

    public void findview(){
        recyclerView = view.findViewById(R.id.recycleViewTodolist);
        tvMessage = view.findViewById(R.id.tvMessage);
    }

    public void initialRecycleList(){
        List<ToDoList> toDoLists = new ArrayList<>();
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My car>>", "28-July-2021"));
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My pen>>", "28-July-2021"));
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My friend>>", "28-July-2021"));
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My parent>>", "28-July-2021"));
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My mouse>>", "28-July-2021"));
//        toDoLists.add(new ToDoList("Read a Book", "Read a story book << My pet>>", "28-July-2021"));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<ToDoList> checkList = toDoListDAO.getAllTasks();
                    if (checkList.isEmpty()){
                        tvMessage.setText("Please add you first task!!");
                    }
                }
            }).start();
        adapter = new ToDoListAdapter(view.getContext(), toDoLists);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);
    }

    private void refreshAdapter(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ToDoList> toDoLists = toDoListDAO.getAllTasks();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateToDoList(toDoLists);
                    }
                });
            }
        }).start();
    }
}