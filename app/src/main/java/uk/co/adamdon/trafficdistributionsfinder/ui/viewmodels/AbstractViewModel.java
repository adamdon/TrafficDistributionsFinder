/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import uk.co.adamdon.trafficdistributionsfinder.App;

public abstract class AbstractViewModel extends AndroidViewModel
{
    App app;

    public AbstractViewModel(@NonNull Application application)
    {
        super(application);
        this.app = (App)application;
    }


}
