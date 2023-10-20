package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Activity
{
    private String name;
    private String description;
    private int duration;
    private OptionalInt capacity;
    private double price;
    private PriceCalculator priceCalculator;

    public Activity(String name, String description, int duration, double price, PriceCalculator priceCalculator)
    {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.capacity = OptionalInt.empty();
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

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public void setCapacity(OptionalInt capacity) throws Exception
    {
        if (capacity.isPresent() && capacity.getAsInt() <= 0)
            throw new Exception("Capacity must be greater than 0.");

        this.capacity = capacity;
    }

    public double getPrice()
    {
        return price;
    }

    public double calculatePrice(int age)
    {
        return priceCalculator.calculatePrice(price, age);
    }
}
