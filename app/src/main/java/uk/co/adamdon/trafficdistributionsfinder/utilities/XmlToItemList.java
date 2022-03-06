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

import uk.co.adamdon.trafficdistributionsfinder.data.Item;

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





    public ArrayList<Item> parse(String xmlDataString)
    {
        ArrayList<Item> currentItemList = new ArrayList<>();


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

            Item currentItem = null;

            while ((eventType = xmlPullParser.next()) != XmlPullParser.END_DOCUMENT)
            {
                String tagName = xmlPullParser.getName();
//                Log.d("TAG", "parse tag name: " + tagName);

                if (eventType == XmlPullParser.END_TAG && tagName.equalsIgnoreCase("item"))
                {
                    currentItemList.add(currentItem);
                    currentItem = null;
                }

                if (eventType != XmlPullParser.START_TAG)
                {
                    continue;
                }

                if (tagName.equalsIgnoreCase ("item"))
                {
                    currentItem = new Item();
//                    Log.d("XMLParser", "\t\tItem found");
                }

                if (currentItem != null)
                {
                    if (tagName.equalsIgnoreCase("title"))
                    {
                        String text = xmlPullParser.nextText();
                        currentItem.setTitleString(text);
//                        Log.d("parser", "Title found: " + text);
                    }
                    else if (tagName.equalsIgnoreCase("description"))
                    {
                        String text = xmlPullParser.nextText();
                        currentItem.setDescriptionString(text);
//                        Log.d("parser", "Description found: " + text);
                        currentItem.setOriginalDescriptionString(text);
                        if(text.startsWith("Start Date:"))
                        {
                            String startDateString;
                            String endDateString;
                            String contentDescriptionString;

                            startDateString = text.substring((text.lastIndexOf("Start Date: ") + 12), text.lastIndexOf("<br />End Date:")); ;
                            endDateString = text.substring((text.lastIndexOf("End Date: ") + 10), (text.lastIndexOf("00:00") + 5) );
                            contentDescriptionString = text.substring((text.lastIndexOf("00:00") + 5), text.length());
                            contentDescriptionString = contentDescriptionString.replaceAll("<br />", "");
                            contentDescriptionString = contentDescriptionString.replaceAll("\\r\\n|\\r|\\n", " ");
//                            Log.d("parser", "!!!!! startDateString: " + startDateString);
//                            Log.d("parser", "!!!!! endDateString: " + endDateString);
//                            Log.d("parser", "!!!!! contentDescriptionString: " + contentDescriptionString);

                            try
                            {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMMM yyyy - HH:mm", Locale.ENGLISH);
                                Date parsedStartDate = simpleDateFormat.parse(startDateString);
                                Date parsedEndDate = simpleDateFormat.parse(endDateString);

                                currentItem.setStartDate(parsedStartDate);
                                currentItem.setEndDate(parsedEndDate);

//                                Log.d("parser", "!!!!! parsedStartDate: " + parsedStartDate);
                            }
                            catch (Exception exception)
                            {
                                Log.e("parser", "Error could not parse date" + exception.getMessage());
                                exception.printStackTrace();
                            }

                            currentItem.setDescriptionString(contentDescriptionString);
                        }
                        else
                        {
                            currentItem.setDescriptionString(text);

                        }
                    }
                    else if (tagName.equalsIgnoreCase("link"))
                    {
                        String text = xmlPullParser.nextText();
                        currentItem.setLinkString(text);
//                        Log.d("parser", "Link found: " + text);
                    }
                    else if (tagName.equalsIgnoreCase("georss:point"))
                    {
                        String text = xmlPullParser.nextText();

                        currentItem.setGeoPointString(text);
//                        Log.d("parser", "GeoPoint geoPointLatLng: " + geoPointLatLng.toString());
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

                            currentItem.setPunDate(parsedDate);

                            if(!currentItem.getOriginalDescriptionString().startsWith("Start Date:"))
                            {
                                currentItem.setStartDate(parsedDate);
                                currentItem.setEndDate(parsedDate);
                            }
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

        return currentItemList;
    }


}
