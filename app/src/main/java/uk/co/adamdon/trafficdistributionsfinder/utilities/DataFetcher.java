package uk.co.adamdon.trafficdistributionsfinder.utilities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DataFetcher
{
    private static DataFetcher INSTANCE;

//    private ExecutorService executor = Executors.newSingleThreadExecutor();
//    private Handler handler = new Handler(Looper.getMainLooper());

    private DataFetcher()
    {
    }

    public static DataFetcher getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new DataFetcher();
        }
        return INSTANCE;
    }




    public void get(String urlString, ParameterRunnable whenCompleteRunnable)
    {
        //worker thread to allow blocking
        Executors.newSingleThreadExecutor().execute(() ->
        {
            try
            {
                final Future<String> fetchStringFuture;
                final String resultString;

                fetchStringFuture = Executors.newSingleThreadExecutor().submit(() -> fetchUrl(urlString));
                resultString = fetchStringFuture.get();

                Handler mainThreadHandler = new Handler(Looper.getMainLooper());
                mainThreadHandler.post(() -> whenCompleteRunnable.run(resultString));
            }
            catch (Exception exception)
            {
                Log.e("TAG", "run3 exception: " + exception.getMessage());
            }

        });

    }








    private String fetchUrl(String urlString )
    {
        URL urlObject;
        URLConnection urlConnection;

        InputStream inputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;

        StringBuilder fullResultString = new StringBuilder();
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
                fullResultString.append(currentLineString);
                fullResultString.append(System.lineSeparator());

                currentLineString = bufferedReader.readLine(); //read next line
            }
            bufferedReader.close();

        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return fullResultString.toString();
    }






}
