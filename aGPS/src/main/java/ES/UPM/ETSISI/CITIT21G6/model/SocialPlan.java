package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class SocialPlan
{
    private final SocialPlanId id;
    private LocalDateTime date;
    private String location;
    private OptionalInt capacity;
    private List<Activity> activities;
    private List<Ticket> participants;

    public SocialPlan(String ownerName, String name, LocalDateTime date, OptionalInt capacity, String location, Clock clock) throws PastDateException, InvalidCapacityException
    {
        this.id = new SocialPlanId(ownerName, name);
        if (date.isBefore(LocalDateTime.now(clock)))
            throw new PastDateException(date);
        this.date = date;
        this.location = location;
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new InvalidCapacityException(InvalidCapacity.NEGATIVEORZERO, capacity, this.capacity);
        this.capacity = capacity;
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

    public LocalDateTime getDate()
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
            throw new InvalidCapacityException(InvalidCapacity.NEGATIVEORZERO, capacity, this.capacity);

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

    public void addParticipant(String participantName) throws FullSocialPlanException, ParticipantAlreadyInSocialPlanException
    {
        Ticket ticket = new Ticket(participantName);
        if (participants.contains(ticket))
            throw new ParticipantAlreadyInSocialPlanException(participantName);

        if (capacity.isPresent() && participants.size() == capacity.getAsInt())
            throw new FullSocialPlanException();

        participants.add(ticket);
    }

    public List<Ticket> getParticipants()
    {
        return participants;
    }

    public void removeParticipant(String participanName) throws ParticipantNotFoundException
    {
        if (!participants.remove(new Ticket(participanName)))
            throw new ParticipantNotFoundException(participanName);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialPlan that = (SocialPlan) o;

        return getId().equals(that.getId());
    }

    @Override
    public int hashCode()
    {
        return getId().hashCode();
    }
}
