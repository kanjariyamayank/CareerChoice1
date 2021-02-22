package com.example.careerchoice.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.careerchoice.models.CollegeModel;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;
import static java.security.AccessController.getContext;

/**
 * Created by MRK on 26-02-2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "bookWay";
    private static final String TABLE_CART = "cart";
    private static final String KEY_ID = "id";
    private static final String KEY_CATEGORY = "field_name";
    private static final String KEY_COLLEGE_NAME = "college_name";
    private static final String KEY_COLLEGE_CODE = "college_code";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_CONTECT = "contact";
    private static final String KEY_DEPARTMENT = "department";
    private static final String KEY_COLLEGE_DETAILS = "college_details";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART +
                "(" + KEY_ID + " TEXT PRIMARY KEY, " +
                KEY_CATEGORY + " TEXT, " +
                KEY_COLLEGE_NAME + " TEXT, " +
                KEY_COLLEGE_CODE + " TEXT, " +
                KEY_ADDRESS + " TEXT, " +
                KEY_CONTECT + " TEXT, " +
                KEY_IMAGE + " TEXT, " +
                KEY_DEPARTMENT + " TEXT, " +
                KEY_COLLEGE_DETAILS + " TEXT)";

        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }
    public void addToCart(CollegeModel bookModel) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, bookModel.getId());
        contentValues.put(KEY_CATEGORY, bookModel.getFieldname());
        contentValues.put(KEY_COLLEGE_NAME, bookModel.getCollege_name());
        contentValues.put(KEY_COLLEGE_CODE, bookModel.getCollege_code());
        contentValues.put(KEY_ADDRESS, bookModel.getAddress());
        contentValues.put(KEY_CONTECT, bookModel.getContact());
        contentValues.put(KEY_IMAGE, bookModel.getImage_college());
        contentValues.put(KEY_DEPARTMENT, bookModel.getDepartment());
        contentValues.put(KEY_COLLEGE_DETAILS, bookModel.getCollege_details());

        sqLiteDatabase.insert(TABLE_CART, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<CollegeModel> getCartItems() {

        String selectQuery = "SELECT * FROM " + TABLE_CART;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        List<CollegeModel> cartItems = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            {
                do {
                    CollegeModel collegeModel = new CollegeModel();
                    collegeModel.setId(cursor.getString(cursor.getColumnIndex(KEY_ID)));
                    /*collegeModel.setFieldname(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));*/
                    collegeModel.setCollege_name(cursor.getString(cursor.getColumnIndex(KEY_COLLEGE_NAME)));
                    collegeModel.setCollege_code(cursor.getString(cursor.getColumnIndex(KEY_COLLEGE_CODE)));
                    collegeModel.setAddress(cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
                    collegeModel.setContact(cursor.getString(cursor.getColumnIndex(KEY_CONTECT)));
                    collegeModel.setImage_college(cursor.getString(cursor.getColumnIndex(KEY_IMAGE)));
                    collegeModel.setDepartment(cursor.getString(cursor.getColumnIndex(KEY_DEPARTMENT)));
                    collegeModel.setCollege_details(cursor.getString(cursor.getColumnIndex(KEY_COLLEGE_DETAILS)));
                    cartItems.add(collegeModel);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return cartItems;
    }

    public void deleteCart() {
        String deleteQuery = "DELETE FROM " + TABLE_CART;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(deleteQuery);
    }

    public void removeItem(String id) {
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            sqLiteDatabase.delete(TABLE_CART, KEY_ID + " = ?", new String[]{id});
            sqLiteDatabase.close();
        }
    }

}