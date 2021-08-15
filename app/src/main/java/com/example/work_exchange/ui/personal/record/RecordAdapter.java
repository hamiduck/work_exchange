package com.example.work_exchange.ui.personal.record;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.List;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.ViewHolder> {

    private List<RecordModel> userList;
    private OnRecordEventListener mClickListener;
    public RecordAdapter(List<RecordModel> records, OnRecordEventListener listener) {
        this.userList = records;
        this.mClickListener = listener;
    }
    public RecordAdapter(List<RecordModel>userList) {
        this.userList=userList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_record,parent,false);
        return new ViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int resource = userList.get(position).getImageview();
        String name=userList.get(position).getTextview1();
        String msg=userList.get(position).getTextview2();
        String time=userList.get(position).getTextview3();



        holder.setData(resource,name,msg,time);



    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    //view holder class



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView textview3;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here use xml ids
            //give different name not like constructor
            imageView=itemView.findViewById(R.id.imageview);
            textView=itemView.findViewById(R.id.textview);
            textView2=itemView.findViewById(R.id.textview2);
            textview3=itemView.findViewById(R.id.textview3);


        }

        public void setData(int resource, String name, String msg, String time) {

            imageView.setImageResource(resource);
            textView.setText(name);
            textView2.setText(msg);
            textview3.setText(time);




        }
    }
    public interface OnRecordEventListener  {
        void onRatingBarChange(RecordModel item, float value);
    }

}