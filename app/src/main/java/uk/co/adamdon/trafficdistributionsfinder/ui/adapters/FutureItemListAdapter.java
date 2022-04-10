/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.ui.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;

import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsItemViewBinding;
import uk.co.adamdon.trafficdistributionsfinder.data.Item;

public class FutureItemListAdapter extends ArrayAdapter<Item>
{

    private Context context;
    private int layoutResourceIdInt;
    private List<Item> resultsItemList;


    public FutureItemListAdapter(@NonNull Context context, int layoutResourceIdInt, List<Item> resultsItemList)
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
        Item currentIncident;
        SimpleDateFormat startEndSimpleDateFormat;
        String startEndDateString; //TODO show total days too
        long totalDaysLong;
        int cardViewColorInt;


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

        if(currentIncident.getStartDate().getTime() == currentIncident.getEndDate().getTime())
        {
            totalDaysLong = 1L;
        }
        else
        {
            totalDaysLong = TimeUnit.MILLISECONDS.toDays(currentIncident.getEndDate().getTime() - currentIncident.getStartDate().getTime());
        }


        if(totalDaysLong == 1)
        {
            cardViewColorInt = Color.parseColor("#99FF99");
        }
        else if((totalDaysLong >= 2) && (totalDaysLong <= 4))
        {
            cardViewColorInt = Color.parseColor("#BBFF77");
        }
        else if((totalDaysLong >= 5) && (totalDaysLong <= 10))
        {
            cardViewColorInt = Color.parseColor("#DDFF55");
        }
        else if((totalDaysLong >= 10) && (totalDaysLong <= 15))
        {
            cardViewColorInt = Color.parseColor("#FFFF33");;
        }
        else if((totalDaysLong >= 16) && (totalDaysLong <= 20)) //new
        {
            cardViewColorInt = Color.parseColor("#FBD33D");;
        }
        else if((totalDaysLong >= 21) && (totalDaysLong <= 30))
        {
            cardViewColorInt = Color.parseColor("#F8A746");;
        }
        else if((totalDaysLong >= 31) && (totalDaysLong <= 40))
        {
            cardViewColorInt = Color.parseColor("#F47B50");;
        }
        else if((totalDaysLong >= 41) && (totalDaysLong <= 50))
        {
            cardViewColorInt = Color.parseColor("#E35F44");;
        }
        else if((totalDaysLong >= 51) && (totalDaysLong <= 70))
        {
            cardViewColorInt = Color.parseColor("#D24339");;
        }
        else if((totalDaysLong >= 71) && (totalDaysLong <= 100))
        {
            cardViewColorInt = Color.parseColor("#C1272D");;
        }
        else
        {
            cardViewColorInt = Color.parseColor("#821700");
        }

//        switch((int)totalDaysLong)
//        {
//            case 0:
//                cardViewColorInt = Color.parseColor("#99FF99");
//                break;
//            case (1):
//                cardViewColorInt = Color.parseColor("#BBFF77");
//                break;
//            case 2:
//                cardViewColorInt = Color.parseColor("#DDFF55");
//                break;
//            case 3:
//                cardViewColorInt = Color.parseColor("#FFFF33");
//                break;
//            case 4:
//                cardViewColorInt = Color.parseColor("#FBD33D");
//                break;
//            default:
//                cardViewColorInt = Color.rgb(200,0,0);
//                break;
//        }

        futureResultsItemViewBinding.colorCardView.setCardBackgroundColor(cardViewColorInt);
        futureResultsItemViewBinding.currentTitleTextView.setText(currentIncident.getTitleString());
        futureResultsItemViewBinding.currentDateTextView.setText(startEndDateString + " (Days: " + totalDaysLong + ")");
        futureResultsItemViewBinding.currentDescriptionTextView.setText(currentIncident.getDescriptionPreViewString(40));



        return convertView;
    }









}
