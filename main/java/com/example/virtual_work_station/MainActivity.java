package com.example.virtual_work_station;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int globalcounter = 0;
    private ListView eventLists;
    private TextView your_name,business_name;
    private  EventListAdaptar adapter;
    private  ArrayList<Event> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_1);
        System.out.println(MainActivity.globalcounter+"mainActivity-onCreate()");
        MainActivity.globalcounter++;

        your_name =findViewById(R.id.y_name);
        business_name =findViewById(R.id.b_name);

        String uname = getIntent().getStringExtra("keyname");
        String buname = getIntent().getStringExtra("keynames");

        your_name.setText(uname);
        business_name.setText(buname);



        eventLists = findViewById(R.id.ListEvents);
        eventLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event selectedEvent = items.get(position);
                Intent i = new Intent(MainActivity.this,create_project_list.class);
                i.putExtra("id",selectedEvent.id);
                i.putExtra("title",selectedEvent.title);
                i.putExtra("Description",selectedEvent.description);
                i.putExtra("location",selectedEvent.location);
                i.putExtra("Member",selectedEvent.member);
                i.putExtra("date",selectedEvent.date);
                i.putExtra("Type",selectedEvent.type);
                startActivity(i);


            }
        });



        TextView CreateNewButton = findViewById(R.id.Create_project_btn);
        CreateNewButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-CreateProject Button was pressed.");
                MainActivity.globalcounter++;

                String name = your_name.getText().toString();
                String busname = business_name.getText().toString();
                Intent i = new Intent(MainActivity.this,create_project_list.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
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
                Intent i = new Intent(MainActivity.this,add_member.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
                startActivity(i);

            }
        });
        TextView Member_list = findViewById(R.id.Member_list_btn);
        Member_list.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                System.out.println(MainActivity.globalcounter+"mainActivity-AddMember Button was pressed.");
                MainActivity.globalcounter++;

                Intent i = new Intent(MainActivity.this,member_list.class);
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

                String name = your_name.getText().toString();
                String busname = business_name.getText().toString();
                Intent i = new Intent(MainActivity.this,home_page2.class);
                i.putExtra("keyname",name);
                i.putExtra("keynames",busname);
                startActivity(i);

            }
        });


        eventLists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Event selectedEvent = items.get(position);
                System.out.println(selectedEvent.id);

               showDialog("Do you want to delete "+selectedEvent.title,"Info",selectedEvent);
                System.out.println("Deleted");
                return true;
            }
        });

        }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println(MainActivity.globalcounter+"mainActivity-onStart()");
        MainActivity.globalcounter++;

    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println(MainActivity.globalcounter+"mainActivity-onResume()");
        MainActivity.globalcounter++;

        items.clear();

        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs" );
        System.out.println("Number of rows: "+rows.getCount());

        while (rows.moveToNext()){
            String key = rows.getString(0);
            String value = rows.getString(1);
            String[] values = value.split("_____");
            System.out.println("Project title: "+values[0]);
             System.out.println("Project description: "+values[1]);
            System.out.println("Project location: "+values[2]);
            System.out.println("Project member : "+values[3]);
            System.out.println("Project date: "+values[4]);
            System.out.println("Project priority: "+values[5]);


            String[] subsString = value.split("_____");
            String title = subsString[0];
            String description = "";//subsString[1];
            String location ="";//subsString[2];
            String member ="";//subsString[3];
            String date ="MM dd, YYYY";
            String type =subsString[5];


            //can be issue
           items.add(new Event(key,title,description,location,member,date,type));
        }

        adapter = new EventListAdaptar(this, R.layout.layout_row,items );
        eventLists.setAdapter(adapter);

    }
    private void showDialog(String message, String title, Event event){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Util.getInstance().deleteByKey(MainActivity.this, event.id);
                        dialog.cancel();
//initializeCustomEventList();
                        items.remove(event);
                       adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                       dialog.cancel();
                    }
                });

//Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(MainActivity.globalcounter+"mainActivity-onPause()");
        MainActivity.globalcounter++;
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println(MainActivity.globalcounter+"mainActivity-onStop()");
        MainActivity.globalcounter++;
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println(MainActivity.globalcounter+"mainActivity-onRestart()");
        MainActivity.globalcounter++;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(MainActivity.globalcounter+"mainActivity-onDestroy()");
        MainActivity.globalcounter++;
    }
}