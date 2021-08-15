package com.example.work_exchange.ui.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.work_exchange.LoginActivity;
import com.example.work_exchange.R;
import com.example.work_exchange.ui.personal.profile.ProfileListActivity;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    FirebaseAuth fAuth;
    String pId;
    FirebaseStorage storage;
    StorageReference storageRef;
    Context context;
    ArrayList<HomeModel>homeModelArrayList;
    static RecyclerViewClickListener listener;

    public HomeAdapter(Context context, ArrayList<HomeModel> homeModelArrayList,RecyclerViewClickListener listener) {
        this.context = context;
        this.homeModelArrayList = homeModelArrayList;
        this.listener=listener;
    }

    @NonNull

    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull  ViewGroup parent,int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_recommend,parent,false);


        return new HomeViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull  HomeAdapter. HomeViewHolder holder, int position) {

        HomeModel homeModel=homeModelArrayList.get(position);

        holder.list_Title.setText(homeModel.getTitle());
        holder.list_Type.setText(homeModel.getType());
        holder.list_People.setText(homeModel.getPeople());

        Picasso.get().load(homeModel .getUri1()).into(holder.list_img);
    }

    @Override
    public int getItemCount() {
        return homeModelArrayList.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView list_Title;
        TextView list_Type;
        TextView list_People;
        ImageView list_img;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);

            list_Title = itemView.findViewById(R.id.list_title);
            list_Type = itemView.findViewById(R.id.list_type);
            list_People = itemView.findViewById(R.id.list_people);
            list_img = itemView.findViewById(R.id.list_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            listener.onClick(view,getAdapterPosition());
        }
    }

}