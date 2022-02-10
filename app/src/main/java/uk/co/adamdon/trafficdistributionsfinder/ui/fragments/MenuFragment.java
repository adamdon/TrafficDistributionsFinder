package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.AbstractFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.MenuViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
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
    public View onCreateView(LayoutInflater pLayoutInflater, ViewGroup pViewGroup, Bundle pSavedInstanceState)
    {
        final View lContentXmlView;
        final ViewGroup lNestedContentView;
//        HelloWorldBinding binding = DataBindingUtil.setContentView(this, R.layout.hello_world);


        lContentXmlView  = pLayoutInflater.inflate( R.layout.menu_fragment, pViewGroup, false);
        lNestedContentView = nestContentInTemplateFrameLayout(lContentXmlView);
        menuViewModel = new ViewModelProvider(this).get( MenuViewModel.class);




        return lNestedContentView;
    }






}
