package com.example.work_exchange.ui.chatroom;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<UserListModel> userList;

    public UserListAdapter(List<UserListModel>userList) {
        this.userList=userList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_userlist,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        int resource = userList.get(position).getImageview();
        String name=userList.get(position).getTextview1();
        String msg=userList.get(position).getTextview2();
        String time=userList.get(position).getTextview3();
        String line=userList.get(position).getDivider();

        holder.setData(resource,name,msg,time,line);

    }



    @Override
    public int getItemCount() {
        return userList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userimageView;
        private TextView username;
        private TextView chatcontext;
        private TextView chattime;
        private TextView chatline;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            userimageView=itemView.findViewById(R.id.userimageview);
            username=itemView.findViewById(R.id.username);
            chatcontext=itemView.findViewById(R.id.chatcontext);
            chattime=itemView.findViewById(R.id.chattime);
            chatline=itemView.findViewById(R.id.chatline);


        }

        public void setData(int resource, String name, String msg, String time,String line) {

            userimageView.setImageResource(resource);
            username.setText(name);
            chatcontext.setText(msg);
            chattime.setText(time);
            chatline.setText(line);



        }
    }
}