package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.CurrentViewModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.FutureViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


public class FutureFragment extends AbstractFragment
{
    private FutureViewModel futureViewModel;


    public FutureFragment(App app)
    {
        super(app);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        FutureFragmentBinding futureFragmentBinding;

        futureViewModel = new ViewModelProvider(this).get(FutureViewModel.class);

        futureFragmentBinding = FutureFragmentBinding.inflate(layoutInflater,viewGroup,false);
        futureFragmentBinding.backButton.setOnClickListener(view -> requireActivity().onBackPressed());
//        futureFragmentBinding.currentListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> currentViewModel.onItemClickCurrentListView(position));



//        currentViewModel.getCurrentIncidentListLiveData().observe(getViewLifecycleOwner(),currentIncidentList -> futureFragmentBinding.currentListView.setAdapter(new CurrentIncidentListAdapter(getContext(), R.layout.current_list_item_view, currentViewModel.getCurrentIncidentListLiveData().getValue())));


        return nestContentInTemplateFrameLayout(futureFragmentBinding.getRoot());
    }









}
