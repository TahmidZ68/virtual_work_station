package com.example.virtual_work_station;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class home_page2 extends AppCompatActivity {
    private TextView your_name,business_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_2);
        System.out.println(MainActivity.globalcounter + "create_project_list-onCreate()");
        MainActivity.globalcounter++;
        this.setTitle("Home Page-2");

        your_name =findViewById(R.id.y_name);
        business_name =findViewById(R.id.b_name);

        String uname = getIntent().getStringExtra("keyname");
        String buname = getIntent().getStringExtra("keynames");

        your_name.setText(uname);
        business_name.setText(buname);

        TextView CreateNewButton = findViewById(R.id.Create_project_btn);
        CreateNewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-CreateProject Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(home_page2.this,create_project_list.class);
                startActivity(i);

            }
        });

        TextView AddMember = findViewById(R.id.Add_member_btn);
        AddMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;

                String name = your_name.getText().toString();
                String busname = business_name.getText().toString();
                Intent i = new Intent(home_page2.this,add_member.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
                startActivity(i);

            }
        });
        TextView Memberlist = findViewById(R.id.Member_list_btn);
        Memberlist.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(home_page2.this,member_list.class);
                startActivity(i);

            }
        });
        TextView home_page2 = findViewById(R.id.pro_btn);
        home_page2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(home_page2.this,MainActivity.class);
                startActivity(i);

            }
        });

        TextView log_out = findViewById(R.id.lgout_btn);
        log_out.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent i = new Intent(home_page2.this,lg_reg.class);
               startActivity(i);



            }
        });

    }
}