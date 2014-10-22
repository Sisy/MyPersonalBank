package com.example.personalbank;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


	public class ExpenseTrackerAdapter extends BaseAdapter{
		private ArrayList<ExpenseLogEntryData> arrayExpenseLog = new ArrayList<ExpenseLogEntryData>();
		private Context context;
		private TextView vHead;
		private TextView vExpense;
		private TextView vDate;
		
		ExpenseTrackerAdapter(){
			
		}
		
		ExpenseTrackerAdapter(Context context, ArrayList<ExpenseLogEntryData> array){
			this.context = context;
			this.arrayExpenseLog = array;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return arrayExpenseLog.size();
		}
		
		public Context getContext(){
			return context;
		}

		@Override
		public ExpenseLogEntryData getItem(int index) {
			// TODO Auto-generated method stub
			return arrayExpenseLog.get(index);
		}

		@Override
		public long getItemId(int index) {
			// TODO Auto-generated method stub
			return index;
		}

		@Override
		public View getView(int index, View convertView, ViewGroup parent) {
			ExpenseLogEntryData expenseData = getItem(index);
	        
			if (convertView == null){
				LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		        convertView = inflater.inflate(R.layout.expense_entry, null);    
			}
			vHead = (TextView) convertView.findViewById(R.id.heading);
	        vExpense = (TextView) convertView.findViewById(R.id.expense);
	        vDate = (TextView) convertView.findViewById(R.id.date);
	        
			vHead.setText(expenseData.getHeading());
			vExpense.setText(expenseData.getExpense());
			vDate.setText(expenseData.getDate().toString());

			return convertView;
		}
		
		public void add(ExpenseLogEntryData data){
			arrayExpenseLog.add(data);
			
		}
		
		public void remove(int index){
			arrayExpenseLog.remove(index);
		}
		
	}

