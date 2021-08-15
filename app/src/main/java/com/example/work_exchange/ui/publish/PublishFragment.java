package com.example.work_exchange.ui.publish;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import java.util.Collections;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.work_exchange.R;
import com.example.work_exchange.ui.chatroom.UserListAdapter;
import com.example.work_exchange.ui.chatroom.UserListModel;

import com.example.work_exchange.ui.publish.profile.UploadPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class PublishFragment extends Fragment {


    List<PublishModel> publishModelList = new ArrayList<>();
    RecyclerView mRecyclerView;
    TextView title;


    FloatingActionButton mAddBtn;

    FirebaseFirestore db;

    PublishAdapter adapter;

    ProgressDialog pd;

    SwipeRefreshLayout mySwipeRefreshLayout;
    private static final String TAG = "MainActivity";

    StorageReference storageReference;

    public PublishFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_publish, container, false);

        db = FirebaseFirestore.getInstance();

        mRecyclerView = view.findViewById(R.id.recycler_view);

        mAddBtn = view.findViewById(R.id.addBtn);

        mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        title=view.findViewById(R.id.txtTitle);
        pd = new ProgressDialog(getActivity());
        storageReference = FirebaseStorage.getInstance().getReference();

        showData();



        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                reset();
                myUpdateOperation();
            }

        });
        mySwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple);




        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadPage.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void reset() {

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        String email=fUser.getEmail();

        db.collection("Publish")
                .whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        publishModelList.clear();
                        pd.dismiss();
                        for(DocumentSnapshot doc: task.getResult()){
                            PublishModel publishModel =new PublishModel(doc.getString("id"),
                                    doc.getString("Email"),
                                    doc.getString("Title"),
                                    doc.getString("HomestayAddress"),
                                    doc.getString("People"),
                                    doc.getString("Months"),
                                    doc.getString("Period"),
                                    doc.getString("Remark"),
                                    doc.getString("Area"),
                                    doc.getString("Type"),
                                    doc.getString("Time"),
                                    doc.getString("Bonus"),
                                    doc.getString("Serving"),
                                    doc.getString("uri1")

                                    );
                            publishModelList.add(publishModel);
                        }
                        adapter = new PublishAdapter(PublishFragment.this, publishModelList);

                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();

                    }
                });
    }



    private void showData() {
        pd.setTitle("Loading Data...");

        pd.show();

        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

        String email=fUser.getEmail();

        db.collection("Publish")
                .whereEqualTo("email",email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        publishModelList.clear();
                        pd.dismiss();

                        for(DocumentSnapshot doc: task.getResult()){
                            PublishModel publishModel =new PublishModel(doc.getString("id"),
                                    doc.getString("Email"),
                                    doc.getString("Title"),
                                    doc.getString("HomestayAddress"),
                                    doc.getString("People"),
                                    doc.getString("Months"),
                                    doc.getString("Period"),
                                    doc.getString("Remark"),
                                    doc.getString("Area"),
                                    doc.getString("Type"),
                                    doc.getString("Time"),
                                    doc.getString("Bonus"),
                                    doc.getString("Serving"),
                                    doc.getString("uri1")

                            );

                            publishModelList.add(publishModel);
                        }
                        adapter = new PublishAdapter(PublishFragment.this, publishModelList);

                        mRecyclerView.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(PublishFragment.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void  deleteData(int index){
        pd.setTitle("Deleting Data...");

        pd.show();

        db.collection("Publish").document(publishModelList.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(),"Deleted...", Toast.LENGTH_SHORT).show();

                        showData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        pd.dismiss();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void myUpdateOperation()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                mySwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);    // 3 秒
    }


}