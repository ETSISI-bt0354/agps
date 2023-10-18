package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.*;

public class Activities
{
    private String name;
    private String description;
    private int duration;
    private double cost;
    private OptionalInt capacity;

    public Activities(String name, String description, int duration, double cost, OptionalInt capacity)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = cost;
        this.capacity = capacity;
    }
    public Activities(String name, String description, int duration, double cost)
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

    public double getPrice()
    {
        return cost;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }
}
