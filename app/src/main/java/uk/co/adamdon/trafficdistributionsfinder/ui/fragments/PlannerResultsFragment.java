package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import static uk.co.adamdon.trafficdistributionsfinder.App.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.PlannerResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.data.Item;


public class PlannerResultsFragment extends AbstractFragment
{
    ArrayList<Item> resultsItemList;

    public PlannerResultsFragment(ArrayList<Item> resultsItemList)
    {
        this.resultsItemList = resultsItemList;
    }

    public PlannerResultsFragment()
    {
        this.resultsItemList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        PlannerResultsFragmentBinding plannerResultsFragmentBinding;
        plannerResultsFragmentBinding = PlannerResultsFragmentBinding.inflate(layoutInflater,viewGroup,false);
        plannerResultsFragmentBinding.futureHelpTextView.setText("Total results: " + resultsItemList.size());



        SupportMapFragment supportMapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.mapFragmentContainerView);
        supportMapFragment.getMapAsync((googleMap) -> onMapReady(googleMap));



        return nestContentInTemplateFrameLayout(plannerResultsFragmentBinding.getRoot());
    }



    public void onMapReady(GoogleMap googleMap)
    {
        UiSettings uiSettings;
        LatLng defaultLatLng;


        Log.d(TAG, "onMapReady: Map");


        uiSettings = googleMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setCompassEnabled(false);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setRotateGesturesEnabled(false);

        defaultLatLng = new LatLng(55.8642, -4.2518);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLatLng, 10) );

        for(Item currentItem : resultsItemList)
        {
            MarkerOptions markerOptions;

            markerOptions = new MarkerOptions();
            markerOptions.title(currentItem.getTitleString());
            markerOptions.position(currentItem.getGeoPointLatLng());

            googleMap.addMarker(markerOptions);

        }


    }









}
