package com.example.personalbank;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.*;

public class MySQLiteHelper extends SQLiteOpenHelper{
	public static final String COLUMN_HEAD ="title";
	public static final String COLUMN_NOTE ="note";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DATE = "date";
	public static final String DATABASE_TABLE = "expenselog";
	
	private static final String DATABASE_NAME = "expense.db";
	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_CREATE = "create table "
			+ DATABASE_TABLE + " (" 
			+ COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_HEAD + " text not null, " 
			+ COLUMN_NOTE + " text not null, "
			+ COLUMN_DATE + " text not null);";
	
	
	public MySQLiteHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public MySQLiteHelper(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(MySQLiteHelper.class.getName(),"Upgrading database from version " 
				+ oldVersion + " to " + newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
		onCreate(db);
	}
	
}
