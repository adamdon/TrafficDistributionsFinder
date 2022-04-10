/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import uk.co.adamdon.trafficdistributionsfinder.utilities.ApiConfig;
import uk.co.adamdon.trafficdistributionsfinder.data.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.LogoFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DateHelper;
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
        AsyncTask.execute(() -> setOnlyCurrentItems(app.getAllItemsDatabase().ItemDao().getAll()));
    }



    public void setOnlyCurrentItems(List<Item> currentIncidentList)
    {
        ArrayList<Item> fullItemList;
        ArrayList<Item> filteredItemList;

        fullItemList = new ArrayList<>(currentIncidentList);


        filteredItemList = new ArrayList<>();

        for(Item currentItem : fullItemList)
        {

            if(!currentItem.getOriginalDescriptionString().startsWith("Start Date: "))
            {
                Log.d(TAG, "setOnlyCurrentItems: " + currentItem.getOriginalDescriptionString());
                filteredItemList.add(currentItem);
            }
        }

        setCurrentIncidentListLiveData(filteredItemList);
    }



    public void onItemClickCurrentListView(int positionInt)
    {
        Item selectedCurrentIncident;

        selectedCurrentIncident = getCurrentIncidentListLiveData().getValue().get(positionInt);

        app.getUiController().replaceFragmentByID( 3, new CurrentSelectedFragment(selectedCurrentIncident) );
    }






    public MutableLiveData<List<Item>> getCurrentIncidentListLiveData()
    {
        if (currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
            currentIncidentListLiveData.setValue(new ArrayList<Item>());
        }
        return currentIncidentListLiveData;
    }



    public void setCurrentIncidentListLiveData(List<Item> currentIncidentList)
    {
        if(currentIncidentListLiveData == null)
        {
            currentIncidentListLiveData = new MutableLiveData<>();
            currentIncidentListLiveData.postValue(new ArrayList<Item>(currentIncidentList));
        }
        else
        {
            ArrayList<Item> newItemList;
            newItemList = new ArrayList<>(currentIncidentListLiveData.getValue());
            newItemList.addAll(currentIncidentList);
            currentIncidentListLiveData.postValue(newItemList);
        }
    }

}
