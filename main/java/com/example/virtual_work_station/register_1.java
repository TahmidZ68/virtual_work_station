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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register_1 extends AppCompatActivity {
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

        boolean isUserIdRemembered = false;

        if (isUserExist) {
            if (sharedPreferences.getBoolean("RM_LOGIN", false)) {
                Intent i = new Intent(register_1.this, MainActivity.class);
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


            if(userId.isEmpty())
            {
                etUserId.setError("Enter an email address");
                etPassword1.requestFocus();
                return;
            }

            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(userId).matches())
            {
                etUserId.setError("Enter a valid email address");
                etUserId.requestFocus();
                return;
            }

            //checking the validity of the password
            if(userId.isEmpty())
            {
                etPassword1.setError("Enter a password");
                etPassword1.requestFocus();
                return;
            }
            if (!password1.equals(password2)) {
                return;
            }
         //   mAuth.signInWithCustomToken(userId,password1).addOnCompleteListener(this, new OnCompleteListener<task>() {
         //       @Override
            //            public void onComplete(@NonNull Task<AuthResult> task) {
                 //           if (task.isSuccessful()) {
            //                    Toast.makeText(getApplicationContext(),"Register is succesfull",Toast.LENGTH_SHORT).show();

                //            } else {
                 //               // If sign in fails, display a message to the user.

                //            }
               //         }
               //     });

                prefsEditor = sharedPreferences.edit();
                prefsEditor.putString("USER_ID", userId);
                prefsEditor.putString("PASSWORD", password1);
                prefsEditor.putBoolean("RM_USER_ID", isUserRemembered);
                prefsEditor.putBoolean("RM_LOGIN", isLoginRemembered);
                prefsEditor.apply();

                Intent i = new Intent(register_1.this, set_up.class);
                startActivity(i);


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



