package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.MenuFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.AbstractFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.MenuViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


public class MenuFragment extends AbstractFragment
{
    private MenuViewModel menuViewModel;

    private Button currentDistributionsButton;
    private Button futureDistributionsButton;
    private Button searchDistributionsButton;
    private Button journeyPlannerButton;
    private Button mSettingsButton;


    public MenuFragment(App app)
    {
        super(app);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        MenuFragmentBinding menuFragmentBinding;

        menuViewModel = new ViewModelProvider(this).get( MenuViewModel.class);


        menuFragmentBinding = MenuFragmentBinding.inflate(layoutInflater,viewGroup,false);
        menuFragmentBinding.currentDistributionsButton.setOnClickListener(view -> menuViewModel.currentDistributionsOnClick());




        return nestContentInTemplateFrameLayout(menuFragmentBinding.getRoot());
    }






}
