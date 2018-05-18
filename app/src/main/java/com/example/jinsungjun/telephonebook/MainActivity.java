package com.example.jinsungjun.telephonebook;

import android.Manifest;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jinsungjun.telephonebook.adapter.PhoneAdapter;
import com.example.jinsungjun.telephonebook.domain.Phone;
import com.example.jinsungjun.telephonebook.domain.PhoneDAO;

import java.util.ArrayList;
import java.util.List;

    /*
    18.05.18 전화번호부 가져오기 실습
     */




public class MainActivity extends BaseActivity {


    List<Phone> data = new ArrayList<>();
    PhoneAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        loadData();
        setRecycler();
        setDataToAdapter();

    }

    //주소록 가져오기
    private void loadData() {
        data = PhoneDAO.getPhoneList(this);
    }

    private void setRecycler() {
        adapter = new PhoneAdapter();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setDataToAdapter() {
        adapter.setDataAndRefresh(data);
    }



}
