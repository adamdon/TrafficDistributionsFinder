package uk.co.adamdon.trafficdistributionsfinder.utilities;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import uk.co.adamdon.trafficdistributionsfinder.models.ItemModel;

public class XmlToItemList
{
    private static XmlToItemList INSTANCE;


    private XmlToItemList()
    {

    }

    public static XmlToItemList getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new XmlToItemList();
        }
        return INSTANCE;
    }





    public ArrayList<ItemModel> parse(String xmlDataString)
    {
        ArrayList<ItemModel> currentIncidentList = new ArrayList<>();


        try
        {
            XmlPullParserFactory xmlPullParserFactory;
            XmlPullParser xmlPullParser;
            String xmlDataCleanString;


            xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(false);

            xmlPullParser = xmlPullParserFactory.newPullParser();

//            xmlDataCleanString = xmlDataString.replace("null", "");
//            xmlDataCleanString = xmlDataString.replace("georss:point", "georsspoint");
            xmlPullParser.setInput(new StringReader(xmlDataString));
            int eventType;

            ItemModel currentIncident = null;

            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT)
            {
                String tagName = xmlPullParser.getName();
//                Log.d("TAG", "parse tag name: " + tagName);

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
                    currentIncident = new ItemModel();
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
                        //TODO check if starts with "start date"
                        //TODO if note set start+end date to pubDate
                        //TODO parse start and end date to model fields
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
                    else if (tagName.equalsIgnoreCase("georss:point"))
                    {
                        String text = xmlPullParser.nextText();
                        currentIncident.setGeoPointString(text);
//                        Log.d("parser", "GeoPoint Found: " + text);
                    }

                    else if (tagName.equalsIgnoreCase("pubDate"))
                    {
                        String text = xmlPullParser.nextText();
//                        Log.d("parser", "Date found: " + text);
                        try
                        {
                            String formatString = "EEE, d MMM yyyy HH:mm:ss z";
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatString, Locale.ENGLISH);
                            Date parsedDate = simpleDateFormat.parse(text);

                            currentIncident.setPunDate(parsedDate);
                        }
                        catch (Exception exception)
                        {
                            Log.e("parser", "Error, Date found: " + text);
                            exception.printStackTrace();
                        }
                    }
                }
            }
        } catch(XmlPullParserException | IOException exception)
        {
            Log.d("parser", "XML could not be parsed: " + exception.getMessage());
        }

        return currentIncidentList;
    }


}
