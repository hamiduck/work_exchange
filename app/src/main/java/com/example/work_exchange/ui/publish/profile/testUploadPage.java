package com.example.work_exchange.ui.publish.profile;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_exchange.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class testUploadPage extends AppCompatActivity {

    EditText medtTitle,medtHomestayAddress,medtPeople,medtMonths,medtPeriod,medtRemark;
    Button mSaveBtn,mListBtn;
    ImageView mPublish_Image1;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    Uri imageUri1;
    private static final int PICK_IMAGE=1;
    ProgressDialog pd;
    UploadTask uploadTask;

    RadioButton rbt1_1,rbt1_2,rbt1_3,rbt1_4,rbt1_5,
            rbt2_1,rbt2_2,rbt2_3,rbt2_4,
            rbt3_1,rbt3_2,rbt3_3,
            rbt4_1,rbt4_2,
            rbt5_1,rbt5_2;
    RadioGroup mArea,mType,mTime,mBonus,mServing;
    ProgressBar progressBar;

    String Area ="";
    String Type ="";
    String Time ="";
    String Bonus ="";
    String Serving ="";

    FirebaseAuth fAuth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testuploadpage);

        medtTitle = findViewById(R.id.edtTitle);
        medtHomestayAddress = findViewById(R.id.edtHomestayAddress);
        medtPeople = findViewById(R.id.edtPeople);
        medtMonths = findViewById(R.id.edtMonths);
        medtPeriod = findViewById(R.id.edtPeriod);
        medtRemark = findViewById(R.id.edtRemark);
        mSaveBtn = findViewById(R.id.saveBtn);
        mListBtn = findViewById(R.id.listBtn);

        mArea = findViewById(R.id.mArea);
        mType =  findViewById(R.id.mType);
        mTime =  findViewById(R.id.mTime);
        mBonus = findViewById(R.id.mBonus);
        mServing = findViewById(R.id.mServing);

        rbt1_1 =findViewById(R.id.rbt1_1);
        rbt1_2 =findViewById(R.id.rbt1_2);
        rbt1_3 =findViewById(R.id.rbt1_3);
        rbt1_4 =findViewById(R.id.rbt1_4);
        rbt1_5 =findViewById(R.id.rbt1_5);

        rbt2_1 =findViewById(R.id.rbt2_1);
        rbt2_2 =findViewById(R.id.rbt2_2);
        rbt2_3 =findViewById(R.id.rbt2_3);
        rbt2_4 =findViewById(R.id.rbt2_4);

        rbt3_1 =findViewById(R.id.rbt3_1);
        rbt3_2 =findViewById(R.id.rbt3_2);
        rbt3_3 =findViewById(R.id.rbt3_3);

        rbt4_1 =findViewById(R.id.rbt4_1);
        rbt4_2 =findViewById(R.id.rbt4_2);

        rbt5_1 =findViewById(R.id.rbt5_1);
        rbt5_2 =findViewById(R.id.rbt5_2);

        mPublish_Image1 = findViewById(R.id.Publish_Image1);
        progressBar = findViewById(R.id.progressbar);

        fAuth = FirebaseAuth.getInstance();
        user = fAuth.getCurrentUser();
        //documentReference=db.collection("publish_test").document();
        storageReference = firebaseStorage.getInstance().getReference("publish_image");

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadData();
            }
        });

        mListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void UploadData() {
        String Title=medtTitle.getText().toString().trim();
        String HomestayAddress=medtHomestayAddress.getText().toString().trim();
        String People=medtPeople.getText().toString().trim();
        String Months=medtMonths.getText().toString().trim();
        String Period=medtPeriod.getText().toString().trim();
        String Remark=medtRemark.getText().toString().trim();


        if(rbt1_1.isChecked()){
            Area="東部";
        }if(rbt1_2.isChecked()){
            Area="西部";
        }if(rbt1_3.isChecked()){
            Area="南部";
        }if(rbt1_4.isChecked()){
            Area="北部";
        }if(rbt1_5.isChecked()){
            Area="中部";
        }
        if(rbt2_1.isChecked()){
            Type="房務清潔";
        }if(rbt2_2.isChecked()){
            Type="接待客人";
        }if(rbt2_3.isChecked()){
            Type="簡單料理";
        }if(rbt2_4.isChecked()){
            Type="環境整理";
        }
        if(rbt3_1.isChecked()){
            Time="1~4";
        }if(rbt3_2.isChecked()){
            Time="4~6";
        }if(rbt3_3.isChecked()){
            Time="6~8";
        }
        if(rbt4_1.isChecked()){
            Bonus="是";
        }if(rbt4_2.isChecked()){
            Bonus="否";
        }
        if(rbt5_1.isChecked()){
            Serving="是";
        }if(rbt5_2.isChecked()){
            Serving="否";
        }
        if (TextUtils.isEmpty(Title)||TextUtils.isEmpty(HomestayAddress)||TextUtils.isEmpty(People)||TextUtils.isEmpty(Months)
                ||TextUtils.isEmpty(Period)||imageUri1==null) {

                Toast.makeText(this, "必須上傳必填欄位 ", Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri1));

                    uploadTask = reference.putFile(imageUri1);
                    Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return reference.getDownloadUrl();
                        }
                    })

                            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    if (task.isSuccessful()) {

                                        Uri downloadUri = task.getResult();
                                        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                                        String id = UUID.randomUUID().toString();
                                        String email = fUser.getEmail();
                                        Map<String, String> publish = new HashMap<>();

                                        publish.put("id", id);

                                        publish.put("email", email);
                                        publish.put("Title", Title);
                                        publish.put("HomestayAddress", HomestayAddress);
                                        publish.put("People", People);
                                        publish.put("Months", Months);
                                        publish.put("Period", Period);
                                        publish.put("Remark", Remark);
                                        publish.put("Area", Area);
                                        publish.put("Type", Type);
                                        publish.put("Time", Time);
                                        publish.put("Bonus", Bonus);
                                        publish.put("Serving", Serving);
                                        publish.put("uri1", downloadUri.toString());



                                        db.collection("publish_test")
                                                .document(id)
                                                .set(publish)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void avoid) {
                                                        progressBar.setVisibility(View.INVISIBLE);
                                                        Toast.makeText(testUploadPage.this, "Publish Create", Toast.LENGTH_SHORT).show();

                                                        Intent intent = new Intent(testUploadPage.this, testShow.class);
                                                        Bundle bundle = new Bundle();
                                                        bundle.putString("id",id);//傳遞String
                                                        startActivity(intent);

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(testUploadPage.this, "Failed  ", Toast.LENGTH_SHORT).show();

                                                    }
                                                });
                                    }
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                }
                            });
                }


    }

    public void ChooseImage1(View view){

        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE||requestCode==RESULT_OK||data!=null||data.getData()!=null){

            imageUri1=data.getData();
            Picasso.get().load(imageUri1).into(mPublish_Image1);
        }
    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver=getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));


    }
}
