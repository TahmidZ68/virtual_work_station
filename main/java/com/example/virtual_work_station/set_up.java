package com.example.virtual_work_station;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;



import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class set_up extends AppCompatActivity {
    DatabaseReference Reference;
    ImageView image;
    FloatingActionButton fab;
    private EditText uname, buname,budescription,bulocation;
    private String selectedId1 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_up);
        System.out.println(MainActivity.globalcounter + "add_member-onCreate()");
        MainActivity.globalcounter++;
        Reference = FirebaseDatabase.getInstance().getReference("business");

        uname = findViewById(R.id.uname);
        buname = findViewById(R.id.buname);
        bulocation = findViewById(R.id.bulocation);
        budescription = findViewById(R.id.budescription);


      //  fab = findViewById(R.id.fab);
        //EditText etcontact = findViewById(R.id.mem_contact);
        //EditText etdepartment = findViewById(R.id.mem_dept);
        //EditText etdate1 = findViewById(R.id.mem_date);


        // System.out.println("Name: " + uname.getText().toString());
        //  System.out.println("Email: " + buname.getText().toString());
        //  System.out.println("Contact Number: " + etcontact.getText().toString());
        //System.out.println("Department: " + etdepartment.getText().toString());
        //System.out.println("Joining date: " + etdate1.getText().toString());


        // if (i.hasExtra("id")) {
        //       selectedId1 = i.getStringExtra("id");
        //       String UName = i.getStringExtra("Name");
        //       String Buname = i.getStringExtra("Email");
        //      String Contact = i.getStringExtra("Contact");
        //      String Department = i.getStringExtra("Department");
        //    String Date = i.getStringExtra("date");
        // String Poins = i.getStringExtra("type");


        //etcontact.setText(Contact);
        //etdepartment.setText(Department);
        //  etdate1.setText(Date);


        // }



        Button save_btn = findViewById(R.id.save_btn);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               // set_business();
                String name = uname.getText().toString();
                String busname = buname.getText().toString();

                Intent i = new Intent(set_up.this, MainActivity.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
                startActivity(i);


            }

        });

    }

  /*  private void set_business() {
        String Name = uname.getText().toString().trim();
        String Bus_name = buname.getText().toString().trim();
        String location = bulocation.getText().toString().trim();
        String Description = budescription.getText().toString().trim();


        String key1 = Reference.push().getKey();

        Business business =new Business(Name,Bus_name,location,Description);
        Reference.child(key1).setValue(business);

        Toast.makeText(getApplicationContext(),"Business is set",Toast.LENGTH_LONG).show();

        uname.setText("");
        buname.setText("");
        bulocation.setText("");
        budescription.setText("");
    }*/
}








