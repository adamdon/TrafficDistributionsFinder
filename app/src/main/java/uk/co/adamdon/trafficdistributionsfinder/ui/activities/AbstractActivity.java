/* Copyright (c) 2022 Adam Don
 * MIT License
 * S1025475
 * */
package uk.co.adamdon.trafficdistributionsfinder.ui.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;

import uk.co.adamdon.trafficdistributionsfinder.App;


public abstract class AbstractActivity extends AppCompatActivity
{

    protected App app;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        app = (App)getApplication();
    }



    protected App getApp()
    {
        return app;
    }


    //
    //Generic methods
    //
    public void showToast(String textString)
    {
        Toast.makeText(this, textString, Toast.LENGTH_LONG).show();
    }




    protected void showSnackbar(CoordinatorLayout coordinatorLayout, String textString)
    {
        Snackbar snackbar;


        snackbar = Snackbar.make(coordinatorLayout, textString, Snackbar.LENGTH_LONG);
        snackbar.show();
    }




    protected void transactFragment(int containerViewIdInt, Fragment fragment)
    {
        FragmentTransaction fragmentTransaction;


        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewIdInt, fragment);
        fragmentTransaction.commit();
    }


}
