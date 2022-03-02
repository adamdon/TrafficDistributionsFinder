package uk.co.adamdon.trafficdistributionsfinder.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;

import uk.co.adamdon.trafficdistributionsfinder.databinding.CurrentListItemViewBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsItemViewBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels.CurrentViewModel;

public class FutureItemListAdapter extends ArrayAdapter<ItemModel>
{

    private Context context;
    private int layoutResourceIdInt;
    private List<ItemModel> resultsItemList;


    public FutureItemListAdapter(@NonNull Context context, int layoutResourceIdInt, List<ItemModel> resultsItemList)
    {
        super(context, layoutResourceIdInt, resultsItemList);
        this.context = context;
        this.layoutResourceIdInt = layoutResourceIdInt;
        this.resultsItemList = resultsItemList;

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        FutureResultsItemViewBinding futureResultsItemViewBinding;
        ItemModel currentIncident;
        SimpleDateFormat startEndSimpleDateFormat;
        String startEndDateString;

        if(convertView == null)
        {
            futureResultsItemViewBinding = FutureResultsItemViewBinding.inflate(LayoutInflater.from(context), parent,false);
            convertView = futureResultsItemViewBinding.getRoot();
        }
        else //recycling view if it is already on screen for better performance
        {
            futureResultsItemViewBinding = FutureResultsItemViewBinding.bind(convertView);
        }

        currentIncident = resultsItemList.get(position);

        startEndSimpleDateFormat = new SimpleDateFormat("dd-MM-yy");
        startEndDateString = startEndSimpleDateFormat.format(currentIncident.getStartDate());
        startEndDateString += " to ";
        startEndDateString += startEndSimpleDateFormat.format(currentIncident.getEndDate());

        futureResultsItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());
//        futureResultsItemViewBinding.currentDateTextView.setText(new SimpleDateFormat("HH:mm dd-MM-yyyy").format(currentIncident.getPunDate()));
        futureResultsItemViewBinding.currentDateTextView.setText(startEndDateString);
        futureResultsItemViewBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionPreViewString(40));



        return convertView;
    }









}
