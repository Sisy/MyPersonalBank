package com.example.personalbank;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class AddExpense extends Activity {
    EditText title;
    EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_expense);

        title = (EditText) findViewById(R.id.titleinput);
        note = (EditText) findViewById(R.id.noteinput);
    }

    public void buttonSave(View view){
        Intent data = new Intent();
        data.putExtra("com.example.personalbank.heading",title.getText().toString());
        data.putExtra("com.example.personalbank.note", note.getText().toString());
        setResult(RESULT_OK,data);
        finish();
    }

    public void buttonCancel(View view){
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_expense, menu);
        return true;
    }



}