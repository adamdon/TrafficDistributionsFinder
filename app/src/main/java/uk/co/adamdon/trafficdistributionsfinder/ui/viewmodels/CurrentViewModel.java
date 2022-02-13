package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;

public class CurrentViewModel extends AbstractViewModel
{



    public CurrentViewModel( @NonNull Application application )
    {
        super(application);

    }


    public void backOnClick()
    {
        app.getUiController().replaceFragmentByID( 1, new MenuFragment(app) );
        app.getUiController().replaceFragmentByID( 2, new BlankFragment(app) );
    }

}
