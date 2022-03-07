package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.data.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.FutureFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.PlannerFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.SearchFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.ApiConfig;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;

public class MenuViewModel extends AbstractViewModel
{
    private int completedRequestsInt;
    private MutableLiveData<Boolean> completedRequestsBoolean;

    public MenuViewModel( @NonNull Application application )
    {
        super(application);
        completedRequestsInt = 0;
        completedRequestsBoolean = new MutableLiveData<>(false);
        AsyncTask.execute(() -> initializeData());
    }

    public void initializeData()
    {
        if(app.getAllItemsDatabase().ItemDao().getAll().size() == 0)
        {
            for(String apiUrlString : ApiConfig.getApiStringList())
            {
                if(ApiConfig.getApiStringList().indexOf(apiUrlString) == (ApiConfig.getApiStringList().size() - 1))
                {
                    Log.d(TAG, "initializeData: onFinalRequestBoolean " + ApiConfig.getApiStringList().indexOf(apiUrlString));
                }
                DataFetcher.getInstance().get(apiUrlString, (results) -> setResultsForItemList(results));
            }
        }
        else
        {
            completedRequestsBoolean.postValue(true);
        }
    }

    public void setResultsForItemList(final Object results) //refactor this out
    {
        AsyncTask.execute(() ->
        {
            ArrayList<Item> itemList;


            itemList = XmlToItemList.getInstance().parse(results.toString());
            for (Item currentItem : itemList)
            {
                app.getAllItemsDatabase().ItemDao().insertAll(currentItem);
            }

            completedRequestsInt++;
            if(completedRequestsInt == ApiConfig.getApiStringList().size())
            {
                Log.d(TAG, "!!!!completedRequestsInt " + completedRequestsInt);
                Log.d(TAG, "getAllItemsDatabase" + app.getAllItemsDatabase().ItemDao().getAll().size());
                completedRequestsBoolean.postValue(true);
            }

        });
    }









    public void currentDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment() );
        app.getUiController().replaceFragmentByID( 1, new CurrentFragment() );
    }


    public void futureDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment() );
        app.getUiController().replaceFragmentByID( 1, new FutureFragment() );
    }

    public void searchDistributionsOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment() );
        app.getUiController().replaceFragmentByID( 1, new SearchFragment() );
    }

    public void journeyPlannerButtonOnClick()
    {
        app.getUiController().replaceFragmentByID( 0, new BlankFragment() );
        app.getUiController().replaceFragmentByID( 1, new PlannerFragment() );
    }


    public MutableLiveData<Boolean> getCompletedRequestsBoolean()
    {
        return completedRequestsBoolean;
    }
}
