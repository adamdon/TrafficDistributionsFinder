package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentSelectedFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.ItemListAdapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class FutureResultsFragment extends AbstractFragment
{
    ArrayList<ItemModel> resultsItemList;

    public FutureResultsFragment(App app, ArrayList<ItemModel> resultsItemList)
    {
        super(app);
        this.resultsItemList = resultsItemList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        FutureResultsFragmentBinding futureResultsFragmentBinding;


        futureResultsFragmentBinding = FutureResultsFragmentBinding.inflate(layoutInflater,viewGroup,false);
        futureResultsFragmentBinding.futureHelpTextView.setText("Total results: " + resultsItemList.size());
        futureResultsFragmentBinding.futureListView.setAdapter(new ItemListAdapter(getContext(), R.layout.current_list_item_view, resultsItemList));



        return nestContentInTemplateFrameLayout(futureResultsFragmentBinding.getRoot());
    }









}
