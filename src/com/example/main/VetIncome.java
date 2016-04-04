package com.example.main;

import java.util.concurrent.TimeUnit;
import java.util.Calendar;


/**
 * Created by codysmac on 3/31/16.
 */
public class VetIncome {
    int startYear, startMonth, startDay, endYear, endMonth, endDay;
    int amount;
    String startDate, endDate;
    private int numberOfMonths, numberOfDaysLeftInLastMonth, numberOfDaysLeftInFirstMonth;

    public VetIncome(int amount, int startYear, int startMonth, int startDay, int endYear,
                     int endMonth, int endDay){
        this.amount = amount;
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay + 1;
        calculateDays();
        System.out.println(numberOfDaysLeftInFirstMonth + " days");
        System.out.println(numberOfDaysLeftInLastMonth + " days");
        System.out.println(lastMonthPay() + "$ is last month pay" + firstMonthPay() + "$ is first month pay");
        System.out.println("total amount would be $ " + getTotalAmount() + " for " + numberOfMonths + " months " + (numberOfDaysLeftInFirstMonth+numberOfDaysLeftInLastMonth) + " days" );
    }


    public int getTotalAmount(){
        return lastMonthPay() + firstMonthPay() + (amount * numberOfMonths);
    }
    public int lastMonthPay(){
        return (int) Math.round(((amount / 30.0) * numberOfDaysLeftInLastMonth));
    }

    public int firstMonthPay(){
        return (int) Math.round(((amount / 30.0) * numberOfDaysLeftInFirstMonth));
    }
    public void calculateDays(){
        Calendar endCal = Calendar.getInstance();
        endCal.set(endYear, endMonth, endDay);//sets the calendar to the end date
        Calendar startCal = Calendar.getInstance();
        startCal.set(startYear, startMonth, startDay);//sets the calendar to the start date
        numberOfMonths = (int) (calendarDaysBetween(startCal, endCal)/30.0);//returns the number of months between
        numberOfDaysLeftInLastMonth = (int) daysLeftInLastMonth(startCal, endCal);
        numberOfDaysLeftInFirstMonth = (int) daysLeftInFirstMonth(startCal, endCal);
        if(numberOfDaysLeftInFirstMonth <=1 || numberOfDaysLeftInFirstMonth >=30){
            numberOfDaysLeftInFirstMonth = 0;
        }
        if(numberOfDaysLeftInLastMonth <=1 || numberOfDaysLeftInLastMonth >=30){
            numberOfDaysLeftInLastMonth = 0;
        }

    }

    public long daysLeftInLastMonth(Calendar startCal, Calendar endCal){

        // Create copies so we don't update the original calendars.
        Calendar start = Calendar.getInstance();
        start.setTimeZone(startCal.getTimeZone());
        start.setTimeInMillis(startCal.getTimeInMillis());

        Calendar end = Calendar.getInstance();
        end.setTimeZone(endCal.getTimeZone());
        end.setTimeInMillis(endCal.getTimeInMillis());

        // Set the copies to be at midnight, but keep the day information.
        // and set the start month to be the same as the end month.
        start.set(Calendar.YEAR, end.get(Calendar.YEAR));
        start.set(Calendar.MONTH, end.get(Calendar.MONTH));
        System.out.println(start.get(Calendar.YEAR) + " year in days last month ");
        System.out.println(start.get(Calendar.MONTH) + " month in days last mont ");
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);


        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        // At this point, each calendar is set to midnight on
        // their respective days and the star month is set to the end month. Now use TimeUnit.MILLISECONDS to
        // compute the number of full days between the two of them.
        return TimeUnit.MILLISECONDS.toDays(
                Math.abs(end.getTimeInMillis() - start.getTimeInMillis()));
    }

    public long daysLeftInFirstMonth(Calendar startCal, Calendar endCal){

        // Create copies so we don't update the original calendars.
        Calendar start = Calendar.getInstance();
        start.setTimeZone(startCal.getTimeZone());
        start.setTimeInMillis(startCal.getTimeInMillis());

        Calendar end = Calendar.getInstance();
        end.setTimeZone(endCal.getTimeZone());
        end.setTimeInMillis(endCal.getTimeInMillis());

        // Set the copies to be at midnight, but keep the day information.
        // and set the start month to be the same as the end month.
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        end.set(Calendar.YEAR, startCal.get(Calendar.YEAR));
        end.set(Calendar.MONTH, startCal.get(Calendar.MONTH)+1);

        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);
        ////////////////////////////////////////////////
        //// dont need this stuff right now just don't want to delete it////////
        //////////////////
        ////this has to be done manually like so because using getActualMaximum is not working.

        /*if (startCal.get(Calendar.MONTH) == Calendar.JANUARY){
            end.set(Calendar.DATE, 32);
        }
        else if(startCal.get(Calendar.MONTH) == Calendar.FEBRUARY){
            end.set(Calendar.DATE, 29);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.MARCH){
            end.set(Calendar.DATE, 33);
        }
        else if(startCal.get(Calendar.MONTH) == Calendar.APRIL){
            end.set(Calendar.DATE, 33);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.MAY){
            end.set(Calendar.DATE, 33);
        }
        else if(startCal.get(Calendar.MONTH) == Calendar.JUNE){
            end.set(Calendar.DATE, 29);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.JULY){
            end.set(Calendar.DATE, 33);
        }
        else if(startCal.get(Calendar.MONTH) == Calendar.AUGUST){
            end.set(Calendar.DATE, 33);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.SEPTEMBER){
            end.set(Calendar.DATE, 33);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.OCTOBER){
            end.set(Calendar.DATE, 33);
        }
        else if(startCal.get(Calendar.MONTH) == Calendar.NOVEMBER){
            end.set(Calendar.DATE, 33);
        }
        else if (startCal.get(Calendar.MONTH) == Calendar.DECEMBER){
            end.set(Calendar.DATE, 33);
        }*/


        // At this point, each calendar is set to midnight on
        // their respective days and the star month is set to the end month. Now use TimeUnit.MILLISECONDS to
        // compute the number of full days between the two of them.
        return (TimeUnit.MILLISECONDS.toDays(
                Math.abs(end.getTimeInMillis() - start.getTimeInMillis()))-1);
    }


    /**
     * Compute the number of calendar days between two Calendar objects.
     * The desired value is the number of days of the month between the
     * two Calendars, not the number of milliseconds' worth of days.
     * @param startCal The earlier calendar
     * @param endCal The later calendar
     * @return the number of calendar days of the month between startCal and endCal
     */
    public long calendarDaysBetween(Calendar startCal, Calendar endCal) {

        // Create copies so we don't update the original calendars.

        Calendar start = Calendar.getInstance();
        start.setTimeZone(startCal.getTimeZone());
        start.setTimeInMillis(startCal.getTimeInMillis());

        Calendar end = Calendar.getInstance();
        end.setTimeZone(endCal.getTimeZone());
        end.setTimeInMillis(endCal.getTimeInMillis());

        // Set the copies to be at midnight, but keep the day information.

        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);

        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        // At this point, each calendar is set to midnight on
        // their respective days. Now use TimeUnit.MILLISECONDS to
        // compute the number of full days between the two of them.

        return TimeUnit.MILLISECONDS.toDays(
                Math.abs(end.getTimeInMillis() - start.getTimeInMillis()));
    }

}
