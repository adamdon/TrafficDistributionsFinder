package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.utilities.ApiConfig;
import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.LogoFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentSelectedFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;

public class CurrentViewModel extends AbstractViewModel
{
    private MutableLiveData<List<Item>> currentIncidentListLiveData;



    public CurrentViewModel( @NonNull Application application )
    {
        super(application);

        Log.d("CurrentViewModel", "CurrentViewModel: crearted");
        DataFetcher.getInstance().get(ApiConfig.CURRENT_INCIDENTS_URL, (results) -> setResultsForCurrentIncidentList(results));
    }



    public void setResultsForCurrentIncidentList(Object results) //refactor this out
    {
        ArrayList<Item> currentIncidentList;

        Log.d("CurrentViewModel", "setResultsForCurrentIncidentList on thread:" + Thread.currentThread().getName());
        currentIncidentList = XmlToItemList.getInstance().parse(results.toString());

        setCurrentIncidentListLiveData(currentIncidentList);
    }



    public void onItemClickCurrentListView(int positionInt)
    {
        Item selectedCurrentIncident;

        selectedCurrentIncident = getCurrentIncidentListLiveData().getValue().get(positionInt);

        app.getUiController().replaceFragmentByID( 3, new CurrentSelectedFragment(selectedCurrentIncident) );
    }



    public void backOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new LogoFragment() );
        app.getUiController().replaceFragmentByID( 1, new MenuFragment() );
        app.getUiController().replaceFragmentByID( 2, new BlankFragment() );
        app.getUiController().replaceFragmentByID( 3, new BlankFragment() );
    }



    public MutableLiveData<List<Item>> getCurrentIncidentListLiveData()
    {
        if (currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
        }
        return currentIncidentListLiveData;
    }

    public void setCurrentIncidentListLiveData(ArrayList<Item> currentIncidentList)
    {
        if(currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
        }
        currentIncidentListLiveData.setValue(currentIncidentList);
    }

}
