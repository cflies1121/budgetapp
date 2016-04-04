package com.example.main;

import com.sun.xml.internal.xsom.impl.WildcardImpl;

import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by codysmac on 4/2/16.
 */
public class GUI extends Control {
    String otherIncomeName, type, rbname, choice, date, recurring;
    float otherIncomeAmount, rbamount;
    Calendar expectedDate, duedate;
    Scanner sc = new Scanner(System.in);

    public GUI() {


    }

    /**
     * method that will be used for now to ask the user for input, should be converted to
     * a gui interface in the future.
     */
    public void askUser() {

        System.out.println("please select from the following 1 enter new item, 2 review previous items, 3 pay a bill");
        choice = sc.nextLine();
        if (choice.equals("1")) {
            enterInfo();
        }
        else if(choice.equals("2")){
            reviewInfo();
        }
        else if(choice.equals("3")){
            payABill();
        }
    }
    private void payABill(){
        System.out.println("please select the bill to be paid");
        int amount;
        String typea = "otherIncome";
        String name = "hen"; /// testing. sc.nextLine();
        if(new Control().readFromFile(typea, name)[0].equals(name)){
            System.out.println(new Control().readFromFile(typea, name)[0] + " is due on " + new Control().readFromFile(typea, name)[2] + " and is $" + new Control().readFromFile(typea, name)[1]);
            System.out.println("how much would you like to pay?");
            amount = Math.round((float)sc.nextDouble());
            payBill(amount);
        }
        else{
            System.out.println("name not found please enter it now");
            enterInfo();
        }
        askUser();
    }

    private void enterInfo(){
        System.out.println("please type i for new income or rb for new recurring bill:");
        choice = "rb"; // testing sc.nextLine();
        if (choice.equals("i")) {
            System.out.println("please name your income");
            otherIncomeName = "he"; //testing ... sc.nextLine();
            System.out.println("please give amount");
            otherIncomeAmount = 25.24f; // testing .... sc.nextFloat();
            System.out.println("please enter the expected date dd.MM.yyyy format");
            date = "03.04.2016"; // testing .... sc.next();
            expectedDate = stringToCalendar(date);
            type = "otherIncome";
            System.out.println("is this a reoccurring income, that happens at the same time every month?");
            recurring = "yes";// testing .... sc.next();
            new OtherIncome(otherIncomeName, otherIncomeAmount, expectedDate, type, recurring);
            //inputToJoin(otherIncomeName, otherIncomeAmount, expectedDate, type);
            //System.out.println("the name is " + otherIncomeName + " the amount is " + otherIncomeAmount + " the expected date is today ");
        } else if (choice.equals("rb")) {
            System.out.println("please give your reccurring bill a name");
            rbname = "hen";// testing  .... sc.nextLine();
            System.out.println("what is the amount");
            rbamount = 25;// testing sc.nextInt();
            System.out.println("please enter the expected date dd.MM.yyyy format");
            date = "04.04.2016"; // testing ....sc.next();
            duedate = stringToCalendar(date);
            type = "recurringBill";


            inputToJoin(rbname, (int)rbamount, duedate, type);

        }
        askUser();

    }

    private void reviewInfo(){
        String name;
        System.out.println("What type of item would you like to review? 1 for income 2 for recuring bill");
        choice = "1"; // testing..... sc.nextLine();
        System.out.println("which item would you like to review; please enter the name search by name");
        name = "hella"; // testing.... sc.nextLine();
        if(choice.equals("1")){
            new OtherIncome(name, "otherIncome");

        }
        askUser();


    }
}