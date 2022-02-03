package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Application;



public class App extends Application
{

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("start up");


    }






    public String getTestString()
    {
        String testString = "hope this worked";

        return testString;
    }
}