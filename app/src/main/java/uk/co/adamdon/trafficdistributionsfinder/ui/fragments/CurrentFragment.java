package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.CurrentIncidentModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.CurrentIncidentListAdapter;
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
import java.util.Objects;


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



        currentViewModel.getCurrentIncidentListLiveData().observe(getViewLifecycleOwner(),currentIncidentList -> currentFragmentBinding.currentListView.setAdapter(new CurrentIncidentListAdapter(getContext(), R.layout.current_list_item_view, currentViewModel.getCurrentIncidentListLiveData().getValue())));


        return nestContentInTemplateFrameLayout(currentFragmentBinding.getRoot());
    }


    public List<CurrentIncidentModel> getTestList()//delete this one done
    {
        List<CurrentIncidentModel> currentIncidentModels = new ArrayList<>();

        CurrentIncidentModel currentIncidentModel1 = new CurrentIncidentModel();
        CurrentIncidentModel currentIncidentModel2 = new CurrentIncidentModel();
        CurrentIncidentModel currentIncidentModel3 = new CurrentIncidentModel();

        currentIncidentModel1.setTitleString("test 1");
        currentIncidentModel2.setTitleString("test 2");
        currentIncidentModel3.setTitleString("test 3");

        currentIncidentModels.add(currentIncidentModel1);
        currentIncidentModels.add(currentIncidentModel2);
        currentIncidentModels.add(currentIncidentModel3);

        return currentIncidentModels;
    }






}
