package uk.co.adamdon.trafficdistributionsfinder.utilities;

import java.util.Calendar;
import java.util.Date;

public class DateHelper
{
    private static DateHelper INSTANCE;

    private DateHelper()
    {
    }

    public static DateHelper getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DateHelper();
        }
        return INSTANCE;
    }


    public int getYearInt(Date date)
    {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }


    public int getMonthInt(Date date)
    {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.setTime(date);

        return (calendar.get(Calendar.MONTH) + 0);
    }


    public int getDayInt(Date date)
    {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    public Date getDate(int yearInt, int monthOfYearInt, int dayOfMonthInt)
    {
        Calendar calendar;

        calendar = Calendar.getInstance();
        calendar.set(yearInt, monthOfYearInt, dayOfMonthInt);

        return calendar.getTime();
    }





}
