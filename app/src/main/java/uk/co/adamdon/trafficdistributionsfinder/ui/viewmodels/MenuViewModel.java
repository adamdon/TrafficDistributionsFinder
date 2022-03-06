package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.data.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.CurrentFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.FutureFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.PlannerFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.SearchFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.ApiConfig;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;
import uk.co.adamdon.trafficdistributionsfinder.utilities.XmlToItemList;

public class MenuViewModel extends AbstractViewModel
{
    ArrayList<Item> resultsList;

    public MenuViewModel( @NonNull Application application )
    {
        super(application);
        AsyncTask.execute(() -> initializeData());
    }

    public void initializeData()
    {
        resultsList = new ArrayList<>(app.getAppDatabase().ItemDao().getAll());
        if(resultsList.size() == 0)
        {
            DataFetcher.getInstance().get(ApiConfig.CURRENT_INCIDENTS_URL, (results) -> setResultsForItemList(results));
            DataFetcher.getInstance().get(ApiConfig.ROADWORKS_URL, (results) -> setResultsForItemList(results));
            DataFetcher.getInstance().get(ApiConfig.PLANNED_ROADWORKS_URL, (results) -> setResultsForItemList(results));
        }
    }

    public void setResultsForItemList(Object results) //refactor this out
    {
        ArrayList<Item> itemList;

        itemList = XmlToItemList.getInstance().parse(results.toString());
        resultsList.addAll(itemList);

        for (Item currentItem : itemList)
        {
            AsyncTask.execute(() -> app.getAppDatabase().ItemDao().insertAll(currentItem));
        }
        Log.d(TAG, "setResultsForItemList: size " + resultsList.size());
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

}
