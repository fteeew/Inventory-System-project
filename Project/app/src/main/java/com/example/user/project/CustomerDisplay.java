package com.example.user.project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.view.View;
import android.widget.TextView;

public class CustomerDisplay extends AppCompatActivity {
    DBHelper db;
    Cursor c;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(CustomerDisplay.this);
        LinearLayout objFirstLinearLayout = new LinearLayout(this);
        LinearLayout objSecondLinearLayout= new LinearLayout(this);
        objFirstLinearLayout .setOrientation(LinearLayout.VERTICAL);
        objSecondLinearLayout.setOrientation(LinearLayout.VERTICAL);
        // add button to add customer to the first layout
         Button btnAddCustomer = new Button(this);
         btnAddCustomer.setText("Main page");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        btnAddCustomer.setLayoutParams(lp);
        // add the button to first layout
        objFirstLinearLayout .addView(btnAddCustomer);
        //delete all customer button

        // add scroll view to the second layout
        ScrollView objScrollView = new ScrollView(this);
        objScrollView.addView(objSecondLinearLayout);
        // add the scroll to the first layout
        objFirstLinearLayout .addView(objScrollView);
        // sett the First Linear Layout as a main content view for CustomerMain Activity.
        setContentView(objFirstLinearLayout);

        btnAddCustomer.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(CustomerDisplay.this,MainActivity.class);
                CustomerDisplay.this.startActivity(myIntent);
                finish();
            }
        });

        String names = "Customer Name:\n";
        c = db.getAllCustomer();
        try {
            while (c.moveToNext()) {
                TextView txtCustomerInfo = new TextView(this);
                names=names+ "ID:" +c.getInt(0) +"\nname:"+ c.getString(1) + "\nAdress:" + c.getString(2) + "\nPhone: " + c.getString(3) + "\n";
                txtCustomerInfo.setText(names);
                objSecondLinearLayout.addView(txtCustomerInfo);
                names="";

            }




        } finally {
            c.close();
        }
        db.close();



    }
}
