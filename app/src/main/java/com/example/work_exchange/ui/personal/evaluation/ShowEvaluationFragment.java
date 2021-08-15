package com.example.work_exchange.ui.personal.evaluation;

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


public class ShowEvaluationFragment extends Fragment {

    RecyclerView mrecyclerView;
    List<EvaluationListModel> userList;
    EvaluationListAdapter adapter;

    public ShowEvaluationFragment() {
        // Required empty public constructor
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_evaluation, container, false);

        initData();


        mrecyclerView= view.findViewById(R.id.RecyclerView);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new EvaluationListAdapter(userList);

        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        return view;
    }




    private void initData() {
        userList = new ArrayList<>();

        userList.add(new EvaluationListModel(R.drawable.exchange_test1,"鴨鴨1","這次打工換宿有趣有趣有趣！","10:45 am"));

        userList.add(new EvaluationListModel(R.drawable.exchange_test2,"鴨鴨2","業主好兇","15:08 pm"));

        userList.add(new EvaluationListModel(R.drawable.exchange_test3,"鴨鴨3","風景好看呵呵呵呵呵呵呵呵呵呵呵呵","1:02 am"));

        userList.add(new EvaluationListModel(R.drawable.exchange_test4,"鴨鴨4","以後會再來換宿喔！","12:55 pm"));

    }
}
