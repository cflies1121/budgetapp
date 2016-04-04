package com.example.main;

import javax.print.attribute.standard.MediaSize;
import javax.swing.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by codysmac on 4/1/16.
 *
 * Controls the program methods and instances.
 */
public class Control {
    int balance;

    public Control(){


    }


    public String calendarToString(Calendar cal){
        String date;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        date = formatter.format(cal.getTime());
        return date;
    }

    public void payBill(int amount){
        balance -= amount;
        System.out.println("new balance is " + balance);
    }

    public void getPaid(int amount){
        balance += amount;
        System.out.println("new balance is " + balance);
    }

    /**
     * just a test method:::>> to be deleted in future
     */
    public void test(){
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2016, Calendar.APRIL,  1);
        //otherIncomePopUp("hello", 25, expectedDate);
    }

    /**
     * takes the date given as a formatted string and converts it to a calendar date
     * @param date the date given as a string in the format of dd.MM.yyyy, maybe create a checker method to
     *             allow for other formats to be entered in the future.
     * @return
     */
    public Calendar stringToCalendar(String date){
        String dateStr = date;
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Calendar cal  = Calendar.getInstance();
        try {
            cal.setTime(df.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("error in string to calendar formatting");
        }
        return cal;
    }

    /**
     * writes the data to a file for future use based on type
     * @param type the type of input it is to determine which file it should go into
     * @param data the data that has been joined together in the inputToJoin() method.
     */
    private void writeToFile(String type, String data){
        String filename = (type + ".txt");
        try {
            File f = new File (filename);
            PrintWriter out = null;
            if(f.exists() && !f.isDirectory()){
                out = new PrintWriter(new FileOutputStream(new File(filename), true));
                out.append(data).append("\n");
                out.close();
            }
            else{
                out = new PrintWriter(filename);
                out.println(data);
                out.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("error in write to File method");
        }
    }

    /**
     * returns data that was saved to the file and returns it in an array to be put to use later
     * @param type the type of file to be opened ie otherIncome, reccuringBill, vetIncome, ect...
     * @param name the name of the item that needs to be found in the method
     * @return the data in a string [0] is the name, [1] is the amount, [2] is the date as a calendar object, [3]
     * is the type.
     */
    public String[] readFromFile(String type, String name){
        String joined = "", filename = (type + ".txt"), unjoined[] = new String[3];//init all the variables needed
        File f = new File(filename);
        try {
            Scanner sc = new Scanner(new File(filename));
            if(f.exists() && !f.isDirectory()) {
                while (sc.hasNext()) {
                    joined = sc.nextLine();
                    unjoined = joined.split("/");
                    if(name.equals(unjoined[0])){
                        break;
                    }
                }
            }
            else{
                System.out.println("file does not exist or is a directory");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("error in read from file method");
        }

        return unjoined;
    }

    /**
     * checks the date given and checks it against todays date
     * @param expectedDate a date to be checked against today
     * @return true if the date given is today, false if not.
     */
    public boolean ifExpectedDate(Calendar expectedDate){
        //ZoneId zoneId = ZoneId.of("America/Los_Angeles");
        //LocalDate today = LocalDate.now(zoneId);
        Calendar today = Calendar.getInstance();
        int dayOfYear = today.get(Calendar.DAY_OF_YEAR);
        int edayOfYear = expectedDate.get(Calendar.DAY_OF_YEAR);
        System.out.println(dayOfYear + " todays day of year");
        System.out.println(edayOfYear + " expected day of year");

        if(dayOfYear == edayOfYear){
            return true;
        }
        else {
            return false;
        }
    }
    /**
     * prints out the information contained inside a file by the name given
     */
    public String printOutArray(String type, String name){
        String print = "";
        for(int i = 0; i<3; i++) {
            if(i==0){
                print +=("name: ");
            }
            if(i==1){
                print+=(" amount: ");
            }
            if(i==2){
                print +=(" date due/expected: ");
            }
            print += (readFromFile(type, name)[i])+ "";


        }
        return (print);

    }
    /**
     * converts a string object into a boolean
     * @param string the string to be converted
     * @return if not yes/no true/false will return error message
     *
     */
    public Boolean stringToBool(String string){
        string = string.toLowerCase();
        if(string.equals("yes")||string.equals("true")){
            return true;
        }
        else if(!string.equals("no")||!string.equals("false")){
            System.out.println("error in string to bool method bad input");
        }
        return false;
    }

    /**
     * joins the data values together in order to prep the data to write to file.
     * @param name name of the data being entered
     * @param amount amount in dollars attached to the data
     * @param date expected date of bill or payment attached to the data
     * @param type type of data
     */
    public void inputToJoin(String name, int amount, Calendar date, String type){
        String join;
        join = (name + "/" + amount + "/" + calendarToString(date) + "/" + type);
        writeToFile(type, join);
    }
    private Calendar setToDate(Calendar cal){
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

/*    public String getTodaysDate(){
        String date;
        Calendar c = Calendar.getInstance();
        c = setToDate(c);
        date = c.getTime().toString();
        return date;
    }*/



}
