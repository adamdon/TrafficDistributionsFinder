package uk.co.adamdon.trafficdistributionsfinder.ui.activities;

import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.ui.activities.AbstractActivity;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;

public class MainActivity extends AbstractActivity
{
    private LinearLayout rootLinearLayout;
    private LinearLayout fragmentsLinearLayout;

    private ArrayList<FrameLayout> containersFrameLayoutsList;
    private ArrayList<Fragment> currentFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setBackgroundColor(Color.parseColor("#EEEEEE"));


        setContentView(R.layout.activity_main);

        fragmentsLinearLayout = getApp().getUiController().createContainerizedLinearLayout( 4);
        rootLinearLayout = findViewById( R.id.rootLinearLayout);
        rootLinearLayout.addView(fragmentsLinearLayout);


        getApp().getUiController().replaceFragmentByID( 0, new MenuFragment(getApp()));
        getApp().getUiController().replaceFragmentByID( 1, new BlankFragment(getApp()));


        String testthingString;

        testthingString = getApp().getTestString();

        showToast(testthingString);
    }
}