package ES.UPM.ETSISI.CITIT21G6;

import java.util.Date;
import java.util.OptionalInt;

public class SocialPlan
{
    private String name;
    private Date date;
    private String location;
    private OptionalInt capacity;

    public SocialPlan(String name, Date date, String location, int capacity)
    {
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = OptionalInt.of(capacity);
    }

    public SocialPlan(String name, Date date, String location)
    {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getName()
    {
        return name;
    }

    public Date getDate()
    {
        return date;
    }

    public String getLocation()
    {
        return location;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public void addActivity(Activities activity)
    {
        
    }
}
