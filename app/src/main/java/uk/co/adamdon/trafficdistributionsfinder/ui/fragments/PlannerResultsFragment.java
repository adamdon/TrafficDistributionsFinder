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
import uk.co.adamdon.trafficdistributionsfinder.databinding.PlannerResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.FutureItemListAdapter;


public class PlannerResultsFragment extends AbstractFragment
{
    ArrayList<ItemModel> resultsItemList;

    public PlannerResultsFragment(App app, ArrayList<ItemModel> resultsItemList)
    {
        super(app);
        this.resultsItemList = resultsItemList;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        PlannerResultsFragmentBinding plannerResultsFragmentBinding;


        plannerResultsFragmentBinding = PlannerResultsFragmentBinding.inflate(layoutInflater,viewGroup,false);
        plannerResultsFragmentBinding.futureHelpTextView.setText("Total results: " + resultsItemList.size());
        plannerResultsFragmentBinding.futureListView.setAdapter(new FutureItemListAdapter(requireContext(), R.layout.future_results_item_view, resultsItemList));



        return nestContentInTemplateFrameLayout(plannerResultsFragmentBinding.getRoot());
    }









}
