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

import java.lang.reflect.Array;
import java.util.List;

public class deletePayment extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{

    Spinner spinner;
    DBHelper db;
    Cursor c;
    Button delete,cancel;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_payment);
        SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
        int id=prefs.getInt("id",0);
        db = new DBHelper(deletePayment.this);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        delete =(Button) findViewById(R.id.delete);
        cancel=(Button) findViewById(R.id.cancel);
        loadSpinnerData(id);
        delete.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                String payment = spinner.getSelectedItem().toString();
                SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
                int id=prefs.getInt("id",0);
                String[] a = payment.split(",");
                db.deletePayment(id,a[0]);
                Intent myIntent = new Intent(deletePayment.this,LoadData.class);
                deletePayment.this.startActivity(myIntent);
                finish();



            }
        });
        cancel.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent myIntent=new Intent(deletePayment.this,LoadData.class);
                deletePayment.this.startActivity(myIntent);

            }
        });


    }
    private void loadSpinnerData(int id ) {
        // database handler

        db = new DBHelper(deletePayment.this);
        // Spinner Drop down elements
        List<String> lables = db.getPaymentLabels(id);

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
