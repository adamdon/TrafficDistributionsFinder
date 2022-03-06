package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentSelectedFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.data.models.Item;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class CurrentSelectedFragment extends AbstractFragment
{
    Item currentIncident;

    public CurrentSelectedFragment(Item currentIncident)
    {
        this.currentIncident = currentIncident;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
//        Log.d("TAG", "onCreateView: " + currentIncident.getGeoPointString());
//        Log.d("TAG", "onCreateView: " + currentIncident.getDescriptionString());
//        Log.d("TAG", "onCreateView: " + currentIncident.getLinkString());
        CurrentSelectedFragmentBinding currentSelectedFragmentBinding;


        currentSelectedFragmentBinding = CurrentSelectedFragmentBinding.inflate(layoutInflater,viewGroup,false);

//        currentListItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());
//        currentListItemViewBinding.currentTitleTextView.setText("");
//        currentListItemViewBinding.currentDateTextView.setText(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(currentIncident.getPunDate()));
//        currentListItemViewBinding.currentDateTextView.setText("");
        currentSelectedFragmentBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionString());



        return nestContentInTemplateFrameLayout(currentSelectedFragmentBinding.getRoot());
    }









}
