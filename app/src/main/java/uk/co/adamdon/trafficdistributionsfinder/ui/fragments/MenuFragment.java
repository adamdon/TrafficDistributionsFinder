package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.MenuFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.AbstractActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.MenuViewModel;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.progressindicator.BaseProgressIndicator;

import java.util.Objects;


public class MenuFragment extends AbstractFragment
{
    private MenuViewModel menuViewModel;


    public MenuFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        MenuFragmentBinding menuFragmentBinding;

        menuViewModel = new ViewModelProvider(this).get( MenuViewModel.class);


        menuFragmentBinding = MenuFragmentBinding.inflate(layoutInflater,viewGroup,false);
        menuFragmentBinding.linearProgressIndicator.setVisibility(View.VISIBLE);
        menuFragmentBinding.linearProgressIndicator.setHideAnimationBehavior(BaseProgressIndicator.HIDE_OUTWARD);
        menuFragmentBinding.currentDistributionsButton.setOnClickListener(view -> menuViewModel.currentDistributionsOnClick());
        menuFragmentBinding.currentDistributionsButton.setEnabled(false);
        menuFragmentBinding.futureDistributionsButton.setOnClickListener(view -> menuViewModel.futureDistributionsOnClick());
        menuFragmentBinding.futureDistributionsButton.setEnabled(false);
        menuFragmentBinding.searchDistributionsButton.setOnClickListener(view -> menuViewModel.searchDistributionsOnClick());
        menuFragmentBinding.searchDistributionsButton.setEnabled(false);
        menuFragmentBinding.journeyPlannerButton.setOnClickListener(view -> menuViewModel.journeyPlannerButtonOnClick());
        menuFragmentBinding.journeyPlannerButton.setEnabled(false);



        menuViewModel.getCompletedRequestsBoolean().observe(getViewLifecycleOwner(), (completedRequestsBoolean) -> onCompletedRequestsBooleanChange(completedRequestsBoolean, menuFragmentBinding));




        return nestContentInTemplateFrameLayout(menuFragmentBinding.getRoot());
    }


    public void onCompletedRequestsBooleanChange(Boolean completedRequestsBoolean, MenuFragmentBinding menuFragmentBinding)
    {
        if(completedRequestsBoolean)
        {
            menuFragmentBinding.linearProgressIndicator.setProgressCompat(100, true);
//            menuFragmentBinding.linearProgressIndicator.setVisibility(View.INVISIBLE);
////            menuFragmentBinding.linearProgressIndicator.setVisibility(View.INVISIBLE);
            menuFragmentBinding.currentDistributionsButton.setEnabled(true);
            menuFragmentBinding.futureDistributionsButton.setEnabled(true);
            menuFragmentBinding.searchDistributionsButton.setEnabled(true);
            menuFragmentBinding.journeyPlannerButton.setEnabled(true);
        }
        Log.d(TAG, "onCompletedRequestsBooleanChange: WORKED " + completedRequestsBoolean);
    }






}
