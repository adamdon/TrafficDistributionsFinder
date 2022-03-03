package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.FutureFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.SearchFragment;

public class MenuViewModel extends AbstractViewModel
{

    public MenuViewModel( @NonNull Application application )
    {
        super(application);

    }


    public void currentDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment(app) );
        app.getUiController().replaceFragmentByID( 1, new CurrentFragment(app) );
    }


    public void futureDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment(app) );
        app.getUiController().replaceFragmentByID( 1, new FutureFragment(app) );
    }

    public void searchDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment(app) );
        app.getUiController().replaceFragmentByID( 1, new SearchFragment(app) );
    }

}
