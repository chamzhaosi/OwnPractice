package com.example.facebookloginpage.RoomDAO;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.facebookloginpage.R;
import com.example.facebookloginpage.SQLiteDatabashHelper.EditContactViewActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ContactAdapter2 extends RecyclerView.Adapter<ContactAdapter2.ViewHolder> {
    private List<Contact2> contactsList;
    private Context context;

    public ContactAdapter2(Context context, List<Contact2> contactsList){
        this.context = context;
        this.contactsList = contactsList;
    }

    @NonNull
    @NotNull
    @Override
    public ContactAdapter2.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_contact, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ContactAdapter2.ViewHolder holder, int position) {
        Contact2 contact2 = contactsList.get(position);

        holder.tvName.setText(contact2.getName());
        holder.tvNumber.setText(contact2.getPhoneNumber());
        holder.rootLayout.setOnClickListener(view->{
            //pass to the editContactView activity
            Intent i  = new Intent(context, EditContactView2Activity.class);
            i.putExtra(EditContactViewActivity.KEY_ID, contact2.getId());
            i.putExtra(EditContactViewActivity.KEY_NAME, contact2.getName());
            i.putExtra(EditContactViewActivity.KEY_NUMBER, contact2.getPhoneNumber());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return contactsList.size();
    }

    public void updateData(List<Contact2> newContactList){
        contactsList.clear();
        contactsList = newContactList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvName, tvNumber;
        public LinearLayout rootLayout;

        public  ViewHolder(View itemView){
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            rootLayout = itemView.findViewById(R.id.single_root_layout);
        }
    }
}
