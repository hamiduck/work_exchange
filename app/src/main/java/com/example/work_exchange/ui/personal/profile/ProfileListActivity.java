package com.example.work_exchange.ui.personal.profile;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.work_exchange.MainActivity;
import com.example.work_exchange.R;
import com.example.work_exchange.ui.personal.PersonalFragment;
import com.example.work_exchange.ui.publish.PublishFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import javax.annotation.Nullable;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileListActivity extends AppCompatActivity {

    TextView Name, email, phone,homeaddress;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userId;
    Button changeProfile,back;
    FirebaseUser user;
    CircleImageView profileImage;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilelist);

        

        phone = findViewById(R.id.profilePhone);
        Name = findViewById(R.id.profileName);
        email    = findViewById(R.id.profileEmailAddress);
        homeaddress = findViewById(R.id.profileHomeAddress);

        profileImage = findViewById(R.id.profileImage);
        changeProfile = findViewById(R.id.changeProfile);
        back=findViewById(R.id.back);
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

        userId = fAuth.getCurrentUser().getUid();
        user = fAuth.getCurrentUser();

        DocumentReference documentReference = fStore.collection("Trade User").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    Name.setText(documentSnapshot.getString("Name"));
                    homeaddress.setText(documentSnapshot.getString("homeaddress"));
                    phone.setText(documentSnapshot.getString("phone"));
                    email.setText(documentSnapshot.getString("email"));
                }else {
                    Log.d("tag", "onEvent: Document do not exists");
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //FragmentTransaction fr = getFragmentManager().beginTransaction();
                //getSupportFragmentManager().beginTransaction().replace(R.id.container,new PersonalFragment()).commit();

                /*Intent intent=new Intent();
                intent.setClass(ProfileListActivity.this,ProfileListActivity.class);
                intent.putExtra("id", 1);
                startActivity(intent);*/

                /*FragmentTransaction fr = getFragmentManager().beginTransaction();
                final FragmentTransaction replace = fr.replace(R.id.container, new PersonalFragment());
                fr.commit();*/

                //getSupportFragmentManager().beginTransaction().replace(R.id.container,new PersonalFragment()).commit();


                finish();

            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // open gallery
                Intent intent = new Intent(v.getContext(),ProfileActivity.class);
                intent.putExtra("Name",Name.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("homeaddress",homeaddress.getText().toString());
                intent.putExtra("email",email.getText().toString());
                startActivity(intent);
//

            }
        });

    }
    }

