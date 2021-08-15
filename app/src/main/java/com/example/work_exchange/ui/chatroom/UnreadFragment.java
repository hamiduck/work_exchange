package com.example.work_exchange.ui.chatroom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.work_exchange.R;

import java.util.ArrayList;
import java.util.List;


public class UnreadFragment extends Fragment {


    RecyclerView mrecyclerView;
    List<UserListModel>userList;
    UserListAdapter adapter;

    public UnreadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_unread, container, false);

        initData();


        mrecyclerView= view.findViewById(R.id.RecyclerView);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new UserListAdapter(userList);

        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        return view;
    }



    private void initData() {
        userList = new ArrayList<>();

        userList.add(new UserListModel(R.drawable.exchange_test1,"鴨鴨1號","你好嗎","10:45 am","_______________________________________"));

        userList.add(new UserListModel(R.drawable.exchange_test2,"鴨鴨2號","今天天氣真好","15:08 pm","_______________________________________"));

        userList.add(new UserListModel(R.drawable.exchange_test3,"鴨鴨3號","哈哈哈哈哈哈哈","1:02 am","_______________________________________"));

        userList.add(new UserListModel(R.drawable.exchange_test4,"鴨鴨4號","呱呱","12:55 pm","_______________________________________"));

    }
}