package com.example.personalbank;

import java.util.Date;

public class ExpenseLogEntryData {
    private String head;
    private String expense;
    private Date date;

    ExpenseLogEntryData(){
        this(null, null);
    }

    ExpenseLogEntryData(String h,String e){
        head = h;
        expense = e;

        date = new Date(java.lang.System.currentTimeMillis());
    }
    public String getHeading(){
        return head;
    }

    public String getExpense(){
        return expense;
    }

    public Date getDate(){
        return date;
    }

    public void setHeading(String h){
        head = h;
    }

    public void setExpense(String ex){
        expense = ex;
    }




}

