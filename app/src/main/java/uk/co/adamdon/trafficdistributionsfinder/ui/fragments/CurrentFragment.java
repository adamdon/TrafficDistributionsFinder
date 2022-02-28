package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.ItemListAdapter;
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


    public CurrentFragment(App app)
    {
        super(app);
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



        currentViewModel.getCurrentIncidentListLiveData().observe(getViewLifecycleOwner(),currentIncidentList -> currentFragmentBinding.currentListView.setAdapter(new ItemListAdapter(getContext(), R.layout.current_list_item_view, currentViewModel.getCurrentIncidentListLiveData().getValue())));


        return nestContentInTemplateFrameLayout(currentFragmentBinding.getRoot());
    }


    public List<ItemModel> getTestList()//delete this one done
    {
        List<ItemModel> itemModels = new ArrayList<>();

        ItemModel itemModel1 = new ItemModel();
        ItemModel itemModel2 = new ItemModel();
        ItemModel itemModel3 = new ItemModel();

        itemModel1.setTitleString("test 1");
        itemModel2.setTitleString("test 2");
        itemModel3.setTitleString("test 3");

        itemModels.add(itemModel1);
        itemModels.add(itemModel2);
        itemModels.add(itemModel3);

        return itemModels;
    }






}
