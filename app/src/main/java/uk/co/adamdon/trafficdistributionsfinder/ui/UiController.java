package uk.co.adamdon.trafficdistributionsfinder.ui;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;

public class UiController
{

    private App app;
    private MainActivity currentActivity;

    private ArrayList<FrameLayout> containersFrameLayoutsList;
    private ArrayList<String> containerFragmentStringList;




    public UiController(App app)
    {
        this.app = app;
        registerLifecycleCallbacks();
        containerFragmentStringList = new ArrayList<>();
    }



    public void replaceFragmentByID(int containerIndexInt, Fragment newFragment)
    {
        FragmentTransaction fragmentTransaction;
        FrameLayout containerFrameLayout;
        int containerIdInt;

        if(currentActivity != null)
        {
            fragmentTransaction  = currentActivity.getSupportFragmentManager().beginTransaction();
            containerFrameLayout = containersFrameLayoutsList.get( containerIndexInt );
            containerIdInt = containerFrameLayout.getId();

            fragmentTransaction.replace(containerIdInt, newFragment, newFragment.getClass().getSimpleName());
            fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );

            containerFragmentStringList.set(containerIndexInt, newFragment.getClass().getSimpleName());
            try
            {
                currentActivity.runOnUiThread(() -> fragmentTransaction.commitNow() );
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }

    }







    public LinearLayout createContainerizedLinearLayout(int containerCountInt)
    {
        containersFrameLayoutsList = setupContainerFrameLayoutsList(containerCountInt);

        LinearLayout returnLinearLayout = new LinearLayout(app.getApplicationContext());

        returnLinearLayout.setId(View.generateViewId());
        returnLinearLayout.setOrientation(LinearLayout.VERTICAL);

        for(FrameLayout currentFrameLayout : containersFrameLayoutsList)
        {
            returnLinearLayout.addView(currentFrameLayout);
        }


        return returnLinearLayout;
    }




    private ArrayList<FrameLayout> setupContainerFrameLayoutsList(int containerCountInt)
    {
        ArrayList<FrameLayout> returnFrameLayoutList = new ArrayList<FrameLayout>();
        FragmentTransaction fragmentTransaction = currentActivity.getSupportFragmentManager().beginTransaction();


        for (int indexInt = 0; indexInt < containerCountInt; indexInt++ )
        {
            FrameLayout currentFrameLayout = new FrameLayout(app.getApplicationContext());

            currentFrameLayout.setTag("FRAMELAYOUT_" + currentFrameLayout.toString());
            currentFrameLayout.setId( View.generateViewId());
            currentFrameLayout.setBackgroundColor( Color.TRANSPARENT);
            currentFrameLayout.setLayoutTransition( createCustomLayoutTransition() );
            currentFrameLayout.getLayoutTransition().enableTransitionType( LayoutTransition.CHANGING);

            returnFrameLayoutList.add(currentFrameLayout);
            containerFragmentStringList.add(BlankFragment.class.getSimpleName());

            BlankFragment currentBlankFragment = new BlankFragment(app);
            fragmentTransaction.add(currentFrameLayout.getId(), currentBlankFragment, ("FRAGMENT_" + currentBlankFragment.toString()));
        }

        fragmentTransaction.commit();


        return returnFrameLayoutList;
    }


    @SuppressLint("ObjectAnimatorBinding")
    private static LayoutTransition createCustomLayoutTransition()
    {
        LayoutTransition returnLayoutTransition;
        Animator scaleDownAnimator;
        Animator scaleUpAnimator;
        int durationMillisecondsInt;

        durationMillisecondsInt = 300;

        scaleDownAnimator = ObjectAnimator.ofPropertyValuesHolder(
                (Object)null,
                PropertyValuesHolder.ofFloat( "scaleX", 1, 0),
                PropertyValuesHolder.ofFloat("scaleY", 1, 0),
                PropertyValuesHolder.ofFloat("alpha", 1, 0)
        );
        scaleDownAnimator.setDuration(durationMillisecondsInt);
        scaleDownAnimator.setInterpolator(new OvershootInterpolator());


        scaleUpAnimator = ObjectAnimator.ofPropertyValuesHolder(
                (Object)null,
                PropertyValuesHolder.ofFloat( "scaleX", 0, 1),
                PropertyValuesHolder.ofFloat( "scaleY", 0, 1),
                PropertyValuesHolder.ofFloat("alpha", 0, 1)
        );
        scaleUpAnimator.setDuration(durationMillisecondsInt);
        scaleUpAnimator.setStartDelay(durationMillisecondsInt);
        scaleUpAnimator.setInterpolator(new OvershootInterpolator());


        returnLayoutTransition = new LayoutTransition();
        returnLayoutTransition.setAnimator(LayoutTransition.APPEARING, scaleUpAnimator);
        returnLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleDownAnimator);


        return returnLayoutTransition;
    }




    public String getFragmentTypeByContainerId(int containerIdInt)
    {
        return containerFragmentStringList.get(containerIdInt);
    }




    public void registerLifecycleCallbacks()
    {
        final Application.ActivityLifecycleCallbacks activityLifecycleCallbacks;

        activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks()
        {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceBundle)
            {
                currentActivity = (MainActivity)activity;
            }

            @Override
            public void onActivityResumed(Activity activity)
            {
                currentActivity = (MainActivity)activity;
            }

            @Override
            public void onActivityPaused(Activity activity)
            {
                currentActivity = null;
            }

            @Override public void onActivityStarted(Activity activity) { }
            @Override public void onActivityStopped(Activity activity) { }
            @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }
            @Override public void onActivityDestroyed(Activity activity) { }
        };

        app.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }




    //
    //// potential anti-pattern features
    //

//    public Fragment getFragmentFromContainer(int numberOfContainerInt)
//    {
//        final Fragment returnFragment;
//
//        returnFragment = currentActivity.getSupportFragmentManager().findFragmentById(containersFrameLayoutsList.get( numberOfContainerInt ).getId());
//
//        return returnFragment;
//    }
//
//
//
//    public ViewModel getViewModelFromFragmentClass(Class fragmentClass, Class viewModelClass)
//    {
//        ViewModel returnViewModel = null;
//
//        try
//        {
//            for (final FrameLayout currentFrameLayout : containersFrameLayoutsList)
//            {
//                final Fragment containerisedFragment;
//                containerisedFragment = currentActivity.getSupportFragmentManager().findFragmentById(currentFrameLayout.getId());
//
//                if (containerisedFragment.getClass() == fragmentClass)
//                {
//                    returnViewModel = new ViewModelProvider(containerisedFragment).get(viewModelClass);
//                }
//            }
//        }
//        catch (Exception exception)
//        {
//            exception.printStackTrace();
//        }
//
//        return returnViewModel;
//    }




}
