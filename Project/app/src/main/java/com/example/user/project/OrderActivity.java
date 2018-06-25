package com.example.user.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import java.util.List;

public class OrderActivity extends AppCompatActivity
         {


    DBHelper db;
    Cursor c;
    EditText DueDate,Date;
    Button add,r;
    TextView text;
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        add = (Button) findViewById(R.id.aa);
        r = (Button) findViewById(R.id.r);
        Date = (EditText) findViewById(R.id.date);
        DueDate = (EditText) findViewById(R.id.duedate);
        db = new DBHelper(OrderActivity.this);


        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                String date1 = Date.getText().toString();
                String date2 =DueDate.getText().toString();
                SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
                int id=prefs.getInt("id",0);
                try{
                    format.parse(date1);
                    format.parse(date2);
                    Order o=new Order(date1,date2,id,0);
                    db.insertOrder(o);
                    Date.setText("Enter Order Date");
                    DueDate.setText("Enter Order DueDate");
                    finish();
                    Intent Intent=new Intent(OrderActivity .this,OrderDetails.class);
                    OrderActivity.this.startActivity(Intent);
                }catch (ParseException e) {
                    Toast toast = Toast.makeText(getApplicationContext(), "date is not valid according to dd/MM/yyyy pattern",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                    toast.show();


                }




            }
        });
        r.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(OrderActivity.this,OrderDetails.class);
                OrderActivity.this.startActivity(myIntent);
                finish();


            }
        });
    }

}
