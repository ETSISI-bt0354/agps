package ES.UPM.ETSISI.CITIT21G6;

import java.util.*;

public class Theater extends Activities
{
    private static final int MAXIMUMYOUNGAGE = 25;
    private static final int MINIMUMELDERLYAGE = 65;
    private static final Double YOUNGDISCOUNT = 0.5;
    private static final Double ELDERLYDISCOUNT = 0.7;

    public Theater(String name, String description, int duration, Double cost, OptionalInt capacity)
    {
        super(name, description, duration, cost, capacity);
    }
    public Theater(String name, String description, int duration, Double cost)
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
