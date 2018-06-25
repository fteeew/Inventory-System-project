package com.example.user.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.database.Cursor;
import static android.R.attr.id;
import static com.example.user.project.DBHelper.TABLE_NAME;
public class MainActivity extends AppCompatActivity {
    Button add,display,order,payment,A,delete,not;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add=(Button)findViewById(R.id.add_customer);
        display=(Button)findViewById(R.id.display_customer);
        order=(Button)findViewById(R.id.customers_order);
        payment=(Button)findViewById(R.id.customers_payment);
        A=(Button)findViewById(R.id.A);
        delete=(Button)findViewById(R.id.delete);
        not=(Button)findViewById(R.id.not);
        add.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,AddCustomer.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        display.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,CustomerDisplay.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        payment.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,LoadData.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        order.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,OrderDetails.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        A.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,AddProduct.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        delete.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,deleteCustomer.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });
        not.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(MainActivity.this,PaymentNotification.class);
                MainActivity.this.startActivity(myIntent);
                finish();



            }
        });


    }
}
