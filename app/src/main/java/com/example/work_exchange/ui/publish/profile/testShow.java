package com.example.work_exchange.ui.publish.profile;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_exchange.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class testShow extends AppCompatActivity {

    ImageView showPublish_Image1;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db;
    DocumentReference documentReference;
    UploadTask uploadTask;
    TextView show_Title,show_HomestayAddress,show_People,show_Area,show_Type,show_Time,show_Bonus,
            show_Serving,show_Months,show_Period,show_Remark;
    String id;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuploadpage);

        db=FirebaseFirestore.getInstance();

        storageReference=firebaseStorage.getInstance().getReference("publish_image");
        showPublish_Image1=findViewById(R.id.show_Publish_Image1);
        show_Title=findViewById(R.id.show_Title);
        show_HomestayAddress=findViewById(R.id.show_HomestayAddress);
        show_People=findViewById(R.id.show_People);
       // show_Area=findViewById(R.id.show_Area);
       // show_Type=findViewById(R.id.show_Type);
       // show_Time=findViewById(R.id.show_Time);
       // show_Bonus=findViewById(R.id.show_Bonus);
       // show_Serving=findViewById(R.id.show_Serving);
        show_Months=findViewById(R.id.show_Months);
        show_Period=findViewById(R.id.show_Period);
        show_Remark=findViewById(R.id.show_Remark);



    }

    @Override
    protected void onStart() {
        super.onStart();

        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");

        db.collection("publish_test").document(id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull  Task<DocumentSnapshot> task) {

                        if (task.getResult().exists()){
                            String Title_result=task.getResult().getString("Title");
                          //  String HomestayAddress_result=task.getResult().getString("HomestayAddress");
                          //  String People_result=task.getResult().getString("People");
                          //  String Months_result=task.getResult().getString("Months");
                          //  String Period_result=task.getResult().getString("Period");
                          //  String Remark_result=task.getResult().getString("Remark");
                           // String Area_result=task.getResult().getString("Area");
                          //  String Type_result=task.getResult().getString("Type");
                          //  String Time_result=task.getResult().getString("Time");
                          //  String Bonus_result=task.getResult().getString("Bonus");
                          //  String Serving_result=task.getResult().getString("Serving");
                            String uri1=task.getResult().getString("uri1");

                           // Picasso.get().load(uri1).into(showPublish_Image1);

                            show_Title.setText(Title_result);
                           // show_HomestayAddress.setText(HomestayAddress_result);
                           // show_People.setText(People_result);
                           // show_Months.setText(Months_result);
                           // show_Period.setText(Period_result);
                           // show_Remark.setText(Remark_result);
                           // show_Area.setText(Area_result);
                          //  show_Type.setText(Type_result);
                          //  show_Time.setText(Time_result);
                          //  show_Bonus.setText(Bonus_result);
                          //  show_Serving.setText(Serving_result);

                        }else {

                            Toast.makeText(testShow .this, "No Publish exist", Toast.LENGTH_SHORT).show();

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull  Exception e) {

                    }
                });
    }
}
