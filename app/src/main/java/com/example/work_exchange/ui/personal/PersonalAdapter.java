package com.example.work_exchange.ui.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.ArrayList;

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalViewHolder>{


    ArrayList<PersonalModel> dataholder;
    OnItemClickListener listener;

    public PersonalAdapter(ArrayList<PersonalModel> dataholder,OnItemClickListener listener) {
        this.dataholder = dataholder;
        this.listener=listener;
    }

    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_design,parent,false);
       return new PersonalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonalViewHolder holder, final int position) {
        holder.textLabel.setText(dataholder.get(position).getTextPersonal());
        holder.imageHeader.setImageResource(dataholder.get(position).getImageHeader());
        holder.imageEnd.setImageResource(dataholder.get(position).getImageEnd());

    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }


    public class PersonalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView textLabel;
        private ImageView imageHeader,imageEnd;

        public PersonalViewHolder(@NonNull View itemView) {
            super(itemView);
            textLabel=itemView.findViewById(R.id.text);
            imageHeader=itemView.findViewById(R.id.imageView);
            imageEnd=itemView.findViewById(R.id.imageViewchevron);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnItemClick(v,getAdapterPosition());
        }
    }

    public interface  OnItemClickListener{
         void OnItemClick(View v,int position);
    }

}
