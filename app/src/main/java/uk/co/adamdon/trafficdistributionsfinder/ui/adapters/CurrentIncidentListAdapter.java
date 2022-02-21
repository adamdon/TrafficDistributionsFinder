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


//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
//    {
//        return super.getView(position, convertView, parent);
//    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Log.d("TAG", "getView: running here?");
        View returnCurrentView = convertView;

        if(returnCurrentView == null)
        {
            final LayoutInflater lLayoutInflater = LayoutInflater.from( context);
            returnCurrentView = lLayoutInflater.inflate(layoutResourceIdInt, null, false);
        }




        CurrentIncidentModel currentIncident;
        CurrentListItemViewBinding currentListItemViewBinding;


        currentIncident = currentIncidentList.get(position);
        currentListItemViewBinding = CurrentListItemViewBinding.inflate(LayoutInflater.from(context),parent,false);

        currentListItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());


        CurrentViewModel currentViewModel = new ViewModelProvider((FragmentActivity)context).get( CurrentViewModel.class);


        final View lReturnCurrentView2 = returnCurrentView;
        returnCurrentView.setOnClickListener( pView ->
        {
            lReturnCurrentView2.setClickable(false);
            lReturnCurrentView2.setAlpha(0.5f);
//            currentViewModel.onClickGoToRecentFromID( currentIncident.getId().intValue());

        } );


//        return lReturnCurrentView2;
        return currentListItemViewBinding.getRoot();
    }









}
