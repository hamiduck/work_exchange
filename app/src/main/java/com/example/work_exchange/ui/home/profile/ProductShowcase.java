package com.example.work_exchange.ui.home.profile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.work_exchange.R;
import com.example.work_exchange.ui.home.HomeAdapter;
import com.example.work_exchange.ui.publish.profile.testShow;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
public class ProductShowcase extends AppCompatActivity  {

    TextView show_Title,show_HomestayAddress,show_People,show_Area,show_Type,show_Time,show_Bonus,
            show_Serving,show_Months,show_Period,show_Remark;
    ImageView showPublish_Image1;
    Button mListBtn;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showcase);

        showPublish_Image1=findViewById(R.id.show_Publish_Image1);
        show_Title=findViewById(R.id.show_Title);
        show_HomestayAddress=findViewById(R.id.show_HomestayAddress);
        show_People=findViewById(R.id.show_People);
        show_Area=findViewById(R.id.show_Area);
        show_Type=findViewById(R.id.show_Type);
        show_Time=findViewById(R.id.show_Time);
        show_Bonus=findViewById(R.id.show_Bonus);
        show_Serving=findViewById(R.id.show_Serving);
        show_Months=findViewById(R.id.show_Months);
        show_Period=findViewById(R.id.show_Period);
        show_Remark=findViewById(R.id.show_Remark);

        mListBtn = findViewById(R.id.listBtn);

        db=FirebaseFirestore.getInstance();

        String Title="Title not set";
        String Type="Type not set";
        String People="People not set";
        String Id;

        Bundle extra=getIntent().getExtras();
        Id=extra.getString("id");
        if(extra!=null){
            Title=extra.getString("Title");
            Type=extra.getString("Type");
            People=extra.getString("People");

        }
        show_Title.setText(Title);
        show_Type.setText(Type);
        show_People.setText(People);
        mListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extra=getIntent().getExtras();
        String Id=extra.getString("id");
        db.collection("Publish").document(Id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            if (task.getResult().exists()){
                //String Title_result=task.getResult().getString("Title");
                String HomestayAddress_result=task.getResult().getString("HomestayAddress");
                //  String People_result=task.getResult().getString("People");
                String Months_result=task.getResult().getString("Months");
                String Period_result=task.getResult().getString("Period");
                String Remark_result=task.getResult().getString("Remark");
                String Area_result=task.getResult().getString("Area");
                //  String Type_result=task.getResult().getString("Type");
                String Time_result=task.getResult().getString("Time");
                String Bonus_result=task.getResult().getString("Bonus");
                String Serving_result=task.getResult().getString("Serving");
                String uri1=task.getResult().getString("uri1");

                Picasso.get().load(uri1).into(showPublish_Image1);

                //show_Title.setText(Title_result);
                 show_HomestayAddress.setText(HomestayAddress_result);
                // show_People.setText(People_result);
                 show_Months.setText(Months_result);
                 show_Period.setText(Period_result);
                 show_Remark.setText(Remark_result);
                 show_Area.setText(Area_result);
                //  show_Type.setText(Type_result);
                 show_Time.setText(Time_result);
                 show_Bonus.setText(Bonus_result);
                 show_Serving.setText(Serving_result);

            }else {

                Toast.makeText(ProductShowcase.this, "No Publish exist", Toast.LENGTH_SHORT).show();

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
