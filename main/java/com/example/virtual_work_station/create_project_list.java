package com.example.virtual_work_station;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class create_project_list extends AppCompatActivity {

    private EditText ettitle, etdate, ettype, etmember, etdescription, etlocation;
    private String selectedId = "";
    RadioGroup rg;
    RadioButton rb;
    private TextView your_name, business_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project);
        System.out.println(MainActivity.globalcounter + "create_project_list-onCreate()");
        MainActivity.globalcounter++;
        ettitle = findViewById(R.id.prtitle);
        etdate = findViewById(R.id.prdate);
        etdescription = findViewById(R.id.prdescription);
        etlocation = findViewById(R.id.prlocation);
        etmember = findViewById(R.id.prmember);
        // ettype = findViewById(R.id.RGT);

        your_name = findViewById(R.id.y_name);
        business_name = findViewById(R.id.b_name);

        String uname = getIntent().getStringExtra("keyname");
        String buname = getIntent().getStringExtra("keynames");

        your_name.setText(uname);
        business_name.setText(buname);

        Intent i = getIntent();

        EditText ettitle = findViewById(R.id.prtitle);
        EditText etdescription = findViewById(R.id.prdescription);
        EditText etlocation = findViewById(R.id.prlocation);
        EditText etmember = findViewById(R.id.prmember);
        EditText etdate = findViewById(R.id.prdate);
        RadioGroup ettype = findViewById(R.id.RGT);
        int selected_id = ettype.getCheckedRadioButtonId();
        RadioButton RB = findViewById(selected_id);


        if (i.hasExtra("id")) {
            selectedId = i.getStringExtra("id");
            String title = i.getStringExtra("title");
            String description = i.getStringExtra("description");
            String location = i.getStringExtra("location");
            String member = i.getStringExtra("member");
            String date = i.getStringExtra("date");
            String type = i.getStringExtra("type");


            ettitle.setText(title);
            etlocation.setText(location);
            etdate.setText(date);


        }







            //   type = findViewById(R.id.RGT);
            //   etDateTime = findViewById(R.id.DateTime);
            //     etEmail = findViewById(R.id.Email);
            //   etPhone = findViewById(R.id.Phone);
            //   etCapacity = findViewById(R.id.Capacity);
            //   etBudget = findViewById(R.id.Budget);
            //   etDescription = findViewById(R.id.Description);

            //RGT.setText(type);


        //  etDateTime.setText(datetime);
        //  etEmail.setText(email);
        //  etPhone.setText(phone);
        //  etCapacity.setText(capacity);
        //   etBudget.setText((int) budget);
        //   etDescription.setText(description);
        //  etDateTime.setText(datetime);





    Button AssignButton = findViewById(R.id.assignbtn);
        AssignButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){


                if(selectedId.isEmpty()){
                    selectedId=ettitle.getText().toString()+"-"+System.currentTimeMillis();
                }



                String eventValue = ettitle.getText().toString() + "_____" + etdescription.getText().toString() + "_____" + etlocation.getText().toString() + "_____" + etmember.getText().toString() + "_____" + etdate.getText().toString() + "_____" + ettype.getCheckedRadioButtonId() + "_____";

                Util.getInstance().setKeyValue(create_project_list.this,selectedId,eventValue);
               // Toast.makeText(getApplicationContext(),"Items has been updated",Toast.LENGTH_SHORT).show();
                finish();



            String name = your_name.getText().toString();
            String busname = business_name.getText().toString();
            Intent i = new Intent(create_project_list.this,MainActivity.class);
            i.putExtra("keyname",name);
            i.putExtra("keynames",busname);
            startActivity(i);
        }

    });

}


}














