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

public class ChangeOrder extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    Spinner spinner1,spinner2,spinner3;
    DBHelper db;
    Cursor c;
    Button change,cancel;
    TextView r;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_order);
        //get the id of customer
        SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
        int id=prefs.getInt("id",0);
        db = new DBHelper(ChangeOrder.this);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);
        change =(Button) findViewById(R.id.change);
        cancel =(Button) findViewById(R.id.cancel);

        loadSpinnerData(id,spinner1);
        loadSpinner2Data();
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int new_customer_id = Integer.parseInt(spinner2.getSelectedItem().toString());
                loadSpinnerData(new_customer_id,spinner3);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        cancel.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent myIntent=new Intent(ChangeOrder.this,OrderDetails.class);
                ChangeOrder.this.startActivity(myIntent);

            }
        });
       change.setOnClickListener( new View.OnClickListener() {
           public void onClick(View v) {

                SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
                int original_customer_id=prefs.getInt("id",0);
                Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
                String Order = spinner1.getSelectedItem().toString();
                String[] a = Order.split(",");
                Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
                int new_customer_id = Integer.parseInt(spinner2.getSelectedItem().toString());
                Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
                String new_Order = spinner3.getSelectedItem().toString();
                 String[] n = new_Order.split(",");
               r=(TextView)findViewById(R.id.textView3);
               //r.setText("n:"+n[1] +", "+n[2]+",a: "+a[1]+", "+a[2]);
               int order_id_original= Integer.parseInt(a[0]);
               int order_id_new=Integer.parseInt(n[0]);
                db.updateOrder(new_customer_id,a[1],a[2],order_id_new);
                db.updateOrder(original_customer_id,n[1],n[2],order_id_original);
               Intent myIntent=new Intent(ChangeOrder.this,OrderDetails.class);
               ChangeOrder.this.startActivity(myIntent);
               finish();

            }
        });


    }

    private void loadSpinnerData(int id ,Spinner spinner) {
        // database handler

        db = new DBHelper(ChangeOrder.this);
        // Spinner Drop down elements
        List<String> lables = db.getOrderLabelswithId(id);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    private void loadSpinner2Data() {
        // database handler

        db = new DBHelper(ChangeOrder.this);
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter);
    }

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
