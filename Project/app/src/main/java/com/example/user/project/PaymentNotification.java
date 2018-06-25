package com.example.user.project;

import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;

public class PaymentNotification extends AppCompatActivity {

    DBHelper db;
    Cursor c;
    Button m;
    TextView textView3;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_notification);
         m = (Button) findViewById(R.id.m);
        db = new DBHelper(PaymentNotification.this);
        Calendar c = Calendar.getInstance();
        DateFormat format = new java.text.SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        String formattedDate = format.format(c.getTime());
        textView3.setText(""+formattedDate );





        m.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(PaymentNotification.this,OrderDetails.class);
                PaymentNotification.this.startActivity(myIntent);
                finish();


            }
        });


    }
}
