package com.example.virtual_work_station;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Login extends AppCompatActivity {
    private EditText etUserId, etPassword1, etPassword2;
    private CheckBox cbRemUser, cbRemLogin;
    private boolean isUserExist = false;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        System.out.println(MainActivity.globalcounter + "create_project_list-onCreate()");
        MainActivity.globalcounter++;
        mAuth = FirebaseAuth.getInstance();
        this.setTitle("Login");
        setContentView(R.layout.log_in);


        etUserId = findViewById(R.id.etusername);
        etPassword1 = findViewById(R.id.etpass1);

        TextView Reg_2 = findViewById(R.id.reg_2);
        Reg_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter + "mainActivity-CreateProject Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(Login.this, registration.class);
                startActivity(i);

            }
        });



        findViewById(R.id.btngo).setOnClickListener(r -> {

            userLogin();

        });
    }

    private void userLogin() {
        String email = etUserId.getText().toString().trim();
        String password = etPassword1.getText().toString().trim();

        if(email.isEmpty())
        {
            etUserId.setError("Enter an email address");
            etPassword1.requestFocus();
            return;
        }

        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            etUserId.setError("Enter a valid email address");
            etUserId.requestFocus();
            return;
        }

        //checking the validity of the password
        if(password.isEmpty())
        {
            etPassword1.setError("Enter a password");
            etPassword1.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            etPassword1.setError("Minimum password legth is 6");
            etPassword1.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Login is succesfull",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Login.this, set_up.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                } else {

                        Toast.makeText(getApplicationContext(), "Login is not succesfull", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}