package com.example.work_exchange.ui.personal.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.ArrayList;


public class ProfileAdapter  extends  RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>{

    ArrayList<ProfileModel> dataHolder;
    OnItemClickListener listener;

    public ProfileAdapter (ArrayList<ProfileModel> dataHolder,OnItemClickListener listener){
        this.dataHolder=dataHolder;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design2,parent,false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {
        holder.textProfile.setText(dataHolder.get(position).getTextProfile());
        holder.textHint.setText(dataHolder.get(position).getTextHint());
        holder.imageEnd.setImageResource(dataHolder.get(position).getImageEnd());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textProfile,textHint;
        ImageView imageEnd;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            textProfile=itemView.findViewById(R.id.textname);
            textHint=itemView.findViewById(R.id.texthint);
            imageEnd=itemView.findViewById(R.id.imageViewchevron2);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v,getAdapterPosition());
        }
    }

    public interface OnItemClickListener{
        void OnItemClick(View v,int position);
    }

}
