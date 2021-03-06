/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.AbstractActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.MainActivity;

public abstract class AbstractFragment extends Fragment
{
    private FrameLayout rootFrameLayout;


    protected AbstractFragment()
    {
        //rootFrameLayout = nestContentIntoTemplateFrameLayout();

    }

    public App getApp()
    {
        return (App)(requireActivity().getApplication());
    }

    protected FrameLayout nestContentInTemplateFrameLayout(View contentView)
    {
        final FrameLayout rootFrameLayout;
        final CardView wrapperCardView;
        final LinearLayout contentLinearLayout;


        rootFrameLayout = new FrameLayout(getActivity().getApplicationContext());
        rootFrameLayout.setId( View.generateViewId() );
        rootFrameLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        wrapperCardView = new CardView( getActivity().getApplicationContext() );
        wrapperCardView.setId( View.generateViewId() );
        wrapperCardView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((FrameLayout.LayoutParams)wrapperCardView.getLayoutParams()).setMargins(fromDPtoPX(8), fromDPtoPX(8), fromDPtoPX(8), fromDPtoPX(8));
        wrapperCardView.setCardBackgroundColor( Color.WHITE );
        wrapperCardView.setRadius(fromDPtoPX(8) );
        wrapperCardView.setCardElevation(fromDPtoPX(4) );
        wrapperCardView.setMaxCardElevation(fromDPtoPX(4)  );

        contentLinearLayout = new LinearLayout(getActivity().getApplicationContext());
        contentLinearLayout.setId( View.generateViewId() );
        contentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        contentLinearLayout.setOrientation( LinearLayout.VERTICAL );
        contentLinearLayout.setPaddingRelative(fromDPtoPX(16), fromDPtoPX(16), fromDPtoPX(16), fromDPtoPX(16));

        contentLinearLayout.addView(contentView);
        wrapperCardView.addView( contentLinearLayout );
        rootFrameLayout.addView( wrapperCardView );


        return rootFrameLayout;
    }



    public int fromDPtoPX(float dpFloat)
    {
        final int pxInt;

        pxInt = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpFloat, getApp().getResources().getDisplayMetrics());

        return pxInt;
    }


    public void confirmConnected()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null)
        {
            if ((connectivityManager.getActiveNetwork() != null) && (connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork()) != null))
            {
                Log.d(TAG, "confirmConnected: Internet found");
            }
            else
            {
                Log.d(TAG, "confirmConnected: Internet NOT found");
                ((MainActivity)requireActivity()).showToast("App Requires Internet");
                requireActivity().finish();
//                System.exit(0);
            }
        }
    }















}