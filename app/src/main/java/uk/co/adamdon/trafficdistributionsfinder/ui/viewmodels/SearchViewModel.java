package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uk.co.adamdon.trafficdistributionsfinder.utilities.ApiConfig;
import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.SearchResultsFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;

public class SearchViewModel extends AbstractViewModel
{
    private MutableLiveData<List<Item>> itemListLiveData;
    private MutableLiveData<String> textStringLiveData;


    public SearchViewModel(@NonNull Application application )
    {
        super(application);
        Log.d(TAG, "SearchViewModel: start up");

        DataFetcher.getInstance().get(ApiConfig.CURRENT_INCIDENTS_URL, (results) -> setResultsForItemList(results));
        DataFetcher.getInstance().get(ApiConfig.ROADWORKS_URL, (results) -> setResultsForItemList(results));
        DataFetcher.getInstance().get(ApiConfig.PLANNED_ROADWORKS_URL, (results) -> setResultsForItemList(results));
    }



    public void setResultsForItemList(Object results) //refactor this out
    {
        ArrayList<Item> itemList;

        Log.d("FutureViewModel", "setResultsForItemList on thread:" + Thread.currentThread().getName());
        itemList = XmlToItemList.getInstance().parse(results.toString());

        setItemListLiveData(itemList);
//        Log.d("FutureViewModel", "setResultsForItemList list size:" + getItemListLiveData().getValue().size());
    }


    public void onSearchTextButtonClick()
    {
        ArrayList<Item> fullItemList;
        ArrayList<Item> filteredItemList;
        String searchTextString;


        if(textStringLiveData.getValue() != null && !textStringLiveData.getValue().equals(""))
        {
            searchTextString = textStringLiveData.getValue();
            searchTextString = searchTextString.toLowerCase();
            fullItemList = new ArrayList<>(Objects.requireNonNull(getItemListLiveData().getValue()));
            filteredItemList = new ArrayList<>();
            Log.d(TAG, "onSearchTextButtonClick: " + searchTextString);

            for(Item currentItem : fullItemList)
            {
                StringBuilder contentStringBuilder;

                contentStringBuilder = new StringBuilder(" ");
                contentStringBuilder.append(currentItem.getTitleString().toLowerCase());
                contentStringBuilder.append(" ");
                contentStringBuilder.append(currentItem.getDescriptionString().toLowerCase());


                if(contentStringBuilder.toString().contains(searchTextString))
                {
                    Log.d(TAG, "onSearchTextButtonClick: match");
                    filteredItemList.add(currentItem);
                }
            }
            
            app.getUiController().replaceFragmentByID( 2, new SearchResultsFragment(filteredItemList) );
        }
        else
        {
            Log.d(TAG, "onSearchTextButtonClick: no text entered");
        }


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

    public void setItemListLiveData(ArrayList<Item> currentIncidentList)
    {
        ArrayList<Item> newItemList;

        if(itemListLiveData == null)
        {
            itemListLiveData = new MutableLiveData<>();
            itemListLiveData.setValue(new ArrayList<Item>());
        }

        newItemList = new ArrayList<>(Objects.requireNonNull(itemListLiveData.getValue()));
        newItemList.addAll(currentIncidentList);

        itemListLiveData.setValue(newItemList);
    }


    public MutableLiveData<String> getTextStringLiveData()
    {
        if (textStringLiveData == null)
        {
            textStringLiveData = new MutableLiveData<>();
        }
        return textStringLiveData;
    }


    public void setTextStringLiveData(String textString)
    {

        if (textStringLiveData == null)
        {
            textStringLiveData = new MutableLiveData<>();
        }
        this.textStringLiveData.setValue(textString);

    }


}
