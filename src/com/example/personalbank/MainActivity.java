package com.example.personalbank;
/*This is a personal bank. It is used to record daily spend .
 * Run the application. Then using menu add function to add entry. Using SQLite to store data.*/
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.*;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class MainActivity extends ListActivity {
	ListView listView;

	String newHead;
	String newNote;
	ExpenseLogDataSource dataSource;
	SimpleCursorAdapter adapter;
	Button btnDelete;
	Cursor mCursor;
	
    @SuppressLint("InlinedApi")
	@SuppressWarnings("deprecation")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = getListView();
        
        
        dataSource = new ExpenseLogDataSource(this);
        dataSource.open();
        
        mCursor = dataSource.getAllExpense();
        startManagingCursor(mCursor);
        adapter = new SimpleCursorAdapter(
		this, 
		R.layout.expense_entry,
		mCursor,
		new String[]{MySQLiteHelper.COLUMN_HEAD,MySQLiteHelper.COLUMN_NOTE,MySQLiteHelper.COLUMN_DATE},
		new int[]{R.id.heading,R.id.expense,R.id.date},
		CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);  
       

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener(){
        	 public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,   
                     long arg3) {   
                
                 Toast.makeText(getApplicationContext(),"Your consumption record on "+ mCursor.getString(3),    
                         Toast.LENGTH_SHORT).show();   
             } 
        });

    }
    
    public void onClickDelete(View v){
    	View parent = (View)v.getParent();//expense_entry.xml

    	final int position = listView.getPositionForView(parent);
    	
    	Builder bd = new AlertDialog.Builder(this);
    	bd.setTitle("Delete Log");
    	bd.setMessage("Are you sure to delete this expense log?");
    	bd.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				Cursor n = (Cursor)adapter.getItem(position);
				long id = (long)n.getInt(0);

				dataSource.deleteExpense(id);
				mCursor = dataSource.getAllExpense();
				adapter.swapCursor(mCursor);
				adapter.notifyDataSetChanged();
			}
		});
    	bd.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		});
    	bd.show();
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_add:
	        	startActivityForResult(new Intent("com.example.personalbank.AddExpense"),1);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	if (requestCode == 1){
    		if(resultCode == RESULT_OK){
    			Bundle extras = data.getExtras();
    			newHead = extras.getString("com.example.personalbank.heading");
    			newNote = extras.getString("com.example.personalbank.note");
    			ExpenseLogEntryData new_d = new ExpenseLogEntryData(newHead, newNote);
    			dataSource.insertExpense(new_d);
    			mCursor = dataSource.getAllExpense();
    			adapter.swapCursor(mCursor);
    			adapter.notifyDataSetChanged();
    		}
    	}
    }
    @Override
    protected void onResume() {
      dataSource.open();
      super.onResume();
    }



}

