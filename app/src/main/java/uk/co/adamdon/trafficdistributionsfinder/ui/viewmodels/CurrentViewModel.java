package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.business.Config;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.LogoFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentSelectedFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;

public class CurrentViewModel extends AbstractViewModel
{
    private MutableLiveData<List<ItemModel>> currentIncidentListLiveData;



    public CurrentViewModel( @NonNull Application application )
    {
        super(application);

        Log.d("CurrentViewModel", "CurrentViewModel: crearted");
        DataFetcher.getInstance().get(Config.CURRENT_INCIDENTS_URL, (results) -> setResultsForCurrentIncidentList(results));
    }



    public void setResultsForCurrentIncidentList(Object results) //refactor this out
    {
        ArrayList<ItemModel> currentIncidentList;

        Log.d("CurrentViewModel", "setResultsForCurrentIncidentList on thread:" + Thread.currentThread().getName());
        currentIncidentList = XmlToItemList.getInstance().parse(results.toString());

        setCurrentIncidentListLiveData(currentIncidentList);
    }



    public void onItemClickCurrentListView(int positionInt)
    {
        ItemModel selectedCurrentIncident;

        selectedCurrentIncident = getCurrentIncidentListLiveData().getValue().get(positionInt);

        app.getUiController().replaceFragmentByID( 3, new CurrentSelectedFragment(app, selectedCurrentIncident) );
    }



    public void backOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new LogoFragment(app) );
        app.getUiController().replaceFragmentByID( 1, new MenuFragment(app) );
        app.getUiController().replaceFragmentByID( 2, new BlankFragment(app) );
        app.getUiController().replaceFragmentByID( 3, new BlankFragment(app) );
    }



    public MutableLiveData<List<ItemModel>> getCurrentIncidentListLiveData()
    {
        if (currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
        }
        return currentIncidentListLiveData;
    }

    public void setCurrentIncidentListLiveData(ArrayList<ItemModel> currentIncidentList)
    {
        if(currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
        }
        currentIncidentListLiveData.setValue(currentIncidentList);
    }

}
