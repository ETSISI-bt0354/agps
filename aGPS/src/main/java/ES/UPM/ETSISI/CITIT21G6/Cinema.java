package ES.UPM.ETSISI.CITIT21G6;

import java.util.*;

public class Cinema extends Activities
{
    private final int MAXIMUMYOUNGAGE = 21;
    private final Double YOUNGDISCOUNT = 0.5;

    public Cinema(String name, String description, int duration, Double cost, OptionalInt capacity)
    {
        super(name, description, duration, cost, capacity);
    }
    public Cinema(String name, String description, int duration, Double cost)
    {
        super(name, description, duration, cost);
    }

    /**
     * Getting the Cinema price
     * @param age
     * @return price
     */

    public Double getPrice(int age)
    {
        if (age < MAXIMUMYOUNGAGE)
        {
            return super.getPrice()-(super.getPrice() * YOUNGDISCOUNT);
        }
        else
        {
            return super.getPrice();
        }
    }
}
