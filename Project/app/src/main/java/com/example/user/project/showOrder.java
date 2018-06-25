package com.example.user.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class showOrder extends AppCompatActivity {

    DBHelper db;
    Cursor c;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DBHelper(showOrder.this);
        LinearLayout objFirstLinearLayout = new LinearLayout(this);
        LinearLayout objSecondLinearLayout= new LinearLayout(this);
        objFirstLinearLayout .setOrientation(LinearLayout.VERTICAL);
        objSecondLinearLayout.setOrientation(LinearLayout.VERTICAL);
        Button r = new Button(this);
        r.setText("customer list");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        r.setLayoutParams(lp);
        objFirstLinearLayout .addView(r);
        ScrollView objScrollView = new ScrollView(this);
        objFirstLinearLayout .addView(objScrollView);
        setContentView(objFirstLinearLayout);
        r.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Intent myIntent = new Intent(showOrder.this,CustomerDisplay.class);
                showOrder.this.startActivity(myIntent);
                finish();
            }
        });
        String names="Orders Details:\n";
        SharedPreferences prefs = getSharedPreferences("t", MODE_PRIVATE);
        int id=prefs.getInt("id",0);
        c=db.getOrder(id);
        try {
            while (c.moveToNext()) {
                TextView txtCustomerInfo = new TextView(this);
                names=names+c.getString(0)+" "+c.getString(1)+"\n";
                txtCustomerInfo .setText(names);
                names="";
                Button delete=new Button(this);
                delete.setText("delete Order");
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                delete.setLayoutParams(layoutParams);
                objSecondLinearLayout.addView(delete);
                delete.setOnClickListener( new View.OnClickListener() {
                    public void onClick(View v) {


                    }
                });
            }
        } finally {
            c.close();
        }
        db.close();

    }
}
