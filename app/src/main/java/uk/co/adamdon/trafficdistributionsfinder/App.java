package uk.co.adamdon.trafficdistributionsfinder;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import uk.co.adamdon.trafficdistributionsfinder.ui.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.UiController;


public class App extends Application
{
    private UiController uiController;
    private MainActivity       mCurrentActivity;


    @Override
    public void onCreate()
    {
        super.onCreate();
        System.out.println("start up");
        registerLifecycleCallbacks();
        uiController = new UiController(this);


    }

    public MainActivity getCurrentActivity()
    {
        return mCurrentActivity;
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


    public void registerLifecycleCallbacks()
    {
        final Application.ActivityLifecycleCallbacks lActivityLifecycleCallbacks;

        lActivityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity pActivity, Bundle pSavedInstanceBundle)
            {
                //log("ActivityLifecycle of onActivityCreated for" + pActivity.getClass().getSimpleName());
                mCurrentActivity = (MainActivity)pActivity;
            }

            @Override
            public void onActivityResumed(Activity activity)
            {
                //log("ActivityLifecycle of onActivityResumed for" + activity.getClass().getSimpleName());
                mCurrentActivity = (MainActivity)activity;
            }

            @Override
            public void onActivityPaused(Activity activity)
            {
                //log("ActivityLifecycle of onActivityPaused for" + activity.getClass().getSimpleName());
                mCurrentActivity = null;
            }

            @Override public void onActivityStarted(Activity activity) { }
            @Override public void onActivityStopped(Activity activity) { }
            @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }
            @Override public void onActivityDestroyed(Activity activity) { }
        };

        registerActivityLifecycleCallbacks(lActivityLifecycleCallbacks);
    }
}