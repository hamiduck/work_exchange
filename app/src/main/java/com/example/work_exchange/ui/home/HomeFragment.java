package com.example.work_exchange.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.work_exchange.LoginActivity;
import com.example.work_exchange.ui.home.profile.ProductShowcase;
import com.example.work_exchange.ui.publish.profile.UploadPage;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.example.work_exchange.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Button Searchbtn;
    EditText edtSearch;

    FirebaseFirestore firebaseFirestore;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;

    FirebaseAuth fAuth;
    ImageView list_img;
    String pId;
    ArrayList<HomeModel>homeModelArrayList;

    HomeAdapter.RecyclerViewClickListener listener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView=root.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        Searchbtn=root.findViewById(R.id.Searchbtn);
        edtSearch=root.findViewById(R.id.edtSearch);

        firebaseFirestore = FirebaseFirestore.getInstance();
        homeModelArrayList=new ArrayList<HomeModel>();
        setOnClickListener();
        homeAdapter=new HomeAdapter(getContext(),homeModelArrayList,listener);

        fAuth = FirebaseAuth.getInstance();
        list_img=root.findViewById(R.id.list_img);


        recyclerView.setAdapter(homeAdapter);
        EventChangeListener();

        Searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                



            }
        });

        return root;
    }

    private void setOnClickListener() {
        listener=new HomeAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(), ProductShowcase.class);
                intent.putExtra("Title",homeModelArrayList.get(position).getTitle());
                intent.putExtra("Type",homeModelArrayList.get(position).getType());
                intent.putExtra("People",homeModelArrayList.get(position).getPeople());
                intent.putExtra("id",homeModelArrayList.get(position).getId());
                startActivity(intent);
            }
        };
    }

    private void EventChangeListener() {
        firebaseFirestore.collection("Publish")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable  QuerySnapshot value, @Nullable  FirebaseFirestoreException error) {

                        for(DocumentChange dc :value.getDocumentChanges()){
                            if(dc.getType()==DocumentChange.Type.ADDED){
                                homeModelArrayList.add(dc.getDocument().toObject(HomeModel.class));
                            }
                            homeAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
