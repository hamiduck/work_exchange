package com.example.work_exchange.ui.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.LoginActivity;
import com.example.work_exchange.R;
import com.example.work_exchange.ui.personal.evaluation.EvaluationActivity;
import com.example.work_exchange.ui.personal.profile.ProfileActivity;
import com.example.work_exchange.ui.personal.profile.ProfileListActivity;
import com.example.work_exchange.ui.personal.record.RecordActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalFragment extends Fragment  {

    private PersonalViewModel personalViewModel;
    private RecyclerView recyclerViewPersonal;
    private ArrayList<PersonalModel> dataholder;
    private PersonalAdapter.OnItemClickListener listener;
    private Button btnLogout;
    private CircleImageView profileImage;
    FirebaseUser user;
    private TextView fullName,email,phone,homeaddress;
    private FirebaseAuth fAuth;
    private FirebaseFirestore fStore;
    private String userId;
    private StorageReference storageReference;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root=inflater.inflate(R.layout.fragment_personal,container,false);
        profileImage =root.findViewById(R.id.profileImage);
        recyclerViewPersonal=root.findViewById(R.id.recyclerView);//
        recyclerViewPersonal.setHasFixedSize(true);//
        recyclerViewPersonal.setLayoutManager(new LinearLayoutManager(getContext()));//
        recyclerViewPersonal.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));//

        setOnClickListener();
        dataholder=new ArrayList<>();
        PersonalModel ob1=new PersonalModel("基本資料設定", R.drawable.ic_person_blue_24dp, R.drawable.ic_chevron_right_gray_24dp);
        dataholder.add(ob1);
        PersonalModel ob2=new PersonalModel("換宿紀錄", R.drawable.ic_history_blue_24dp, R.drawable.ic_chevron_right_gray_24dp);
        dataholder.add(ob2);
        PersonalModel ob3=new PersonalModel("評價", R.drawable.ic_rate_review_blue_24dp, R.drawable.ic_chevron_right_gray_24dp);
        dataholder.add(ob3);
        recyclerViewPersonal.setAdapter(new PersonalAdapter(dataholder,listener));




        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("Trade User/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(profileImage);
            }
        });



        personalViewModel=new ViewModelProvider(this).get(PersonalViewModel.class);
        personalViewModel.gettext().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        btnLogout=root.findViewById(R.id.buttonLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().onBackPressed();

            }
        });

        return root;
    }

    private void setOnClickListener() {
        listener=new PersonalAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                if(position==0){
                    Intent intent=new Intent(getActivity(), ProfileListActivity.class);
                    startActivity(intent);
                }
                else if(position==1){
                    Intent intent=new Intent(getActivity(), RecordActivity.class);
                    startActivity(intent);
                }
                else if (position==2){
                    Intent intent=new Intent(getActivity(), EvaluationActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

}
