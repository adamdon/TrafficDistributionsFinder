package uk.co.adamdon.trafficdistributionsfinder.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;
import uk.co.adamdon.trafficdistributionsfinder.ui.AbstractActivity;

public class MainActivity extends AbstractActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String testthingString;

        testthingString = getApp().getTestString();

        showToast(testthingString);
    }
}