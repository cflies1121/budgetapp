package com.example.main;

import javax.swing.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by codysmac on 3/31/16.
 */
public class OtherIncome extends Control{
    private String name, type, recurring;
    private int amount;
    private Calendar expectedDate;
    private boolean isRecurring;



    public OtherIncome (String name, float amount, Calendar expectedDate, String type, String recurring){
        this.name = name;
        this.amount = Math.round(amount);
        this.expectedDate = expectedDate;
        this.type = type;
        this.isRecurring = super.stringToBool(recurring);
        if(isRecurring){
            recurring();
        }
        //System.out.println(ifExpectedDate());
        otherIncomePopUp();
        super.inputToJoin(this.name, this.amount, this.expectedDate, this.type);

    }

    public OtherIncome(String name, String type){
        this.name = name;
        this.type = type;
        System.out.println(super.printOutArray(this.type, this.name));
        this.expectedDate = super.stringToCalendar(super.readFromFile(this.type, getName())[2]);
        this.amount = Integer.parseInt(super.readFromFile(this.type, getName())[1]);
        otherIncomePopUp();


    }

    public void recurring(){
        if (ifExpectedDate(expectedDate)){
            Calendar c = Calendar.getInstance();
            expectedDate.set(expectedDate.MONTH, (c.getTime().getMonth())+1);
            System.out.println(calendarToString(expectedDate));
        }
    }


    /**
     * if the expected date matches the current date then displays a pop up, may need modifications later
     */
    public void otherIncomePopUp(){
        if(super.ifExpectedDate(this.expectedDate)) {
            JOptionPane.showMessageDialog(null, "You should have $" + getAmount() + " from " + getName());
        }
    }



    public String getName (){
        return this.name;
    }
    public int getAmount(){
        return this.amount;
    }




}
