package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;


    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("start up");
        uiController = new UiController(this);

    }

    public MainActivity getCurrentActivity()
    {
        return uiController.getCurrentActivity();
    }

    public UiController getUiController()
    {
        return uiController;
    }





}