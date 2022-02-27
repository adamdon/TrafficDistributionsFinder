package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.FutureViewModel;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DateHelper;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.Date;


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
        futureFragmentBinding.FutureDatePicker.init(
                DateHelper.getInstance().getYearInt(futureViewModel.getSelectedDateLiveData().getValue()),
                DateHelper.getInstance().getMonthInt(futureViewModel.getSelectedDateLiveData().getValue()),
                DateHelper.getInstance().getDayInt(futureViewModel.getSelectedDateLiveData().getValue()),
                (DatePicker view, int year, int monthOfYear, int dayOfMonth) ->
                {
                    futureViewModel.setSelectedDate(DateHelper.getInstance().getDate(year, monthOfYear, dayOfMonth));
                }
        );
        futureFragmentBinding.FutureDatePicker.setMinDate(new Date().getTime());



        futureViewModel.getSelectedDateLiveData().observe(getViewLifecycleOwner(), date -> futureFragmentBinding.selectButton.setText("Search: " + date.toString().substring(0, 11)));


        return nestContentInTemplateFrameLayout(futureFragmentBinding.getRoot());
    }

//    public void testSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
//    {
//        Date testDate = DateHelper.getInstance().getDate(year, monthOfYear, dayOfMonth);
//
//        Log.d("TAG", "testSet: " + year + " " + monthOfYear + " " + dayOfMonth);
//        Log.d("TAG", "testSet: " + testDate.toString());
//    }









}
