package ES.UPM.ETSISI.CITIT21G6.model;


import java.util.OptionalInt;

public class Theater extends Activities
{
    private static final int MAXIMUMYOUNGAGE = 25;
    private static final int MINIMUMELDERLYAGE = 65;
    private static final double YOUNGDISCOUNT = 0.5;
    private static final double ELDERLYDISCOUNT = 0.7;

    public Theater(String name, String description, int duration, double cost, OptionalInt capacity)
    {
        super(name, description, duration, cost, capacity);
    }
    public Theater(String name, String description, int duration, double cost)
    {
        super(name, description, duration, cost);
    }

    /**
     * Getting the Theatre price
     * @param age
     * @return
     */
    public double getPrice(int age)
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
