package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.PlannerFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.PlannerViewModel;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DateHelper;


public class PlannerFragment extends AbstractFragment
{
    public PlannerFragment()
    {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        PlannerViewModel plannerViewModel;
        PlannerFragmentBinding plannerFragmentBinding;

        plannerViewModel = new ViewModelProvider(this).get(PlannerViewModel.class);

        plannerFragmentBinding = PlannerFragmentBinding.inflate(layoutInflater,viewGroup,false);
        plannerFragmentBinding.backButton.setOnClickListener(view -> requireActivity().onBackPressed());
        plannerFragmentBinding.searchDateButton.setOnClickListener(view -> plannerViewModel.onSearchDateButtonClick());
        plannerFragmentBinding.plannerDatePicker.init(
                DateHelper.getInstance().getYearInt(plannerViewModel.getSelectedDateLiveData().getValue()),
                DateHelper.getInstance().getMonthInt(plannerViewModel.getSelectedDateLiveData().getValue()),
                DateHelper.getInstance().getDayInt(plannerViewModel.getSelectedDateLiveData().getValue()),
                (DatePicker view, int year, int monthOfYear, int dayOfMonth) -> plannerViewModel.setSelectedDate(DateHelper.getInstance().getDate(year, monthOfYear, dayOfMonth))
        );
        plannerFragmentBinding.plannerDatePicker.setMinDate(new Date().getTime());



        plannerViewModel.getSelectedDateLiveData().observe(getViewLifecycleOwner(), date -> plannerFragmentBinding.searchDateButton.setText("Search Date: " + date.toString().substring(0, 11)));


        return nestContentInTemplateFrameLayout(plannerFragmentBinding.getRoot());
    }


}
