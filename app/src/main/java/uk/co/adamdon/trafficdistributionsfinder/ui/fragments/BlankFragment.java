package uk.co.adamdon.trafficdistributionsfinder.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.adamdon.trafficdistributionsfinder.App;
import uk.co.adamdon.trafficdistributionsfinder.R;

public class BlankFragment extends AbstractFragment
{
    public BlankFragment(App app)
    {
        super(app);

    }


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        final View lContentXmlView;
        final ViewGroup lNestedContentView;


        lContentXmlView  = layoutInflater.inflate( R.layout.blank_fragment, viewGroup, false);

        return lContentXmlView;

    }


}
