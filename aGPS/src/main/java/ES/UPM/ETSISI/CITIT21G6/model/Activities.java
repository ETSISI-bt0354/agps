package ES.UPM.ETSISI.CITIT21G6;

import java.util.*;

public class Activities
{
    private String name;
    private String description;
    private int duration;
    private Double cost;
    private OptionalInt capacity;

    public Activities(String name, String description, int duration, Double cost, OptionalInt capacity)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
        this.capacity = capacity;
    }
    public Activities(String name, String description, int duration, Double cost)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
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

    public Double getPrice()
    {
        return cost;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }
}
