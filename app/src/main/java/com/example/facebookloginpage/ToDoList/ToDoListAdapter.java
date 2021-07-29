package com.example.facebookloginpage.ToDoList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookloginpage.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {
    public List<ToDoList> list = new ArrayList<>();
    public Context context;

    public ToDoListAdapter(Context context, List<ToDoList> list){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.single_row_todolist, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ToDoListAdapter.ViewHolder holder, int position) {
        ToDoList todolist = list.get(position);

        holder.tvSubject.setText(todolist.getSubject());
        holder.tvDueDate.setText(todolist.getDueDate());
        holder.tvDetail.setText(todolist.getDetail());
        holder.todolistRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Edit_Delete_ToDoListActivity.class);
                intent.putExtra(Edit_Delete_ToDoListActivity.KEY_ID, todolist.getId());
                intent.putExtra(Edit_Delete_ToDoListActivity.KEY_SUBJECT_LINE, todolist.getSubject());
                intent.putExtra(Edit_Delete_ToDoListActivity.KEY_DUE_DATE, todolist.getDueDate());
                intent.putExtra(Edit_Delete_ToDoListActivity.KEY_DETAIL, todolist.getDetail());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateToDoList(List<ToDoList> newToDoList){
        list.clear();
        list = newToDoList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvSubject, tvDueDate, tvDetail;
        private LinearLayout todolistRootLayout;

    public ViewHolder(View itemview){
            super(itemview);

            tvSubject = itemview.findViewById(R.id.tvSubject);
            tvDueDate = itemview.findViewById(R.id.tvDueDate);
            tvDetail = itemview.findViewById(R.id.tvDetail);
            todolistRootLayout = itemview.findViewById(R.id.todolistRootLayout);
        }
    }
}


