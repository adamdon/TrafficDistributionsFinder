package uk.co.adamdon.trafficdistributionsfinder.models;

import java.util.UUID;

public class AbstractModel
{
    UUID uuid;

    public AbstractModel()
    {
        uuid = UUID.randomUUID();
    }

    public UUID getUuid()
    {
        return uuid;
    }
}
