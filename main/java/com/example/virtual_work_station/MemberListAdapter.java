package com.example.virtual_work_station;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MemberListAdapter extends ArrayAdapter<Member> {
    private Activity context;
    private List<Member> memberList;

    public MemberListAdapter(Activity context, List<Member>  memberList) {
        super(context, R.layout.layout_mem, memberList);

        this.context = context;
        this.memberList = memberList;


    }

    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        LayoutInflater inflater;
        inflater = context.getLayoutInflater();
        View view= inflater.inflate(R.layout.layout_mem,parent,false);

        Member member = memberList.get(position);

        TextView Membername = view.findViewById(R.id.memname);
        TextView Memberdepartment = view.findViewById(R.id.memdept);
        TextView Memberemail =view.findViewById(R.id.mememail);
        TextView Membercontact =view.findViewById(R.id.memcontact);

       /// Member e = this.getItem(position);

        Membername.setText(member.name);
        Memberdepartment.setText(member.department);
        Memberemail.setText(member.email);
        Membercontact.setText(member.contact);
        return view;
    }
}
