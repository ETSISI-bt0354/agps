package ES.UPM.ETSISI.CITIT21G6.service.DiscountCalculator;

import ES.UPM.ETSISI.CITIT21G6.model.ActivityType;

public class DiscountCalculatorFactory
{
    public static DiscountCalculator getDiscountCalculator(ActivityType activityType)
    {
        return switch (activityType)
        {
            case GENERIC -> new GenericDiscountCalculator();
            case CINEMA -> new CinemaDiscountCalculator();
            case THEATRE -> new TheatreDiscountCalculator();
        };
    }
}
