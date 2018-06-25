package com.example.user.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OrderDetails extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    Spinner spinner;
    DBHelper db;
    Cursor c;
    Button addo,sho,delete,m,change;
    TextView textView3;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        addo = (Button) findViewById(R.id.addo);
        sho= (Button) findViewById(R.id.sho);
        delete = (Button) findViewById(R.id.delete);
        m = (Button) findViewById(R.id.m);
        change = (Button) findViewById(R.id.change);
        loadSpinnerData();
        textView3=(TextView) findViewById(R.id.textView3);
        addo.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                SharedPreferences.Editor editor = getSharedPreferences("t", MODE_PRIVATE).edit();
                editor.putInt("id",customer_id);
                editor.apply();
                Intent myIntent = new Intent(OrderDetails.this,OrderActivity.class);
                OrderDetails.this.startActivity(myIntent);
                finish();



            }
        });
        sho.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                textView3.setText("");
                String names="Order details:\n";
                c=db.getOrder(customer_id);
                try {
                    while (c.moveToNext()) {

                        names=names+"OrderDate:"+c.getString(0)+" ,OrderDueDate: "+c.getString(1)+"\n";
                        textView3.setText(names);



                    }
                    names="";
                } finally {
                    c.close();
                }
                db.close();



            }
        });
        delete.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                SharedPreferences.Editor editor = getSharedPreferences("t", MODE_PRIVATE).edit();
                editor.putInt("id",customer_id);
                editor.apply();
                Intent myIntent = new Intent(OrderDetails.this,deleteOrder.class);
                OrderDetails.this.startActivity(myIntent);
                finish();
            }
        });


        m.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent myIntent=new Intent(OrderDetails.this,MainActivity.class);
                OrderDetails.this.startActivity(myIntent);


            }
        });
        change.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                SharedPreferences.Editor editor = getSharedPreferences("t", MODE_PRIVATE).edit();
                editor.putInt("id",customer_id);
                editor.apply();
                finish();
                Intent myIntent=new Intent(OrderDetails.this,ChangeOrder.class);
                OrderDetails.this.startActivity(myIntent);


            }
        });





    }
    private void loadSpinnerData() {
        // database handler

        db = new DBHelper(OrderDetails.this);
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
