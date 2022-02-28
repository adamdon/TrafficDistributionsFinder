package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToCurrentInstancesList;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentSelectedFragment;

public class FutureViewModel extends AbstractViewModel
{
    private MutableLiveData<List<ItemModel>> itemListLiveData;
    private MutableLiveData<Date> selectedDateLiveData;
    private Date selectedDate;



    public FutureViewModel( @NonNull Application application )
    {
        super(application);
        Log.d("FutureViewModel", "FutureViewModel: crearted");

        setSelectedDate(new Date(new Date().getTime() + 86400000)); //default date set to tomorrow

//        DataFetcher.getInstance().get(Config.CURRENT_INCIDENTS_URL, (results) -> setResultsForCurrentIncidentList(results));
    }



    public void setResultsForCurrentIncidentList(Object results) //refactor this out
    {
        ArrayList<ItemModel> currentIncidentList;

        Log.d("CurrentViewModel", "setResultsForCurrentIncidentList on thread:" + Thread.currentThread().getName());
        currentIncidentList = XmlToCurrentInstancesList.getInstance().parse(results.toString());

        setItemListLiveData(currentIncidentList);
    }



    public void onItemClickCurrentListView(int positionInt)
    {
        ItemModel selectedCurrentIncident;

        selectedCurrentIncident = getItemListLiveData().getValue().get(positionInt);

        app.getUiController().replaceFragmentByID( 3, new CurrentSelectedFragment(app, selectedCurrentIncident) );
    }



    public MutableLiveData<List<ItemModel>> getItemListLiveData()
    {
        if (itemListLiveData == null)
        {
            itemListLiveData = new MutableLiveData<>();
        }
        return itemListLiveData;
    }

    public void setItemListLiveData(ArrayList<ItemModel> currentIncidentList)
    {
        if(itemListLiveData == null)
        {
            itemListLiveData = new MutableLiveData<>();
        }
        itemListLiveData.setValue(currentIncidentList);
    }







    public MutableLiveData<Date> getSelectedDateLiveData()
    {
        if (selectedDateLiveData == null)
        {
            selectedDateLiveData = new MutableLiveData<>();
        }
        return selectedDateLiveData;
    }

    public void setSelectedDate(Date selectedDate)
    {
        if (selectedDateLiveData == null)
        {
            selectedDateLiveData = new MutableLiveData<>();
        }
//        Log.d("selectedDate", "selectedDate: " + selectedDate.toString());
        this.selectedDateLiveData.setValue(selectedDate);
    }
}
