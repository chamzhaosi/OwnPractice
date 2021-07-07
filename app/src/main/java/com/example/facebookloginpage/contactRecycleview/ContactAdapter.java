package com.example.facebookloginpage.contactRecycleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookloginpage.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private ArrayList<Contact> contactsList = new ArrayList<>();
    private Context context;

    public ContactAdapter(Context context, ArrayList<Contact> contactsList){
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @NotNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter.ViewHolder holder, int position) {
        Contact contact = contactsList.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void updateData(ArrayList<Contact> newContactList){
        contactsList.clear();
        contactsList = newContactList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvNumber;

        public  ViewHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }
}
