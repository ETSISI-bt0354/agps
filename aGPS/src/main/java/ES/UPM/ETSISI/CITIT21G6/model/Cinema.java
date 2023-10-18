package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.*;

public class Cinema extends Activities
{
    private static final int MAXIMUMYOUNGAGE = 21;
    private static final double YOUNGDISCOUNT = 0.5;

    public Cinema(String name, String description, int duration, double cost, OptionalInt capacity)
    {
        super(name, description, duration, cost, capacity);
    }
    public Cinema(String name, String description, int duration, double cost)
    {
        super(name, description, duration, cost);
    }

    /**
     * Getting the Cinema price
     * @param age
     * @return price
     */

    public double getPrice(int age)
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
