package com.example.user.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "c.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Customer";
    public static final String TABLE_NAME2 = "Product";
    public static final String TABLE_NAME3 = "Payment";
    public static final String TABLE_NAME4 = "demand";
    public static final String TABLE_NAME5 = "OrderDetails";
    // for customer
    public static final String COLUMN_ID = "CustomerID";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_Add = "Address";
    public static final String COLUMN_Phone = "Phone";
    public static final String voidInd = "voidInd";
    //
    //for product
    public static final String productID = "ProductID";
    public static final String StandardPrice = "StandardPrice";
    public static final String Quantity = "Quantity";
    public static final String Unit = "Unit";
    //public static final String pvoidInd = "pvoidInd";
    public static final String productName = "productName";
    //
    //for payment
    public static final String PaymentID = "PaymentID";
    public static final String PaymentDate = "PaymentDate";
    public static final String Amount = "Amount";
    public static final String CustomerID = "CustomerID";
    public static final String ptvoidInd = "ptvoidInd";
    //
    //for order
    public static final String OrderID = "OrderID";
    public static final String OrderDate = "OrderDate";
    public static final String OrderDueDate = "OrderDueDate";
    public static final String OCustomerID = "OCustomerID";
    public static final String OvoidInd = "OvoidInd";
    //
    // for order details
    public static final String OrderDetalID = "OrderDetalID";
    public static final String odOrderID = "odOrderID";
    public static final String od_productID = "odproductID";
    public static final String FinalPrice = "FinalPrice";
    public static final String odQuantity = "odQuantity";
    public static final String odvoidInd = "odvoidInd";

    private static final String TableOrder = "CREATE TABLE "
            + TABLE_NAME4 + "(" + OrderID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + OrderDate + " TEXT, "
            + OrderDueDate + " TEXT, "
            + OCustomerID + " INTEGER )";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " + COLUMN_Add + " TEXT ," + COLUMN_Phone + " TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_NAME2 + "(" + productID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + productName + " VARCHAR," + StandardPrice + " REAL, " + Unit + " TEXT )");
        db.execSQL("CREATE TABLE " + TABLE_NAME3 + "(" + PaymentID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +PaymentDate+ " text, "+Amount+" REAL, " +
        ""+CustomerID+" INTEGER )");
        //db.execSQL("CREATE TABLE" + TABLE_NAME4 + "(" + OrderID + " INTEGER PRIMARY KEY AUTOINCREMENT," + OrderDate + " TEXT," + OrderDueDate + " TEXT,"+OCustomerID + " INTEGER)");
      // db.execSQL("CREATE TABLE " + TABLE_NAME4 + "(" + OrderID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
               //OrderDate + " TEXT, " + OrderDueDate + " TEXT, "+ OCustomerID +" INTERGER)");
        db.execSQL(TableOrder);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME4);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME5);

        onCreate(db);
    }


    public void insertCustomer(Customer c) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, c.getName());
        contentValues.put(COLUMN_Add, c.getAddress());
        contentValues.put(COLUMN_Phone, c.getPhone());
        //contentValues.put(voidInd,c.getVoidInd());
        db.insert(TABLE_NAME, null, contentValues);
        db.close();

    }

    public Cursor getAllCustomer() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }

    //delete all customers without using voidInd
    public Integer deleteCustomer(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});

    }

    public void insertOrder(Order o) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderDate, o.getOrderDate());
        values.put(OrderDueDate, o.getOrederDueDate());
        values.put(OCustomerID, o.getID());
        db.insert(TABLE_NAME4, null, values);
        db.close();

    }

    public Cursor getOrder(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
       Cursor res = db.query(TABLE_NAME4, new String[]{OrderDate,
                       OrderDueDate},OCustomerID + "=?",
        new String[]{String.valueOf(id)}, null, null, null, null);

        return res;
    }
    public Cursor getPayment(int id ) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.query(TABLE_NAME3, new String[]{PaymentDate,
                        Amount},CustomerID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        return res;
    }

    public void Add_Product(Product objProduct) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(productName, objProduct.getName());
        contentValues.put(StandardPrice, objProduct.getStandard_price());
        contentValues.put(Unit, objProduct.getUnit());
        db.insert(TABLE_NAME2, null, contentValues);
        db.close();
    }



    public void insertPayment(Payment p) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PaymentDate,p.getPaymentDate());
        contentValues.put(Amount,p.getAmount());
        contentValues.put(CustomerID,p.getPt_CustomerID());
        db.insert(TABLE_NAME3, null, contentValues);
        db.close();

    }

    public List<String> getAllLabels(){
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public void deleteCustomer(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME+ " WHERE "+COLUMN_ID+"='"+id+"'");
        db.close();
    }
    public void deletePayment(int id,String m)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME3+ " WHERE "+CustomerID+"='"+id+"' and "+PaymentDate+"='"+m+"'");
        db.close();
    }
    public void deleteOrder(int id,String m)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME4+ " WHERE "+OCustomerID+"='"+id+"' and "+OrderDate+"='"+m+"'");
        db.close();
    }


    public List<String> getPaymentLabels(int id){
        List<String> labels = new ArrayList<>();



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME3, new String[]{PaymentDate,
                        Amount},CustomerID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0)+","+cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public List<String> getOrderLabels(int id){
        List<String> labels = new ArrayList<>();



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME4, new String[]{OrderDate,
                        OrderDueDate},OCustomerID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0)+","+cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public List<String> getOrderLabelswithId(int id){
        List<String> labels = new ArrayList<>();



        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME4, new String[]{OrderID,OrderDate,
                        OrderDueDate},OCustomerID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(0)+","+cursor.getString(1)+","+cursor.getString(2));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public void updateOrder(int id,String orderdate,String orderDueDate,int orderid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put(OrderDate,orderdate );
       contentValues.put(OrderDueDate,orderDueDate);
       db.update(TABLE_NAME4, contentValues,OCustomerID + " = ? and "+OrderID +" = ? ", new String[] {Integer.toString(id),Integer.toString(orderid)});
       // db.execSQL("update " + TABLE_NAME4+" set "+OrderDate+"='"+orderdate+"' and "+OrderDueDate+"='"+orderDueDate+"' WHERE "+OCustomerID+"='"+id+"' and "+OrderID+"='"+orderid+"'");
    }

}
