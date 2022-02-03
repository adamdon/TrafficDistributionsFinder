package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Application;

import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;

    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("start up");


    }

    public UiController getUiController()
    {
        return uiController;
    }

    public String getTestString()
    {
        String testString = "hope this worked";

        return testString;
    }
}