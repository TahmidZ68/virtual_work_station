package com.example.virtual_work_station;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Button;
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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_member extends AppCompatActivity {

    private EditText etname, etdepartment, etemail, etdate1, etcontact;
    private String selectedId1 = "";
    private TextView your_name,business_name;
     DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_member);
        System.out.println(MainActivity.globalcounter + "add_member-onCreate()");
        MainActivity.globalcounter++;

        your_name =findViewById(R.id.y_name);
        business_name =findViewById(R.id.b_name);

        String uname = getIntent().getStringExtra("keyname");
        String buname = getIntent().getStringExtra("keynames");

        your_name.setText(uname);
        business_name.setText(buname);

        databaseReference = FirebaseDatabase.getInstance().getReference("members");

        etname = findViewById(R.id.mem_name);
        etdepartment = findViewById(R.id.mem_dept);
        etemail = findViewById(R.id.mem_email);
        etcontact = findViewById(R.id.mem_contact);
        etdate1 = findViewById(R.id.mem_date);

       /* Intent i = getIntent();
        EditText etname = findViewById(R.id.mem_name);
        EditText etemail = findViewById(R.id.mem_email);
        EditText etcontact = findViewById(R.id.mem_contact);
        EditText etdepartment = findViewById(R.id.mem_dept);
        EditText etdate1 = findViewById(R.id.mem_date);


        System.out.println("Name: " + etname.getText().toString());
        System.out.println("Email: " + etemail.getText().toString());
        System.out.println("Contact Number: " + etcontact.getText().toString());
        System.out.println("Department: " + etdepartment.getText().toString());
        System.out.println("Joining date: " + etdate1.getText().toString());



            if (i.hasExtra("id")) {
                selectedId1 = i.getStringExtra("id");
                String Name = i.getStringExtra("Name");
                String Email = i.getStringExtra("Email");
                String Contact = i.getStringExtra("Contact");
                String Department = i.getStringExtra("Department");
                String Date = i.getStringExtra("date");
                // String Poins = i.getStringExtra("type");


                etname.setText(Name);
                etemail.setText(Email);
                etcontact.setText(Contact);
                etdepartment.setText(Department);
                etdate1.setText(Date);


            }
*/

        Button SaveButton = findViewById(R.id.SaveButton);
        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       saveMember();

                String name = your_name.getText().toString();
                String busname = business_name.getText().toString();
                Intent i = new Intent(add_member.this,MainActivity.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
                startActivity(i);
               /*     if (selectedId1.isEmpty()) {
                        selectedId1 = etname.getText().toString() + "-" + System.currentTimeMillis();



                    String eventValues = etname.getText().toString() + "_____" + etemail.getText().toString() + "_____" + etcontact.getText().toString() + "_____" + etdepartment.getText().toString() + "_____" + etdate1.getText().toString() + "_____";

                    Utils.getInstance().setKeyValue(add_member.this, selectedId1, eventValues);
                    Toast.makeText(getApplicationContext(), "Items has been updated", Toast.LENGTH_SHORT).show();
                    finish();
                }*/

            }

        });

    }

    private void saveMember() {

        String Name = etname.getText().toString().trim();
        String Email = etemail.getText().toString().trim();
        String Contact = etcontact.getText().toString().trim();
        String Department = etdepartment.getText().toString().trim();
        String Date = etdate1.getText().toString().trim();

       String key = databaseReference.push().getKey();

        Member member =new Member(Name,Email,Contact,Department,Date);
        databaseReference.child(key).setValue(member);

        Toast.makeText(getApplicationContext(),"Member is added",Toast.LENGTH_LONG).show();

        etname.setText("");
        etemail.setText("");
        etcontact.setText("");
        etdepartment.setText("");
        etdate1.setText("");

    }
}




