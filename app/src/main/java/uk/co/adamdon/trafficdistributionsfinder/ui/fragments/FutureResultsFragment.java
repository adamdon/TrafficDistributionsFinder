package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.CurrentItemListAdapter;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.FutureItemListAdapter;

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

    public FutureResultsFragment(ArrayList<ItemModel> resultsItemList)
    {
        this.resultsItemList = resultsItemList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        FutureResultsFragmentBinding futureResultsFragmentBinding;


        futureResultsFragmentBinding = FutureResultsFragmentBinding.inflate(layoutInflater,viewGroup,false);
        futureResultsFragmentBinding.futureHelpTextView.setText("Total results: " + resultsItemList.size());
        futureResultsFragmentBinding.futureListView.setAdapter(new FutureItemListAdapter(requireContext(), R.layout.future_results_item_view, resultsItemList));



        return nestContentInTemplateFrameLayout(futureResultsFragmentBinding.getRoot());
    }









}
