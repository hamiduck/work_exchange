package com.example.work_exchange.ui.personal.record;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<RecordModel>userList;
    RecordAdapter recordAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);


        initData();
        initRecyclerView();
        Button btnback3=(Button)findViewById(R.id.btnback3);
        btnback3.setOnClickListener(btnback3Listner);
    }
    private View.OnClickListener btnback3Listner=new View.OnClickListener(){
        public void onClick(View v){
            finish();
        }

    };

    private void initRecyclerView() {
        mrecyclerView=findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        recordAdapter =new RecordAdapter(userList);
        mrecyclerView.setAdapter(recordAdapter);
        recordAdapter.notifyDataSetChanged();




    }
    private void initData() {
        userList = new ArrayList<>();

        userList.add(new RecordModel(R.drawable.exchange_test1,"鴨鴨1","換宿紀錄一","10:45 am"));

        userList.add(new RecordModel(R.drawable.exchange_test2,"鴨鴨2","換宿紀錄二","15:08 pm"));

        userList.add(new RecordModel(R.drawable.exchange_test3,"鴨鴨3","換宿紀錄三","1:02 am"));

        userList.add(new RecordModel(R.drawable.exchange_test4,"鴨鴨4","換宿紀錄四","12:55 pm"));

       

    }


}