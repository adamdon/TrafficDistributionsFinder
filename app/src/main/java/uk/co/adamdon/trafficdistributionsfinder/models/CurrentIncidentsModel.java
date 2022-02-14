package uk.co.adamdon.trafficdistributionsfinder.models;

import java.time.LocalDate;

public class CurrentIncidentsModel
{
    private String titleString;
    private String descriptionString;
    private String linkString;
    private String authorString;
    private String commentsString;
    private LocalDate punDateLocalDate;

    public CurrentIncidentsModel(String titleString, String descriptionString, String linkString, String authorString, String commentsString, LocalDate punDateLocalDate)
    {
        this.titleString = titleString;
        this.descriptionString = descriptionString;
        this.linkString = linkString;
        this.authorString = authorString;
        this.commentsString = commentsString;
        this.punDateLocalDate = punDateLocalDate;
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

    public LocalDate getPunDateLocalDate()
    {
        return punDateLocalDate;
    }


    //    <title>M8 Jct 15 - Jct 18 - Planned Roadworks</title>
//    <description>The M8 both Eastbound and Westbound between Junctions 15 and Junction 18 is currently restricted due to essential bridge repairs.  Motorists are advised to expect delays in the area.</description>
//      <link>http://tscot.org/01a13134</link>
//      <georss:point>55.8692771239109 -4.24135563418866</georss:point>
//      <author />
//      <comments />
//      <pubDate>Fri, 12 Mar 2021 20:53:52 GMT</pubDate>

}
