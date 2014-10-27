package com.example.personalbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ExpenseLogDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    Context context;
    private String[] allColumns =
            {MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_HEAD,
                    MySQLiteHelper.COLUMN_NOTE, MySQLiteHelper.COLUMN_DATE};

    public ExpenseLogDataSource(Context context){
        this.context = context;
        dbHelper = new MySQLiteHelper(context);
    }

    public void open () throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }
    //return the id for insert data.
    public long insertExpense(ExpenseLogEntryData data){
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_HEAD, data.getHeading());
        values.put(MySQLiteHelper.COLUMN_NOTE, data.getExpense());
        values.put(MySQLiteHelper.COLUMN_DATE, data.getDate().toString());
        long id = database.insert(MySQLiteHelper.DATABASE_TABLE,null, values);
        return id;
    }

    public boolean deleteExpense(long id){
        return database.delete(MySQLiteHelper.DATABASE_TABLE,MySQLiteHelper.COLUMN_ID
                + "=" + id, null) > 0;
    }

    public Cursor getAllExpense (){
        return database.query(MySQLiteHelper.DATABASE_TABLE,
                allColumns,
                null, null, null, null, null);

    }

    public Cursor getExpense(Long id) throws SQLException{
        Cursor myCursor;
        myCursor = database.query(MySQLiteHelper.DATABASE_TABLE,
                allColumns, MySQLiteHelper.COLUMN_ID + "=" + id,
                null, null, null, null);
        if (myCursor != null){
            myCursor.moveToFirst();
        }
        return myCursor;
    }
}

