package uk.co.adamdon.trafficdistributionsfinder.parsers;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uk.co.adamdon.trafficdistributionsfinder.models.CurrentIncidentModel;

public class XmlToCurrentInstancesList
{
    private static XmlToCurrentInstancesList INSTANCE;


    private XmlToCurrentInstancesList()
    {

    }

    public static XmlToCurrentInstancesList getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new XmlToCurrentInstancesList();
        }
        return INSTANCE;
    }





    public ArrayList<CurrentIncidentModel> parse(String xmlDataString)
    {
        ArrayList<CurrentIncidentModel> currentIncidentList = new ArrayList<>();


        try
        {
            XmlPullParserFactory xmlPullParserFactory;
            XmlPullParser xmlPullParser;
            String xmlDataCleanString;


            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);

            xmlPullParser = xmlPullParserFactory.newPullParser();

            xmlDataCleanString = xmlDataString.replace("null", "");
            xmlPullParser.setInput(new StringReader(xmlDataCleanString));
            int eventType;

            CurrentIncidentModel currentIncident = null;

            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT)
            {
                String tagName = xmlPullParser.getName();

                if (eventType == XmlPullParser.END_TAG && tagName.equalsIgnoreCase("item"))
                {
                    currentIncidentList.add(currentIncident);
                    currentIncident = null;
                }

                if (eventType != XmlPullParser.START_TAG)
                {
                    continue;
                }

                if (tagName.equalsIgnoreCase ("item"))
                {
                    currentIncident = new CurrentIncidentModel();
//                    Log.d("XMLParser", "\t\tItem found");
                }

                if (currentIncident != null)
                {
                    if (tagName.equalsIgnoreCase("title"))
                    {
                        String text = xmlPullParser.nextText();
                        currentIncident.setTitleString(text);
//                        Log.d("parser", "Title found: " + text);
                    }
                    else if (tagName.equalsIgnoreCase("description"))
                    {
                        String text = xmlPullParser.nextText();
                        currentIncident.setDescriptionString(text);
//                        Log.d("parser", "Description found: " + text);
                    }
                    else if (tagName.equalsIgnoreCase("link"))
                    {
                        String text = xmlPullParser.nextText();
                        currentIncident.setLinkString(text);
//                        Log.d("parser", "Link found: " + text);
                    }
//                    else if (tagName.equalsIgnoreCase("pubDate"))
//                    {
//                        String text = xmlPullParser.nextText();
////                        LocalDate localDate = LocalDate.parse(text);
//                        currentIncident.setPunDateLocalDate();
//                        Log.d("parser", "Date found: " + text);
//                    }
                }
            }
        } catch(XmlPullParserException | IOException exception)
        {
            Log.d("parser", "XML could not be parsed: " + exception.getMessage());
        }

        return currentIncidentList;
    }


}
