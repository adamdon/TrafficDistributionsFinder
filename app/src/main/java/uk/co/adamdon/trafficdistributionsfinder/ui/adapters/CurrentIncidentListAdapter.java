package uk.co.adamdon.trafficdistributionsfinder.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentListItemViewBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.CurrentIncidentModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.CurrentViewModel;

public class CurrentIncidentListAdapter extends ArrayAdapter<CurrentIncidentModel>
{

    private Context context;
    private int layoutResourceIdInt;
    private List<CurrentIncidentModel> currentIncidentList;


    public CurrentIncidentListAdapter(@NonNull Context context, int layoutResourceIdInt, List<CurrentIncidentModel> currentIncidentList)
    {
        super(context, layoutResourceIdInt, currentIncidentList);
        this.context = context;
        this.layoutResourceIdInt = layoutResourceIdInt;
        this.currentIncidentList = currentIncidentList;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        CurrentListItemViewBinding currentListItemViewBinding;

        CurrentIncidentModel currentIncident;
        CurrentViewModel currentViewModel;


        Log.d("TAG", "getView: running here?");
        currentIncident = currentIncidentList.get(position);
//        currentViewModel = new ViewModelProvider((FragmentActivity)context).get( CurrentViewModel.class); //don't use this unless it's really needed


        if(convertView == null)
        {
            currentListItemViewBinding = CurrentListItemViewBinding.inflate(LayoutInflater.from(context), parent,false);
            convertView = currentListItemViewBinding.getRoot();
        }
        else //recycling view if it is already on screen for better performance
        {
            currentListItemViewBinding = CurrentListItemViewBinding.bind(convertView);
        }

        currentListItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());
        currentListItemViewBinding.currentDateTextView.setText(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(currentIncident.getPunDate()));
        currentListItemViewBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionPreViewString(30));

//        currentListItemViewBinding.currentTitleTextView.setOnClickListener(view ->
//        {
//            currentListItemViewBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionString());
//        });

//        convertView.setOnClickListener( view ->
//        {
////            currentListItemViewBinding.getRoot().setClickable(false);
////            currentListItemViewBinding.getRoot().setAlpha(0.5f);
//
////            currentViewModel.onClickGoToRecentFromID( currentIncident.getId().intValue());
//        } );

        return convertView;
    }









}
