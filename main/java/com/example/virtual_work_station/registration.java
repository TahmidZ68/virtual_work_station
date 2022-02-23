package com.example.virtual_work_station;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
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

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class registration extends AppCompatActivity {
    private EditText etUserId, etPassword1, etPassword2;
    private CheckBox cbRemUser, cbRemLogin;
    private boolean isUserExist = false;
    private FirebaseAuth mAuth;

    private SharedPreferences.Editor prefsEditor;
    private SharedPreferences sharedPreferences;
    private String existUserid = "";
    private boolean wasOpened = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getPreferences(MODE_PRIVATE);
        isUserExist = sharedPreferences.contains("RM_LOGIN");
        this.setTitle("Registration");

        boolean isUserIdRemembered = false;

        if (isUserExist) {
            if (sharedPreferences.getBoolean("RM_LOGIN", false)) {
                Intent i = new Intent(registration.this, MainActivity.class);
                startActivity(i);
                return;
            }
            isUserIdRemembered = sharedPreferences.getBoolean("RM_USER_ID", false);
            if (isUserIdRemembered) {
                existUserid = sharedPreferences.getString("USER_ID", "");

            }
        }


        setContentView(R.layout.register);


        etUserId = findViewById(R.id.etusername);
        etUserId.setText(existUserid);
        etPassword1 = findViewById(R.id.etpass1);
        etPassword2 = findViewById(R.id.etpass2);
        if (isUserExist) {
            etPassword2.setVisibility(View.GONE);
       }
        cbRemUser = findViewById(R.id.rem_userid);
        cbRemLogin = findViewById(R.id.rem_lgid);
        cbRemUser.setChecked(isUserIdRemembered);

        findViewById(R.id.btnexit).setOnClickListener(r -> finish());
        findViewById(R.id.btngo).setOnClickListener(r -> {
            String userId = etUserId.getText().toString().trim();
            String password1 = etPassword1.getText().toString().trim();
            String password2 = etPassword2.getText().toString().trim();
            boolean isUserRemembered = cbRemUser.isChecked();
            boolean isLoginRemembered = cbRemLogin.isChecked();

            userRegister();


            prefsEditor = sharedPreferences.edit();
            prefsEditor.putString("USER_ID", userId);
            prefsEditor.putString("PASSWORD", password1);
            prefsEditor.putBoolean("RM_USER_ID", isUserRemembered);
            prefsEditor.putBoolean("RM_LOGIN", isLoginRemembered);
            prefsEditor.apply();



        });
    }



    private void userRegister() {
        String email = etUserId.getText().toString().trim();
        String password = etPassword1.getText().toString().trim();
        String password2 = etPassword2.getText().toString().trim();

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
        if (!password.equals(password2)) {
            etPassword1.setError("Didn't match with confirm password!");
            etPassword1.requestFocus();
            return;
        }
        Intent i = new Intent(registration.this, set_up.class);
        startActivity(i);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Registration is succesfull",Toast.LENGTH_SHORT).show();

                } else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException) {

                        Toast.makeText(getApplicationContext(), "User already exists", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Registration is not succesfull", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



    @Override
    public void onPause() {
        super.onPause();
        wasOpened = true;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (wasOpened) {
            finish();
        }

    }

}



