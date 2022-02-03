package uk.co.adamdon.trafficdistributionsfinder.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.ui.AbstractActivity;

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
        setContentView(R.layout.activity_main);
        rootLinearLayout = findViewById( R.id.rootLinearLayout);





        String testthingString;

        testthingString = getApp().getTestString();

        showToast(testthingString);
    }
}