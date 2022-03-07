package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.data.Item;
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
    ArrayList<Item> resultsItemList;

    public FutureResultsFragment(ArrayList<Item> resultsItemList)
    {
        this.resultsItemList = resultsItemList;
    }

    public FutureResultsFragment()
    {
        this.resultsItemList = new ArrayList<>();
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
