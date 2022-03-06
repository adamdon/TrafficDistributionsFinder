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
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.databinding.FutureResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.databinding.PlannerResultsFragmentBinding;
import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;
import uk.co.adamdon.trafficdistributionsfinder.ui.adapters.FutureItemListAdapter;


public class PlannerResultsFragment extends AbstractFragment
{
    ArrayList<ItemModel> resultsItemList;

    public PlannerResultsFragment(App app, ArrayList<ItemModel> resultsItemList)
    {
        super(app);
        this.resultsItemList = resultsItemList;
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
        Log.d(TAG, "onMapReady: Map");

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions()
                .position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }









}
