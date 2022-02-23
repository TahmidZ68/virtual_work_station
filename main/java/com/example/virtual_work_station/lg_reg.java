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

public class lg_reg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_reg);
        System.out.println(MainActivity.globalcounter + "create_project_list-onCreate()");
        MainActivity.globalcounter++;

        TextView Log_1 = findViewById(R.id.lg_1);
        Log_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter + "mainActivity-CreateProject Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(lg_reg.this,Login.class);
                startActivity(i);

            }
        });

        TextView Registration_1 = findViewById(R.id.reg_1);
        Registration_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter + "mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(lg_reg.this, registration.class);
                startActivity(i);

            }
        });
    }
}