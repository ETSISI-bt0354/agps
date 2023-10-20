package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Activity
{
    private String name;
    private String description;
    private int duration;
    private double price;
    private OptionalInt capacity;
    private PriceCalculator priceCalculator;

    public Activity(String name, String description, int duration, Double price, PriceCalculator priceCalculator)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
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

    public void setCapacity(OptionalInt capacity) throws Exception
    {
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new Exception("Capacity must be greater than 0");

        this.capacity = capacity;
    }
}
