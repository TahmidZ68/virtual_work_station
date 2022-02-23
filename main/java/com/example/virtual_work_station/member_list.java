package com.example.virtual_work_station;

import android.view.View;
import android.widget.AdapterView;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class member_list extends AppCompatActivity {

    private ListView List_member;
    private MemberListAdapter memberListAdapter;
    DatabaseReference databaseReference;
    private List<Member> memberList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_list);
        System.out.println(MainActivity.globalcounter + "member_list-onCreate()");
        MainActivity.globalcounter++;
        databaseReference = FirebaseDatabase.getInstance().getReference("members");

        memberList =new ArrayList<>();
        memberListAdapter =new MemberListAdapter(member_list.this,memberList);

       List_member = findViewById(R.id.member_lists);




    }
    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot1: snapshot.getChildren()){

                    Member member = dataSnapshot1.getValue(Member.class);
                    memberList.add(member);
                    System.out.println(member);
                }

                List_member.setAdapter(memberListAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
        super.onStart();
        System.out.println(MainActivity.globalcounter+"member_list-onStart()");
        MainActivity.globalcounter++;


    }


}


