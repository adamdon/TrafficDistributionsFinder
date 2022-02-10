package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
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
        app.getUiController().replaceFragmentByID( 1, new MenuFragment(app) );
        app.getUiController().replaceFragmentByID( 2, new MenuFragment(app) );
    }

}
