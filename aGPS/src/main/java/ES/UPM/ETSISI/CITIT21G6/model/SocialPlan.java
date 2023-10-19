package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.List;

public class SocialPlan
{
    private String name;
    private LocalDate date;
    private String location;
    private OptionalInt capacity;
    private List<Activity> activities;
    private List<Ticket> participants;

    public SocialPlan(String name, LocalDate date, String location)
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

    public LocalDate getDate()
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
        if (capacity.orElse(Integer.MAX_VALUE) <= this.capacity.orElse(Integer.MAX_VALUE))
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The plan's minimal capacity is ");
            errorMessage.append(this.capacity.orElse(Integer.MAX_VALUE));
            errorMessage.append(".");
            throw new Exception(errorMessage.toString());
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

    public Ticket createTicket(User user) throws Exception
    {
        Ticket ticket = new Ticket(user, this);
        if (participants.contains(ticket))
            throw new Exception("The user already has a ticket");
        participants.add(ticket);
        return ticket;
    }

    protected void deleteTicket(Ticket ticket)
    {
        participants.remove(ticket);
    }
}
