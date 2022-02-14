package uk.co.adamdon.trafficdistributionsfinder.ui.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import uk.co.adamdon.trafficdistributionsfinder.business.Config;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.BlankFragment;
import uk.co.adamdon.trafficdistributionsfinder.ui.fragments.MenuFragment;
import uk.co.adamdon.trafficdistributionsfinder.utilities.DataFetcher;

public class CurrentViewModel extends AbstractViewModel
{

    String fullResultString = null;



    public CurrentViewModel( @NonNull Application application )
    {
        super(application);

    }


    public void backOnClick()
    {



        DataFetcher.getInstance().get(Config.CURRENT_INCIDENTS_URL, (results) -> setValue(results));

        app.getUiController().replaceFragmentByID( 1, new MenuFragment(app) );
        app.getUiController().replaceFragmentByID( 2, new BlankFragment(app) );
    }



    public void setValue(Object test)
    {
        Log.d("TAG", "DID THIS WORK" + Thread.currentThread().getName());
        Log.d("TAG", "setValue: test value update in viewmodel ");
        Log.d("TAG",  test.toString());
        Log.d("TAG",  String.valueOf(test.hashCode()));
    }




    private class Task implements Runnable
    {
        private String urlString;

        public Task(String urlString)
        {
            this.urlString = urlString;
        }
        @Override
        public void run()
        {

            URL urlObject;
            URLConnection urlConnection;

            InputStream inputStream;
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader;

            String currentLineString = "";



            try
            {
                urlObject = new URL(urlString);
                urlConnection = urlObject.openConnection();

                inputStream = urlConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                bufferedReader = new BufferedReader(inputStreamReader);


                currentLineString = bufferedReader.readLine(); // read first line
                while(currentLineString != null)
                {
                    fullResultString = fullResultString + currentLineString;
                    fullResultString = fullResultString + System.lineSeparator();

                    Log.d("output",currentLineString);

                    currentLineString = bufferedReader.readLine(); //read next line
                }
                bufferedReader.close();

            }
            catch (Exception e)
            {
                Log.d("testa", "What's going on here?");
                Log.e("testb", e.getMessage());
                Log.d("testc", "ioexception in run");
            }


            app.getUiController().getCurrentActivity().runOnUiThread(new Runnable()
            {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    Log.d("UI thread", fullResultString);
                }
            });

        }

    }


}
