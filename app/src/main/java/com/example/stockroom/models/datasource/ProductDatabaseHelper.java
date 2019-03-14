package com.example.stockroom.models.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import static com.example.stockroom.models.datasource.ProductDatabaseContract.COLUMN_COUNT;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.COLUMN_DESCRIPTION;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.COLUMN_ID;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.COLUMN_NAME;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.DATABASE_NAME;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.DATABASE_VERSION;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.TABLE_NAME;
import static com.example.stockroom.models.datasource.ProductDatabaseContract.getWhereClauseById;

public class ProductDatabaseHelper extends SQLiteOpenHelper {

    public ProductDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ProductDatabaseContract.createQuery());
        //initialProducts();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);

    }

    //Insert a product into the database
    public long insertAnimalIntoDatabase(@NonNull Product product) {
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        //Data container used for database key value pairs
        ContentValues contentValues = new ContentValues();

        //inset key value pairs into the contentValues container
        contentValues.put(COLUMN_ID, product.getId());
        contentValues.put(COLUMN_NAME, product.getName());
        contentValues.put(COLUMN_COUNT, product.getInventoryCount());
        contentValues.put(COLUMN_DESCRIPTION, product.getDescription());

        //insert the Animal into the table using contentValues
        return writableDatabase.insert(TABLE_NAME, null, contentValues);
    }

    //Get All products from Database and return an ArrayList
    public ArrayList<Product> getAllProductsFromDatabase() {
        ArrayList<Product> returnProductsList = new ArrayList<>();
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Get results from query and hold in cursor(iterable object for database operations
        Cursor cursor = readableDatabase.rawQuery(ProductDatabaseContract.getAllProductsQuery(), null);
        //Check to see if we have any results
        if(cursor.moveToFirst()) {
            //while we have results, get the values and place in list
            do {
                //get values
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String count= cursor.getString(cursor.getColumnIndex(COLUMN_COUNT));
                String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));



                //add to list
                returnProductsList.add(new Product(id, name, count, description));
            } while (cursor.moveToNext());
            //return the result in a list
        }
        cursor.close();
        return returnProductsList;
    }

    //Get One entry from database
    public Product getProductById(int id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        //Animal to return
        Product returnProduct = new Product();
        //cursor to hold results
        Cursor cursor = readableDatabase.rawQuery(ProductDatabaseContract.getOneProductById(id), null);
        if(cursor.moveToFirst()){
            int idFromDB = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            String count = cursor.getString(cursor.getColumnIndex(COLUMN_COUNT));
            String description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));

            //set return Animal
            returnProduct = new Product(idFromDB,name,count,description);
        }
        cursor.close();
        return returnProduct;
    }

    //delete entry(ies) from the database
    public  long deleteFromDatabaseById(String[] id){
        SQLiteDatabase sqliteDatabas = this.getWritableDatabase();

        return sqliteDatabas.delete(TABLE_NAME,getWhereClauseById()+id[0], null);
    }
}
