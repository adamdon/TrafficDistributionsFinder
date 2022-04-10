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
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.PlannerResultsFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DateHelper;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;

public class PlannerViewModel extends AbstractViewModel
{
    private MutableLiveData<List<Item>> itemListLiveData;
    private MutableLiveData<Date> selectedDateLiveData;


    public PlannerViewModel(@NonNull Application application )
    {
        super(application);
        Log.d("FutureViewModel", "FutureViewModel: crearted");

        setSelectedDate(new Date(new Date().getTime() + 86400000)); //default date set to tomorrow

        AsyncTask.execute(() -> setItemListLiveData(app.getAllItemsDatabase().ItemDao().getAll()));
    }



    public void setResultsForItemList(Object results) //refactor this out
    {
        ArrayList<Item> itemList;

        Log.d(TAG, "setResultsForItemList on thread:" + Thread.currentThread().getName());
        itemList = XmlToItemList.getInstance().parse(results.toString());

        setItemListLiveData(itemList);
//        Log.d("FutureViewModel", "setResultsForItemList list size:" + getItemListLiveData().getValue().size());
    }


    public void onSearchDateButtonClick()
    {
        ArrayList<Item> fullItemList;
        ArrayList<Item> filteredItemList;
        Date selectedDate;
        Date selectedWithoutTimeDate;


        selectedDate = new Date(Objects.requireNonNull(selectedDateLiveData.getValue()).getTime());
        selectedWithoutTimeDate = DateHelper.getInstance().removeTimeFromDate(selectedDate);
        fullItemList = new ArrayList<>(Objects.requireNonNull(getItemListLiveData().getValue()));
        filteredItemList = new ArrayList<>();

        for(Item currentItem : fullItemList)
        {
            Date startDate = DateHelper.getInstance().removeTimeFromDate(currentItem.getStartDate());
            Date endData = DateHelper.getInstance().removeTimeFromDate(currentItem.getEndDate());

            if((selectedDate.getTime() >= startDate.getTime()) && (selectedDate.getTime() <= endData.getTime()))
            {
                filteredItemList.add(currentItem);
            }
        }

        app.getUiController().replaceFragmentByID( 2, new PlannerResultsFragment(filteredItemList) );

//        Log.d("END", "currentItem: " + filteredItemList.size() );
    }

























//    public void onItemClickCurrentListView(int positionInt)
//    {
//        ItemModel selectedCurrentIncident;
//
//        selectedCurrentIncident = getItemListLiveData().getValue().get(positionInt);
//
//        app.getUiController().replaceFragmentByID( 3, new CurrentSelectedFragment(app, selectedCurrentIncident) );
//    }




    //
    ///// GETTERS AND SETTERS
    //
    public MutableLiveData<List<Item>> getItemListLiveData()
    {
        if (itemListLiveData == null)
        {
            itemListLiveData = new MutableLiveData<>();
            itemListLiveData.setValue(new ArrayList<Item>());
        }
        return itemListLiveData;
    }

    public void setItemListLiveData(List<Item> currentIncidentList)
    {
        if(itemListLiveData == null)
        {
            itemListLiveData = new MutableLiveData<>();
            itemListLiveData.postValue(new ArrayList<Item>(currentIncidentList));
        }
        else
        {
            ArrayList<Item> newItemList;
            newItemList = new ArrayList<>(itemListLiveData.getValue());
            newItemList.addAll(currentIncidentList);
            itemListLiveData.postValue(newItemList);
        }
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
