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
import android.widget.Toast;

import java.util.List;

public class deleteCustomer extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    Spinner spinner;
    DBHelper db;
    Cursor c;
    Button delete,cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);
        db = new DBHelper(deleteCustomer.this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        delete =(Button) findViewById(R.id.delete);
        cancel =(Button) findViewById(R.id.cancel);
        ///////////////////////////////////////////
        loadSpinnerData();
        delete.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                db.deleteCustomer(customer_id);
                Intent myIntent = new Intent(deleteCustomer.this,MainActivity.class);
                deleteCustomer.this.startActivity(myIntent);
                finish();



            }
        });
        cancel.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(deleteCustomer.this,MainActivity.class);
                deleteCustomer.this.startActivity(myIntent);
                finish();



            }
        });




    }
    private void loadSpinnerData() {
        // database handler

        db = new DBHelper(deleteCustomer.this);
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
