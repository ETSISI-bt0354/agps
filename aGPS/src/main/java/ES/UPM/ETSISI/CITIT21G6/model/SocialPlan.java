package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.OptionalInt;
import java.util.List;

public class SocialPlan
{
    private String name;
    private Date date;
    private String location;
    private OptionalInt capacity;
    private List<Activity> activities;
    private List<Ticket> participants;

    public SocialPlan(String name, Date date, String location)
    {
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = OptionalInt.empty();
        this.activities = new ArrayList<>();
        this.participants = new ArrayList<>();
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

    public void setCapacity(OptionalInt capacity) throws Exception
    {
        for (Activity activity: activities)
        {
            if (capacity.orElse(Integer.MAX_VALUE) >= activity.getCapacity().orElse(Integer.MAX_VALUE))
            {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("The plan's minimal capacity is ");
                errorMessage.append(activity.getCapacity().orElse(Integer.MAX_VALUE));
                errorMessage.append(".");
                throw new Exception(errorMessage.toString());
            }
        }

        this.capacity = capacity;
    }

    public void addActivity(Activity activity) throws Exception
    {
        if (!activities.contains(activity))
        {
            activities.add(activity);
            if (activity.getCapacity().orElse(Integer.MAX_VALUE) < capacity.orElse(Integer.MAX_VALUE))
                capacity = activity.getCapacity();
        }
        else
        {
            throw new Exception("The activity is already added!");
        }
    }

    public List<Ticket> getParticipants()
    {
        return participants;
    }
}
