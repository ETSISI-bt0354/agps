package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

import java.util.OptionalInt;

public class InvalidPriceException extends Exception
{
    private final double price;

    public InvalidPriceException(double price)
    {
        this.price = price;
    }

    public double getPrice()
    {
        return price;
    }
}
