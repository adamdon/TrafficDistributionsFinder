package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentListItemViewBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.CurrentIncidentModel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;


public class CurrentSelectedFragment extends AbstractFragment
{
    CurrentIncidentModel currentIncident;

    public CurrentSelectedFragment(App app, CurrentIncidentModel currentIncident)
    {
        super(app);
        this.currentIncident = currentIncident;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        CurrentListItemViewBinding currentListItemViewBinding;


        currentListItemViewBinding = CurrentListItemViewBinding.inflate(layoutInflater,viewGroup,false);

//        currentListItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());
        currentListItemViewBinding.currentTitleTextView.setText("");
//        currentListItemViewBinding.currentDateTextView.setText(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(currentIncident.getPunDate()));
        currentListItemViewBinding.currentDateTextView.setText("");
        currentListItemViewBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionString());



        return nestContentInTemplateFrameLayout(currentListItemViewBinding.getRoot());
    }









}
