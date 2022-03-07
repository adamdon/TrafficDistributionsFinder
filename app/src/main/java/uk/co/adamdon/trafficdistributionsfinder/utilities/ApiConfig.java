package uk.co.adamdon.trafficdistributionsfinder.utilities;

import java.util.ArrayList;

public class ApiConfig
{
    public static final String CURRENT_INCIDENTS_URL = "https://trafficscotland.org/rss/feeds/currentincidents.aspx";
    public static final String ROADWORKS_URL = "https://trafficscotland.org/rss/feeds/roadworks.aspx";
    public static final String PLANNED_ROADWORKS_URL = "https://trafficscotland.org/rss/feeds/plannedroadworks.aspx";

    public static ArrayList<String> getApiStringList()
    {
        ArrayList<String> apiStringList;

        apiStringList = new ArrayList<>();
        apiStringList.add(CURRENT_INCIDENTS_URL);
        apiStringList.add(ROADWORKS_URL);
        apiStringList.add(PLANNED_ROADWORKS_URL);

        return apiStringList;
    }
}
