package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;

public class MenuViewModel extends AbstractViewModel
{
    private static final String TAG = "MenuViewModel";



    public MenuViewModel( @NonNull Application application )
    {
        super(application);

    }


    public void currentDistributionsOnClick()
    {
        Log.d(TAG, "currentDistributionsOnClick: test");
        app.getUiController().replaceFragmentByID( 1, new CurrentFragment(app) );
//        app.getUiController().replaceFragmentByID( 2, new MenuFragment(app) );
    }

}
