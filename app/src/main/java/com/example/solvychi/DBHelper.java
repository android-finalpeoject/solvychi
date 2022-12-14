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
        super(context, "SignUp.db", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(email TEXT primary key ,username TEXT, password TEXT,level TEXT,gender TEXT)");
//        MyDB.execSQL("alter Table users add level TEXT");


    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("alter Table  users add column gender TEXT");


    }
    public Boolean insertData(String username, String email, String password,String level,String gender)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("level", level);
        contentValues.put("gender", gender);

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
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email =?",new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public Boolean checkemailpassword(String email,String password)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where email =? and password = ?",new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else return false;
    }
    public String checkpassword(String email)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select password from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    public String fetchUser(String email)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select username from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    public String fetchLevel(String email)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select level from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    public String fetchGender(String email)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select gender from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    public String fetchPwd(String email)
    {
        SQLiteDatabase MyDB = this.getReadableDatabase();
        Cursor cursor = MyDB.rawQuery("Select password from users where email =? ",new String[] {email});
        cursor.moveToFirst();
        if(cursor.getCount()>0)
            return cursor.getString(0);
        else return null;
    }
    public boolean alterLevel(String email, String level)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("level", level);
       return MyDB.update("users",newValues,"email=?",new String[] {email})>0;


    }
    public void updateProfile(String origemail,String username, String password
    ) {

        // calling a method to get writable database.
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put("username", username);
        values.put("password", password);


        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our user which is stored in original name variable.
        MyDB.update("users", values, "email=?", new String[]{origemail});
        MyDB.close();
    }
}
