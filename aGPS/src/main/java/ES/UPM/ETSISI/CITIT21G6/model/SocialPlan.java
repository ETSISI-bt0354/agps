package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;

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

    public void setCapacity(OptionalInt capacity) throws InvalidCapacityException
    {
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new InvalidCapacityException(InvalidCapacity.NEGATIVE, capacity, this.capacity);

        int numberOfParticipants = participants.size();

        if (capacity.orElse(Integer.MAX_VALUE) < numberOfParticipants)
            throw  new InvalidCapacityException(InvalidCapacity.TOOSMALL, capacity, this.capacity);

        OptionalInt minimumPossibleCapacity = activities.stream()
                .map(Activity::getCapacity)
                .flatMapToInt(OptionalInt::stream)
                .min();

        if (capacity.orElse(Integer.MAX_VALUE) > minimumPossibleCapacity.orElse(Integer.MAX_VALUE))
            throw new InvalidCapacityException(InvalidCapacity.TOOBIG, capacity, minimumPossibleCapacity);

        this.capacity = capacity;
    }

    public void addActivity(Activity activity) throws InvalidCapacityException, ActivityAlreadyInSocialPlanException
    {
        if (activities.contains(activity))
            throw new ActivityAlreadyInSocialPlanException(activity);

        if (activity.getCapacity().orElse(Integer.MAX_VALUE) < participants.size())
            throw new InvalidCapacityException(InvalidCapacity.TOOSMALL, activity.getCapacity(), OptionalInt.of(participants.size()));

        activities.add(activity);
        if (activity.getCapacity().orElse(Integer.MAX_VALUE) < capacity.orElse(Integer.MAX_VALUE))
            capacity = activity.getCapacity();
    }

    public List<Activity> getActivities()
    {
        return activities;
    }

    public void addParticipant(Ticket ticket) throws FullSocialPlanException, UserAlreadyInSocialPlanException
    {
        if (participants.contains(ticket))
            throw new UserAlreadyInSocialPlanException(ticket.getUserName());

        if (capacity.isPresent() && participants.size() == capacity.getAsInt())
            throw new FullSocialPlanException();

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
