package com.example.user.project;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {

    DBHelper db;
    Cursor c;
    EditText enter_Name,enter_Add,enter_phone;
    Button add,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        enter_Name = (EditText) findViewById(R.id.enterName);
        enter_Add = (EditText) findViewById(R.id.enterAdd);
        enter_phone = (EditText) findViewById(R.id.enterphone);
        add = (Button) findViewById(R.id.Add);
        cancel = (Button) findViewById(R.id.cancel);
        db = new DBHelper(AddCustomer.this);

        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
               if(isAlpha(enter_Name.getText().toString())&&isAlpha(enter_Add.getText().toString())&&enter_phone.getText().toString().matches("[0-9]+")) {
                   Customer customer = new Customer(enter_Name.getText().toString(), enter_Add.getText().toString(), enter_phone.getText().toString(), 0);
                   db.insertCustomer(customer);
                   enter_Name.setText("Enter Name");
                   enter_Add.setText("Enter Age");
                   enter_phone.setText("Enter Phone");
                   finish();
                   Intent myIntent = new Intent(AddCustomer.this, MainActivity.class);
                   AddCustomer.this.startActivity(myIntent);
               }
               else{
                   Toast toast = Toast.makeText(getApplicationContext(), "Your should enter only alphabet characters in name and address only numbers in phone.",
                           Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
                   toast.show();

               }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                finish();
                Intent myIntent=new Intent(AddCustomer.this,MainActivity.class);
                AddCustomer.this.startActivity(myIntent);

            }
        });
    }
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}
