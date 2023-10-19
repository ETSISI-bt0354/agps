package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Activity
{
    private String name;
    private String description;
    private int duration;
    private double cost;
    private OptionalInt capacity;
    private PriceCalculator priceCalculator;

    public Activity(String name, String description, int duration, Double basePrice, PriceCalculator priceCalculator)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.cost = basePrice;
        this.priceCalculator = priceCalculator;
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

    public double getPrice(int age)
    {
        return priceCalculator.calculatePrice(age);
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public void setCapacity(OptionalInt capacity)
    {
        this.capacity = capacity;
    }
}
