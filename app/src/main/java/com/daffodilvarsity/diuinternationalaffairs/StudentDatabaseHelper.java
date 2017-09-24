package com.daffodilvarsity.diuinternationalaffairs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class StudentDatabaseHelper extends SQLiteOpenHelper {

    public static final String DatabaseName = "DiuForeignAffairs.DB";
    public static final int DatabaseVirsion = 1;
    public static final String DB_Table_Create_Query=
   "CREATE TABLE "+ UserContract.Table_Name_StudentInfo+"("+ UserContract.User_Full_Name +" TEXT,"
            + UserContract.User_ID +" TEXT NOT NULL PRIMARY KEY,"
            + UserContract.Semester +" Integer,"
           + UserContract.Department +" TEXT,"
           + UserContract.Designation +" TEXT,"
            + UserContract.User_Diu_Email +" TEXT,"
            + UserContract.User_Phone +" TEXT,"
            + UserContract.User_Type+" TEXT);";

    public StudentDatabaseHelper(Context context){
        super(context, DatabaseName, null, DatabaseVirsion);
        Log.e("Database Message: ", " Database created / Opened..");

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DB_Table_Create_Query);
        Log.e("Database Message: ", " StudentInfo Table created..");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UserContract.Table_Name_StudentInfo + "");
        onCreate(db);
    }
    public boolean InsertStudentInfo(String Fullname, String ID, int Semester,String Department,String Email, String Phone,String Type, SQLiteDatabase db){
        db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserContract.User_Full_Name,Fullname);
        contentValues.put(UserContract.User_ID,ID);
        contentValues.put(UserContract.Semester,Semester);
        contentValues.put(UserContract.Department,Department);
        contentValues.put(UserContract.User_Diu_Email,Email);
        contentValues.put(UserContract.User_Phone,Phone);
        contentValues.put(UserContract.User_Type,Type);
       long checker= db.insert(UserContract.Table_Name_StudentInfo, null, contentValues);
       if (checker==-1){
           return false;
       }
        else{
           return true;
       }
    }
    public boolean InsertStudentInfo(String Fullname, String ID,String Department,String Designation,String Email, String Phone,String Type, SQLiteDatabase db){
        db=getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UserContract.User_Full_Name,Fullname);
        contentValues.put(UserContract.User_ID,ID);
        contentValues.put(UserContract.Department,Department);
        contentValues.put(UserContract.Designation,Designation);
        contentValues.put(UserContract.User_Diu_Email,Email);
        contentValues.put(UserContract.User_Phone,Phone);
        contentValues.put(UserContract.User_Type,Type);
        long checker= db.insert(UserContract.Table_Name_StudentInfo, null, contentValues);
        if (checker==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public Cursor retriveData(){

        SQLiteDatabase db;
        db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ UserContract.Table_Name_StudentInfo+"", null);
        return cursor;
    }
    public Cursor retriveUserInformation(String ID){
        SQLiteDatabase db;
        db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+ UserContract.Table_Name_StudentInfo+" where "+ UserContract.User_ID +"='"+ID+"'", null);
        return cursor;
    }

    public void UpdateStudentInfo(String ID, int Semester,String Email, String Phone, SQLiteDatabase db){

        db=getWritableDatabase();
        String query="update "+ UserContract.Table_Name_StudentInfo+" set "+ UserContract.Semester +"='"+Semester+
                "', "+ UserContract.User_ID +"='"+ID+
                "', "+ UserContract.User_Diu_Email +"='"+Email+
                "', "+ UserContract.User_Phone +"='"+Phone+"' WHERE "+ UserContract.User_ID +"='"+ID+"'";
        db.execSQL(query);


    }
    public void UpdateStudentInfo(String ID, String Department,String Email, String Phone, SQLiteDatabase db){

        db=getWritableDatabase();
        String query="update "+ UserContract.Table_Name_StudentInfo+" set "+ UserContract.Department +"='"+Department+
                "', "+ UserContract.User_ID +"='"+ID+
                "', "+ UserContract.User_Diu_Email +"='"+Email+
                "', "+ UserContract.User_Phone +"='"+Phone+"' WHERE "+ UserContract.User_ID +"='"+ID+"'";
        db.execSQL(query);


    }


}
