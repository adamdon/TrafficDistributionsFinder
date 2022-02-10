package uk.co.adamdon.trafficdistributionsfinder.ui;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.graphics.Color;
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
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;

public class UiController
{

    private App app;
    private ArrayList<FrameLayout> containersFrameLayoutsList;
    private Boolean isBackEnabledBoolean;
    private Fragment previousSlot1Fragment;
    private Fragment currentSlot1Fragment;

    private ExecutorService executorService;




    public UiController(App app)
    {
        this.app = app;
        isBackEnabledBoolean = true;
        previousSlot1Fragment = new BlankFragment(app);
        currentSlot1Fragment = new BlankFragment(app);

        executorService = Executors.newSingleThreadExecutor();
    }


    private Fragment getFragmentById(final int pIdInt)
    {
        final Fragment returnFragment;

        returnFragment = app.getCurrentActivity().getSupportFragmentManager().findFragmentById(pIdInt);

        return returnFragment;
    }



    public void replaceFragmentByID( int containerIndexInt, Fragment newFragment)
    {
        FragmentTransaction fragmentTransaction;
        FrameLayout containerFrameLayout;
        int containerIdInt;

        if(app.getCurrentActivity() != null)
        {
            fragmentTransaction  = app.getCurrentActivity().getSupportFragmentManager().beginTransaction();
            containerFrameLayout = containersFrameLayoutsList.get( containerIndexInt );
            containerIdInt = containerFrameLayout.getId();

            if(containerIndexInt == 1)
            {
                previousSlot1Fragment = getFragmentById(containerIdInt);
            }

            fragmentTransaction.replace(containerIdInt, newFragment, newFragment.getClass().getSimpleName());
            fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN );

            try
            {
                app.getCurrentActivity().runOnUiThread(() -> fragmentTransaction.commitNow() );
            }
            catch ( Exception e )
            {
//                log( "replaceNewFragmentByID fragmentSlot : " +  pContainerIndexInt + " to new " + pNewFragment.getClass().getSimpleName());
                e.printStackTrace();
            }
            if(containerIndexInt == 1)
            {
                currentSlot1Fragment = getFragmentById(containerIdInt);
            }

        }

    }


    public Fragment getFragmentFromContainer(int numberOfContainerInt)
    {
        final Fragment returnFragment;

        returnFragment = app.getCurrentActivity().getSupportFragmentManager().findFragmentById(containersFrameLayoutsList.get( numberOfContainerInt ).getId());

        return returnFragment;
    }



    public ViewModel getViewModelFromFragmentClass(Class fragmentClass, Class viewModelClass)
    {
        ViewModel lReturnViewModel = null;

        try
        {
            for (final FrameLayout lCurrentFrameLayout : containersFrameLayoutsList)
            {
                final Fragment lContainerisedFragment;
                lContainerisedFragment = app.getCurrentActivity().getSupportFragmentManager().findFragmentById(lCurrentFrameLayout.getId());

                if (lContainerisedFragment.getClass() == fragmentClass)
                {
                    lReturnViewModel = new ViewModelProvider(lContainerisedFragment).get(viewModelClass);
                }
            }
        } catch (Exception pE)
        {
//            log("Error " + pE.getMessage());
            pE.printStackTrace();
        }

        return lReturnViewModel;

    }



    public LinearLayout createContainerizedLinearLayout(int containerCountInt)
    {
        containersFrameLayoutsList = setupContainerFrameLayoutsList(containerCountInt);

        LinearLayout returnLinearLayout = new LinearLayout(app.getApplicationContext());

        returnLinearLayout.setId( View.generateViewId() );
        returnLinearLayout.setOrientation( LinearLayout.VERTICAL );

        for (final FrameLayout lCurrentFrameLayout : containersFrameLayoutsList)
        {
            returnLinearLayout.addView(lCurrentFrameLayout);
        }


        return returnLinearLayout;
    }




    private ArrayList<FrameLayout> setupContainerFrameLayoutsList(int containerCountInt)
    {
        ArrayList<FrameLayout> returnFrameLayoutList = new ArrayList<FrameLayout>();
        FragmentTransaction fragmentTransaction = app.getCurrentActivity().getSupportFragmentManager().beginTransaction();


        for (int lIndexInt = 0; lIndexInt < containerCountInt; lIndexInt++ )
        {
            FrameLayout currentFrameLayout = new FrameLayout(app.getApplicationContext());

            currentFrameLayout.setTag("FRAMELAYOUT_" + currentFrameLayout.toString());
            currentFrameLayout.setId( View.generateViewId());
            currentFrameLayout.setBackgroundColor( Color.TRANSPARENT);
            currentFrameLayout.setLayoutTransition( createCustomLayoutTransition() );
            currentFrameLayout.getLayoutTransition().enableTransitionType( LayoutTransition.CHANGING);

            returnFrameLayoutList.add(currentFrameLayout);

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
                PropertyValuesHolder.ofFloat("alpha", 1, 0));
        scaleDownAnimator.setDuration(durationMillisecondsInt);
        //scaleDownAnimator.setInterpolator(new OvershootInterpolator());
        scaleDownAnimator.setInterpolator(new OvershootInterpolator());


        scaleUpAnimator = ObjectAnimator.ofPropertyValuesHolder(
                (Object)null,
                PropertyValuesHolder.ofFloat( "scaleX", 0, 1),
                PropertyValuesHolder.ofFloat( "scaleY", 0, 1),
                PropertyValuesHolder.ofFloat("alpha", 0, 1));
        scaleUpAnimator.setDuration(durationMillisecondsInt);
        scaleUpAnimator.setStartDelay(durationMillisecondsInt);
        //scaleUpAnimator.setInterpolator(new OvershootInterpolator());
        scaleUpAnimator.setInterpolator(new OvershootInterpolator());


        returnLayoutTransition = new LayoutTransition();
        returnLayoutTransition.setAnimator(LayoutTransition.APPEARING, scaleUpAnimator);
        returnLayoutTransition.setAnimator(LayoutTransition.DISAPPEARING, scaleDownAnimator);


        return returnLayoutTransition;
    }










    public Fragment getPreviousSlot1Fragment()
    {
        return previousSlot1Fragment;
    }


    public void setBackEnabledBoolean(Boolean backEnabledBoolean )
    {
        isBackEnabledBoolean = backEnabledBoolean;
    }









}
