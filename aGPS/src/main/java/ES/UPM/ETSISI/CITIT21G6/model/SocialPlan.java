package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class SocialPlan
{
    private final SocialPlanId id;
    private LocalDate date;
    private String location;
    private OptionalInt capacity;
    private List<Activity> activities;
    private List<Ticket> participants;

    public SocialPlan(String ownerName, String name, LocalDate date, String location)
    {
        this.id = new SocialPlanId(ownerName, name);
        this.date = date;
        this.location = location;
        this.capacity = OptionalInt.empty();
        this.activities = new ArrayList<>();
        this.participants = new ArrayList<>();
    }

    public SocialPlanId getId()
    {
        return id;
    }

    public String getOwnerName()
    {
        return id.ownerName();
    }

    public String getName()
    {
        return id.name();
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
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new Exception("Capacity must be greater than 0.");

        int numberOfParticipants = participants.size();

        if (capacity.orElse(Integer.MAX_VALUE) < numberOfParticipants)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The minimum plan capacity is ");
            errorMessage.append(this.capacity.orElse(Integer.MAX_VALUE));
            errorMessage.append(".");
            throw new Exception(errorMessage.toString());
        }

        OptionalInt minimumPossibleCapacity = activities.stream()
                .map(Activity::getCapacity)
                .flatMapToInt(OptionalInt::stream)
                .min();

        if (capacity.orElse(Integer.MAX_VALUE) > minimumPossibleCapacity.orElse(Integer.MAX_VALUE))
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The maximum plan capacity is ");
            errorMessage.append(this.capacity.orElse(Integer.MAX_VALUE));
            errorMessage.append(".");
            throw new Exception(errorMessage.toString());
        }

        this.capacity = capacity;
    }

    public void addActivity(Activity activity) throws Exception
    {
        if (activities.contains(activity))
            throw new Exception("The activity is already added.");

        if (activity.getCapacity().orElse(Integer.MAX_VALUE) < participants.size())
            throw new Exception("There are too many user on the plan for this activity.");

        activities.add(activity);
        if (activity.getCapacity().orElse(Integer.MAX_VALUE) < capacity.orElse(Integer.MAX_VALUE))
            capacity = activity.getCapacity();
    }

    public List<Activity> getActivities()
    {
        return activities;
    }

    public void addParticipant(Ticket ticket) throws Exception
    {
        if (participants.contains(ticket))
            throw new Exception("The user is already on the social plan.");

        if (capacity.isPresent() && participants.size() == capacity.getAsInt())
            throw new Exception("The social plan is full.");

        participants.add(ticket);
    }

    public List<Ticket> getParticipants()
    {
        return participants;
    }

    public void removeParticipant(Ticket ticket)
    {
        participants.remove(ticket);
    }
}
