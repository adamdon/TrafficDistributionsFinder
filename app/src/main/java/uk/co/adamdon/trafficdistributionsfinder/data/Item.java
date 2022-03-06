package uk.co.adamdon.trafficdistributionsfinder.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.UUID;

@Entity
public class Item
{
    private String titleString;
    private String descriptionString;
    private String originalDescriptionString;
    private String linkString;
    private String authorString;
    private String commentsString;
    private String geoPointString;
//    private LatLng geoPointLatLng;
    private Date punDate;
    private Date startDate;
    private Date endDate;

    @PrimaryKey
    @NonNull
    private UUID uuid;

//    public Item(String titleString, String descriptionString, String originalDescriptionString, String linkString, String authorString, String commentsString, String geoPointString, Date punDate, Date startDate, Date endDate)
//    {
//        this.titleString = titleString;
//        this.descriptionString = descriptionString;
//        this.originalDescriptionString = originalDescriptionString;
//        this.linkString = linkString;
//        this.authorString = authorString;
//        this.commentsString = commentsString;
//        this.geoPointString = geoPointString;
//        this.punDate = punDate;
//        this.startDate = startDate;
//        this.endDate = endDate;

//    uuid = UUID.randomUUID();

//    }

    public Item()
    {
        uuid = UUID.randomUUID();

    }





    public LatLng getGeoPointLatLng()
    {
        String textString;
        String[] splitStringArray;
        double latDouble;
        double LagDouble;
        LatLng geoPointLatLng;


        textString = geoPointString;
        splitStringArray = textString.split(" ");
        latDouble = Double.parseDouble(splitStringArray[0]);
        LagDouble = Double.parseDouble(splitStringArray[1]);
        geoPointLatLng = new LatLng(latDouble, LagDouble);


        return geoPointLatLng;
    }





    public String getDescriptionPreViewString(int numberOfCharsInt)
    {
        String descriptionPreviewString = "";

        if(descriptionString.length() <= numberOfCharsInt)
        {
            descriptionPreviewString = descriptionString;
        }
        else
        {
            descriptionPreviewString = descriptionString.substring(0, numberOfCharsInt);
        }

        descriptionPreviewString += "...";

        return descriptionPreviewString;
    }



    public String getTitleString()
    {
        return titleString;
    }

    public String getDescriptionString()
    {
        return descriptionString;
    }

    public String getLinkString()
    {
        return linkString;
    }

    public String getAuthorString()
    {
        return authorString;
    }

    public String getCommentsString()
    {
        return commentsString;
    }

    public Date getPunDate()
    {
        return punDate;
    }

    public String getGeoPointString()
    {
        return geoPointString;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public String getOriginalDescriptionString()
    {
        return originalDescriptionString;
    }

    @NonNull
    public UUID getUuid()
    {
        return uuid;
    }

    public void setTitleString(String titleString)
    {
        this.titleString = titleString;
    }

    public void setDescriptionString(String descriptionString)
    {
        this.descriptionString = descriptionString;
    }

    public void setLinkString(String linkString)
    {
        this.linkString = linkString;
    }

    public void setAuthorString(String authorString)
    {
        this.authorString = authorString;
    }

    public void setCommentsString(String commentsString)
    {
        this.commentsString = commentsString;
    }

    public void setPunDate(Date punDate)
    {
        this.punDate = punDate;
    }

    public void setGeoPointString(String geoPointString)
    {
        this.geoPointString = geoPointString;
    }

//    public void setGeoPointLatLng(LatLng geoPointLatLng)
//    {
//        this.geoPointLatLng = geoPointLatLng;
//    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setOriginalDescriptionString(String originalDescriptionString)
    {
        this.originalDescriptionString = originalDescriptionString;
    }

    public void setUuid(@NonNull UUID uuid)
    {
        this.uuid = uuid;
    }

    @Override
    public String toString()
    {
        return "ItemModel{" +
                "titleString='" + titleString + '\'' +
                ", descriptionString='" + descriptionString + '\'' +
                ", linkString='" + linkString + '\'' +
                ", authorString='" + authorString + '\'' +
                ", commentsString='" + commentsString + '\'' +
                ", geoPointString='" + geoPointString + '\'' +
                ", punDate=" + punDate +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }


    //    <title>M8 Jct 15 - Jct 18 - Planned Roadworks</title>
//    <description>The M8 both Eastbound and Westbound between Junctions 15 and Junction 18 is currently restricted due to essential bridge repairs.  Motorists are advised to expect delays in the area.</description>
//      <link>http://tscot.org/01a13134</link>
//      <georss:point>55.8692771239109 -4.24135563418866</georss:point>
//      <author />
//      <comments />
//      <pubDate>Fri, 12 Mar 2021 20:53:52 GMT</pubDate>

}
