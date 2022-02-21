package uk.co.adamdon.trafficdistributionsfinder.models;

import java.time.LocalDate;
import java.util.Date;

public class CurrentIncidentModel
{
    private String titleString;
    private String descriptionString;
    private String linkString;
    private String authorString;
    private String commentsString;
    private Date punDate;

    public CurrentIncidentModel(String titleString, String descriptionString, String linkString, String authorString, String commentsString, Date punDate)
    {
        this.titleString = titleString;
        this.descriptionString = descriptionString;
        this.linkString = linkString;
        this.authorString = authorString;
        this.commentsString = commentsString;
        this.punDate = punDate;
    }

    public CurrentIncidentModel()
    {

    }

    public String getTitleString()
    {
        return titleString;
    }

    public String getDescriptionString()
    {
        return descriptionString;
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

    @Override
    public String toString()
    {
        return "CurrentIncidentModel{" +
                "titleString='" + titleString + '\'' +
                ", descriptionString='" + descriptionString + '\'' +
                ", linkString='" + linkString + '\'' +
                ", authorString='" + authorString + '\'' +
                ", commentsString='" + commentsString + '\'' +
                ", punDate=" + punDate +
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
