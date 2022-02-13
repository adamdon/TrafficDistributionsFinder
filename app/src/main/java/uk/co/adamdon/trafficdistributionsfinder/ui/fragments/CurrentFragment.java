package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.MenuFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.CurrentViewModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.MenuViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


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
        currentFragmentBinding.backButton.setOnClickListener(view -> currentViewModel.backOnClick());




        return nestContentInTemplateFrameLayout(currentFragmentBinding.getRoot());
    }






}
