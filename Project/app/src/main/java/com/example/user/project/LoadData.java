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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class LoadData extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {


    Spinner spinner;
    DBHelper db;
    Cursor c;
    Button addp,shp,m,delete;
    TextView textView3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        // Spinner element
        spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Loading spinner data from database
        addp = (Button) findViewById(R.id.addp);
        shp = (Button) findViewById(R.id.shp);
        delete = (Button) findViewById(R.id.delete);
        m = (Button) findViewById(R.id.m);
        loadSpinnerData();
        textView3=(TextView) findViewById(R.id.textView3);
        addp.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                SharedPreferences.Editor editor = getSharedPreferences("t", MODE_PRIVATE).edit();
                editor.putInt("id",customer_id);
                editor.apply();
                Intent myIntent = new Intent(LoadData.this,AddPayment.class);
                LoadData.this.startActivity(myIntent);
                finish();



            }
        });
        shp.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Spinner spinner = (Spinner)findViewById(R.id.spinner);
                int customer_id = Integer.parseInt(spinner.getSelectedItem().toString());
                textView3.setText("");
                String names="payment details:\n";
                c=db.getPayment(customer_id);
                try {
                    while (c.moveToNext()) {

                        names=names+"PaymentDate:"+c.getString(0)+" , Amount: "+c.getString(1)+"\n";
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
        Intent myIntent = new Intent(LoadData.this,deletePayment.class);
        LoadData.this.startActivity(myIntent);
        finish();
            }
        });
        m.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                finish();
                Intent myIntent=new Intent(LoadData.this,MainActivity.class);
                LoadData.this.startActivity(myIntent);


            }
        });
    }


    private void loadSpinnerData() {
        // database handler

        db = new DBHelper(LoadData.this);
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
