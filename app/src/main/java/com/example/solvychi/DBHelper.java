package com.example.solvychi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "SignUp.db";
    public DBHelper(Context context)
    {
        super(context, "SignUp.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (email TEXT primary key ,username TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");

    }
    public Boolean insertData(String username, String email, String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1) return false;
        else  return true;

    }
//    public Boolean checkusername(String username)
//    {
//        SQLiteDatabase MyDB = this.getWritableDatabase();
//        Cursor cursor = MyDB.rawQuery("Select * from users where username =?",new String[] {username});
//        if(cursor.getCount()>0)
//        return true;
//        else return false;
//    }
    public Boolean checkemail(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email =?",new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public Boolean checkemailpassword(String email,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email =? and password = ?",new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public String checkpassword(String email)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select password from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    // below is the method for updating our courses
    public void updateCourse(String origemail,String username, String email, String password
                           ) {

        // calling a method to get writable database.
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("username", username);
        values.put("email",email);
        values.put("password", password);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        MyDB.update("users", values, "email=?", new String[]{origemail});
        MyDB.close();
    }



}
