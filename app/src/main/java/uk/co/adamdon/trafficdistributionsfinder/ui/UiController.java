package uk.co.adamdon.trafficdistributionsfinder.ui;

import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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
        previousSlot1Fragment = new BlankFragment();
        currentSlot1Fragment = new BlankFragment();

        executorService = Executors.newSingleThreadExecutor();
    }


    private Fragment getFragmentById(final int pIdInt)
    {
        final Fragment returnFragment;

        returnFragment = app.getCurrentActivity().getSupportFragmentManager().findFragmentById(pIdInt);

        return returnFragment;
    }



    private void replaceNewFragmentByID( int containerIndexInt, Fragment newFragment)
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













}
