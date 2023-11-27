package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacity;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacityException;

import java.util.OptionalInt;

public class Activity
{
    private String name;
    private String description;
    private int duration;
    private OptionalInt capacity;
    private double price;
    private ActivityType type;

    public Activity(String name, String description, int duration, double price, ActivityType type)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.capacity = OptionalInt.empty();
        this.price = price;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public int getDuration()
    {
        return duration;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public void setCapacity(OptionalInt capacity) throws InvalidCapacityException
    {
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new InvalidCapacityException(InvalidCapacity.NEGATIVEORZERO, capacity, OptionalInt.of(Integer.MAX_VALUE));

        this.capacity = capacity;
    }

    public double getPrice()
    {
        return price;
    }

    public ActivityType getType()
    {
        return type;
    }
}
