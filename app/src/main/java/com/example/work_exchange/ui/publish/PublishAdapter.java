package com.example.work_exchange.ui.publish;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;
import com.example.work_exchange.ui.publish.profile.UploadPage;

import java.util.List;

public class PublishAdapter extends RecyclerView.Adapter<PublishViewHolder> {

    PublishFragment publishFragment;
    List<PublishModel> publishModelList;
    Context context;

    public PublishAdapter(PublishFragment publishFragment, List<PublishModel> publishModelList) {
        this.publishFragment = publishFragment;
        this.publishModelList = publishModelList;

    }


    @NonNull
    @Override
    public PublishViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_publishlist,viewGroup,false);

        PublishViewHolder publishViewHolder =new PublishViewHolder(itemView);

        publishViewHolder.setOnClickListener(new PublishViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                AlertDialog.Builder builder=new AlertDialog.Builder(publishFragment.getContext());

                String[] options={"修改","刪除"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            String id= publishModelList.get(position).getId();
                            String email=publishModelList.get(position).getEmail();
                            String Title= publishModelList.get(position).getTitle();
                            String HomestayAddress= publishModelList.get(position).getHomestayAddress();
                            String People= publishModelList.get(position).getPeople();
                            String Months= publishModelList.get(position).getMonths();
                            String Period= publishModelList.get(position).getPeriod();
                            String Remark= publishModelList.get(position).getRemark();
                            String Area=publishModelList.get(position).getArea();
                            String Type=publishModelList.get(position).getType();
                            String Time=publishModelList.get(position).getTime();
                            String Bonus=publishModelList.get(position).getBonus();
                            String Serving=publishModelList.get(position).getServing();



                            Intent intent=new Intent(publishFragment.getContext(), UploadPage.class);

                            intent.putExtra("pId",id);
                            intent.putExtra("pEmail",email);
                            intent.putExtra("pTitle",Title);
                            intent.putExtra("pHomestayAddress",HomestayAddress);
                            intent.putExtra("pPeople",People);
                            intent.putExtra("pMonths",Months);
                            intent.putExtra("pPeriod",Period);
                            intent.putExtra("pRemark",Remark);
                            intent.putExtra("pArea",Area);
                            intent.putExtra("pType",Type);
                            intent.putExtra("pTime",Time);
                            intent.putExtra("pBonus",Bonus);
                            intent.putExtra("pServing",Serving);


                            publishFragment.startActivity(intent);

                        }
                        if (which==1){
                            publishFragment.deleteData(position);

                        }
                    }
                }).create().show();
            }
        });

        return publishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PublishViewHolder publishViewHolder, int i) {

        publishViewHolder.mTitleTv.setText(publishModelList.get(i).getTitle());
        publishViewHolder.mTypeTv.setText(publishModelList.get(i).getType());

        Picasso.get().load(publishModelList.get(i).getUri1()).into(publishViewHolder.mImageTv);
    }

    @Override
    public int getItemCount() {
        return publishModelList.size();
    }
}