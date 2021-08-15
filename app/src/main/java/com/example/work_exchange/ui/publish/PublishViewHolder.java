package com.example.work_exchange.ui.publish;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

public class PublishViewHolder extends RecyclerView.ViewHolder {

    TextView mTitleTv,mTypeTv;
    ImageView mImageTv;
    View mView;

    public PublishViewHolder(@NonNull View itemView) {
        super(itemView);

        mView=itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v,getAdapterPosition());

            }
        });
            mImageTv = itemView.findViewById(R.id.rTImageTv);
            mTitleTv = itemView.findViewById(R.id.rTitleTv);
            mTypeTv = itemView.findViewById(R.id.rTypeTv);
        }



    private ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnClickListener(ClickListener clickListener){

        mClickListener=clickListener;
    }
}
