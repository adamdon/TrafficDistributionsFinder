package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.CurrentItemListAdapter;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.CurrentViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;


public class CurrentFragment extends AbstractFragment
{
    private CurrentViewModel currentViewModel;


    public CurrentFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        CurrentFragmentBinding currentFragmentBinding;

        currentViewModel = new ViewModelProvider(this).get( CurrentViewModel.class);

        currentFragmentBinding = CurrentFragmentBinding.inflate(layoutInflater,viewGroup,false);
//        currentFragmentBinding.backButton.setOnClickListener(view -> currentViewModel.backOnClick());
        currentFragmentBinding.backButton.setOnClickListener(view -> requireActivity().onBackPressed());
        currentFragmentBinding.currentListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> currentViewModel.onItemClickCurrentListView(position));



        currentViewModel.getCurrentIncidentListLiveData().observe(getViewLifecycleOwner(),currentIncidentList -> currentFragmentBinding.currentListView.setAdapter(new CurrentItemListAdapter(getContext(), R.layout.current_list_item_view, currentViewModel.getCurrentIncidentListLiveData().getValue())));


        return nestContentInTemplateFrameLayout(currentFragmentBinding.getRoot());
    }


    public List<Item> getTestList()//delete this one done
    {
        List<Item> items = new ArrayList<>();

        Item item1 = new Item();
        Item item2 = new Item();
        Item item3 = new Item();

        item1.setTitleString("test 1");
        item2.setTitleString("test 2");
        item3.setTitleString("test 3");

        items.add(item1);
        items.add(item2);
        items.add(item3);

        return items;
    }






}
