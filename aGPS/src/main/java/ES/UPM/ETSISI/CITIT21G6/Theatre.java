package ES.UPM.ETSISI.CITIT21G6;

import java.util.*;

public class Theatre extends Activities
{
    private final int MAXIMUMYOUNGAGE = 25;
    private final int MINIMUMELDERLYAGE = 65;
    private final Double YOUNGDISCOUNT = 0.5;
    private final Double ELDERLYDISCOUNT = 0.7;

    public Theatre(String name, String description, int duration, Double cost, OptionalInt capacity)
    {
        super(name, description, duration, cost, capacity);
    }
    public Theatre(String name, String description, int duration, Double cost)
    {
        super(name, description, duration, cost);
    }

    /**
     * Getting the Theatre price
     * @param age
     * @return
     */
    public Double getPrice(int age)
    {
        if (age < MAXIMUMYOUNGAGE)
        {
            return super.getPrice()-(super.getPrice() * YOUNGDISCOUNT);
        }
        else if (age > MINIMUMELDERLYAGE)
        {
            return super.getPrice()-(super.getPrice() * ELDERLYDISCOUNT);
        }
        else
        {
            return super.getPrice();
        }
    }
}
