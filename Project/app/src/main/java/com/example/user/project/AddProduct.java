package com.example.user.project;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {


    DBHelper db;
    EditText txtProductName;
    EditText txtStandardPrice;
    EditText txtUnit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

         db = new DBHelper(AddProduct.this);
          txtProductName =(EditText) findViewById(R.id.ProductName);
          txtStandardPrice =(EditText) findViewById(R.id.ProductPrice);
          txtUnit =(EditText) findViewById(R.id.ProductUnit);


        Button btnAddNewProduct = (Button) findViewById(R.id.add);
        btnAddNewProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                String name = txtProductName.getText().toString();
                double standard_price = Double.parseDouble(txtStandardPrice.getText().toString());
                String unit = txtUnit.getText().toString();
                Product objProduct = new Product(name,standard_price,unit);
                db.Add_Product(objProduct);
                finish();
                //Start Product Main Activity
                Intent myIntent=new Intent(AddProduct.this,MainActivity.class);
                AddProduct.this.startActivity(myIntent);
            }
        });

        Button btnCancel = (Button) findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                finish();
                Intent myIntent=new Intent(AddProduct.this,MainActivity.class);
                AddProduct.this.startActivity(myIntent);
            }
        });


    }
}
