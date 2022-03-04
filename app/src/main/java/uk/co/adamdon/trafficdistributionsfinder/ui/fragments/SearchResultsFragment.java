package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.SearchResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.FutureItemListAdapter;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.SearchItemListAdapter;


public class SearchResultsFragment extends AbstractFragment
{
    ArrayList<ItemModel> resultsItemList;

    public SearchResultsFragment(App app, ArrayList<ItemModel> resultsItemList)
    {
        super(app);
        this.resultsItemList = resultsItemList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        SearchResultsFragmentBinding searchResultsFragmentBinding;


        searchResultsFragmentBinding = SearchResultsFragmentBinding.inflate(layoutInflater,viewGroup,false);
        searchResultsFragmentBinding.futureHelpTextView.setText("Total results: " + resultsItemList.size());
        searchResultsFragmentBinding.futureListView.setAdapter(new SearchItemListAdapter(requireContext(), R.layout.search_results_item_view, resultsItemList));



        return nestContentInTemplateFrameLayout(searchResultsFragmentBinding.getRoot());
    }









}