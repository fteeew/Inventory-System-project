package com.example.user.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AddPayment extends AppCompatActivity
        {
    DBHelper db;
    Cursor c;
    EditText Amount,D;
    Button add,r;
    TextView text;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
        Amount = (EditText) findViewById(R.id.amount);
        D = (EditText) findViewById(R.id.date);
        add=(Button)findViewById(R.id.aa);
        db = new DBHelper(AddPayment.this);
        r = (Button) findViewById(R.id.r);


        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
                 int id=prefs.getInt("id",0);
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                format.setLenient(false);
                try {
                    double amount = Double.parseDouble(Amount.getText().toString());
                    String date=D.getText().toString();
                    format.parse(date);
                    Payment p =new Payment(date,amount,id);
                    db.insertPayment(p);
                    Intent myIntent = new Intent(AddPayment.this,LoadData.class);
                    AddPayment.this.startActivity(myIntent);
                    finish();

                }catch(NumberFormatException e ) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Your should enter double value.",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                    toast.show();


                } catch (ParseException e) {

                    Toast toast = Toast.makeText(getApplicationContext(), "date is not valid according to dd/MM/yyyy pattern",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                    toast.show();

                }

            }

        });

        r.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(AddPayment.this,LoadData.class);
                AddPayment.this.startActivity(myIntent);
                finish();


            }
        });
    }

}
