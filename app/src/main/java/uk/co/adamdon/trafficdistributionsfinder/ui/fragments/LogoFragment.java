package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.LogoFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.MenuFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.MenuViewModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;


public class LogoFragment extends AbstractFragment
{
    private MenuViewModel menuViewModel;


    public LogoFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        LogoFragmentBinding logoFragmentBinding;



        logoFragmentBinding = LogoFragmentBinding.inflate(layoutInflater,viewGroup,false);




        return nestContentInTemplateFrameLayout(logoFragmentBinding.getRoot());
    }






}
