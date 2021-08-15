package com.example.work_exchange.ui.publish.profile;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.work_exchange.MainActivity;
import com.example.work_exchange.R;
import com.example.work_exchange.ui.publish.PublishFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UploadPage extends AppCompatActivity {


    EditText medtTitle, medtHomestayAddress, medtPeople, medtMonths, medtPeriod, medtRemark;
    Button mSaveBtn, mListBtn;
    String pId, pTitle, pHomestayAddress, pPeople, pMonths, pPeriod, pRemark, pEmail
            ;
    StorageReference storageRef;
    ImageView mPublish_Image1;
    private static final int PICK_IMAGE=1;
    Uri imageUri1;
    ProgressDialog pd;

    FirebaseStorage firebaseStorage;

    FirebaseFirestore db;
    StorageReference storageReference;
    FirebaseAuth fAuth;
    UploadTask uploadTask;

    RadioButton rbt1_1,rbt1_2,rbt1_3,rbt1_4,rbt1_5,
            rbt2_1,rbt2_2,rbt2_3,rbt2_4,
            rbt3_1,rbt3_2,rbt3_3,
            rbt4_1,rbt4_2,
            rbt5_1,rbt5_2;
    RadioGroup mArea,mType,mTime,mBonus,mServing;

    String Area ="";
    String Type ="";
    String Time ="";
    String Bonus ="";
    String Serving ="";

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploadpage);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add Data");

        storageRef = FirebaseStorage.getInstance().getReference();
        medtTitle = findViewById(R.id.edtTitle);
        medtHomestayAddress = findViewById(R.id.edtHomestayAddress);
        medtPeople = findViewById(R.id.edtPeople);
        medtMonths = findViewById(R.id.edtMonths);
        medtPeriod = findViewById(R.id.edtPeriod);
        medtRemark = findViewById(R.id.edtRemark);

        mSaveBtn = findViewById(R.id.saveBtn);
        mListBtn = findViewById(R.id.listBtn);
        mPublish_Image1 = findViewById(R.id.Publish_Image1);

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

        fAuth = FirebaseAuth.getInstance();
        // firebaseStorage=FirebaseStorage.getInstance();

        storageReference = firebaseStorage.getInstance().getReference().child("publish_image/"+fAuth.getCurrentUser().getUid()+"/"+pId+"/publish_image1.jpg");
       // ("publish/"+fAuth.getCurrentUser().getUid()+"/"+pId+"/publish_image1.jpg");
        db = FirebaseFirestore.getInstance();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actionBar.setTitle("Update Data");
            mSaveBtn.setText("Update");
            pId = bundle.getString("pId");
            pEmail = bundle.getString("pEmail");
            pTitle = bundle.getString("pTitle");
            pHomestayAddress = bundle.getString("pHomestayAddress");
            pPeople = bundle.getString("pPeople");
            pMonths = bundle.getString("pMonths");
            pPeriod = bundle.getString("pPeriod");
            pRemark = bundle.getString("pRemark");
            Area= bundle.getString("pArea");
            Type= bundle.getString("pType");
            Time= bundle.getString("pATime");
            Bonus= bundle.getString("pBonus");
            Serving= bundle.getString("pServing");

            medtTitle.setText(pTitle);
            medtHomestayAddress.setText(pHomestayAddress);
            medtPeople.setText(pPeople);
            medtMonths.setText(pMonths);
            medtPeriod.setText(pPeriod);
            medtRemark.setText(pRemark);

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



        } else {
            actionBar.setTitle("Add Data");
            mSaveBtn.setText("Save");
        }


        pd = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle1 = getIntent().getExtras();
                if (bundle != null) {

                    String id = pId;
                    String email = pEmail;
                    String Title = medtTitle.getText().toString().trim();
                    String HomestayAddress = medtHomestayAddress.getText().toString().trim();
                    String People = medtPeople.getText().toString().trim();
                    String Months = medtMonths.getText().toString().trim();
                    String Period = medtPeriod.getText().toString().trim();
                    String Remark = medtRemark.getText().toString().trim();

                    String Area ="";
                    String Type ="";
                    String Time ="";
                    String Bonus ="";
                    String Serving ="";
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


                    updateData(id, email, Title, HomestayAddress, People, Months, Period, Remark,
                            Area, Type , Time, Bonus, Serving);

                } else {

                    String Title = medtTitle.getText().toString().trim();
                    String HomestayAddress = medtHomestayAddress.getText().toString().trim();
                    String People = medtPeople.getText().toString().trim();
                    String Months = medtMonths.getText().toString().trim();
                    String Period = medtPeriod.getText().toString().trim();
                    String Remark = medtRemark.getText().toString().trim();

                    String Area ="";
                    String Type ="";
                    String Time ="";
                    String Bonus ="";
                    String Serving ="";

                    uploadData(Title, HomestayAddress, People, Months, Period, Remark,
                            Area, Type , Time, Bonus, Serving);


                }
                //finish();
            }

        });
        mListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateData(String id, String email, String Title, String HomestayAddress, String People, String Months, String Period, String Remark,
                            String Area,String Type ,String Time,String Bonus,String Serving) {

        if (TextUtils.isEmpty(Title) || TextUtils.isEmpty(HomestayAddress) || TextUtils.isEmpty(People) || TextUtils.isEmpty(Months)
                || TextUtils.isEmpty(Period) || imageUri1 == null) {

            Toast.makeText(this, "必須上傳必填欄位 ", Toast.LENGTH_SHORT).show();
        }
       else {
            final StorageReference reference = storageReference.child("publish_image/"+fAuth.getCurrentUser().getUid()+"/"+pId+"/publish_image1.jpg");

            uploadTask = reference.putFile(imageUri1);

            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return reference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {

                        Uri downloadUri = task.getResult();
                        pd.setTitle("Updating Data...");
                        pd.show();

                        final DocumentReference sfDocRef= db.collection("Publish").document(id);

                        db.runTransaction(new Transaction.Function<Void>() {
                            @Override
                            public Void apply(Transaction transaction)throws FirebaseFirestoreException{
                                DocumentSnapshot snapshot=transaction.get(sfDocRef);

                                transaction.update(sfDocRef,"Title", Title);
                                transaction.update(sfDocRef,"People", People);
                                transaction.update(sfDocRef,"Months", Months);
                                transaction.update(sfDocRef,"Period", Period);
                                transaction.update(sfDocRef,"Remark", Remark);
                                transaction.update(sfDocRef,"Area", Area);
                                transaction.update(sfDocRef,"Type", Type);
                                transaction.update(sfDocRef,"Time", Time);
                                transaction.update(sfDocRef,"Bonus", Bonus);
                                transaction.update(sfDocRef,"Serving", Serving);
                                transaction.update(sfDocRef,"uri1", downloadUri.toString());

                                return null;

                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull  Exception e) {

                            }
                        })

                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        pd.dismiss();
                                        Toast.makeText(com.example.work_exchange.ui.publish.profile.UploadPage.this, "Updated...", Toast.LENGTH_SHORT).show();

                                        finish();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        pd.dismiss();
                                        Toast.makeText(com.example.work_exchange.ui.publish.profile.UploadPage.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                });
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull  Exception e) {

                }
            });
        }
    }


    private void uploadData(String Title, String HomestayAddress, String People, String Months, String Period, String Remark,
                           String Area,String Type ,String Time,String Bonus,String Serving) {
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
        if (TextUtils.isEmpty(Title) || TextUtils.isEmpty(HomestayAddress) || TextUtils.isEmpty(People) || TextUtils.isEmpty(Months)
                || TextUtils.isEmpty(Period) || imageUri1 == null) {

            Toast.makeText(this, "必須上傳必填欄位 ", Toast.LENGTH_SHORT).show();
        }else {

        pd.setTitle("Adding Data to Firestore");
        pd.show();
        FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
        String id = UUID.randomUUID().toString();
        String email = fUser.getEmail();
        Map<String, String> doc = new HashMap<>();

        doc.put("id", id);
        doc.put("email", email);
        doc.put("Title", Title);
        doc.put("HomestayAddress", HomestayAddress);
        doc.put("People", People);
        doc.put("Months", Months);
        doc.put("Period", Period);
        doc.put("Remark", Remark);
        doc.put("Area",Area );
        doc.put("Type", Type);
        doc.put("Time", Time);
        doc.put("Bonus", Bonus);
        doc.put("Serving", Serving);


            final StorageReference reference = storageReference.child(System.currentTimeMillis() + "." + getFileExt(imageUri1));

            uploadTask = reference.putFile(imageUri1);
            String finalArea = Area;
            String finalType = Type;
            String finalTime = Time;
            String finalBonus = Bonus;
            String finalServing = Serving;
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
                                pd.setTitle("Adding Data to Firestore");
                                pd.show();
                                FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();
                                String id = UUID.randomUUID().toString();
                                String email = fUser.getEmail();

                                Map<String, String> doc = new HashMap<>();

                                doc.put("id", id);
                                doc.put("email", email);
                                doc.put("Title", Title);
                                doc.put("HomestayAddress", HomestayAddress);
                                doc.put("People", People);
                                doc.put("Months", Months);
                                doc.put("Period", Period);
                                doc.put("Remark", Remark);

                                doc.put("Area", finalArea);
                                doc.put("Type", finalType);
                                doc.put("Time", finalTime);
                                doc.put("Bonus", finalBonus);
                                doc.put("Serving", finalServing);

                                doc.put("uri1", downloadUri.toString());

                                db.collection("Publish").document(id).set(doc)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void avoid) {
                                                Toast.makeText(UploadPage.this, "Publish Create", Toast.LENGTH_SHORT).show();

                                                finish();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(UploadPage.this, "Failed  ", Toast.LENGTH_SHORT).show();

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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