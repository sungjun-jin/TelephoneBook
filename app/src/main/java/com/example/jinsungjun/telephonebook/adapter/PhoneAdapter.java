package com.example.jinsungjun.telephonebook.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jinsungjun.telephonebook.R;
import com.example.jinsungjun.telephonebook.domain.Phone;

import java.util.ArrayList;
import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.Holder> {

    List<Phone> data = new ArrayList<>();


    public void setDataAndRefresh(List<Phone> data) {

        this.data = data;
        notifyDataSetChanged();

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        Phone phone = data.get(position);

        holder.setName(phone.name);
        holder.setNo(phone.id   );
        holder.setPhoneNumber(phone.phoneNumber);
        holder.phone = phone;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView textNo, textName;
        private TextView textNumber;
        Button btnCall;
        LinearLayout item;
        Phone phone;

        public Holder(View itemView) {
            super(itemView);
            textNo = itemView.findViewById(R.id.textNo);
            textName = itemView.findViewById(R.id.textName);
            textNumber = itemView.findViewById(R.id.textNumber);
            btnCall = itemView.findViewById(R.id.btnCall);
            item = itemView.findViewById(R.id.item);

            btnCall.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("MissingPermission")
                @Override
                public void onClick(View view) {
                    Uri phoneUri = Uri.parse("tel:"+phone.phoneNumber);
                    Intent intent = new Intent(Intent.ACTION_DIAL,phoneUri);
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void setPhoneNumber(String number) {

            textNumber.setText(number);
        }

        public void setNo(String no) {

            textNo.setText(no);
        }

        public void setName(String name) {

            textName.setText(name);
        }
    }
}
