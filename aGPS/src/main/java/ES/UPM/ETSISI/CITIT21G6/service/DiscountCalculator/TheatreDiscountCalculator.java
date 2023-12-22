package ES.UPM.ETSISI.CITIT21G6.service.DiscountCalculator;

import ES.UPM.ETSISI.CITIT21G6.service.DiscountCalculator.DiscountCalculator;

public class TheatreDiscountCalculator implements DiscountCalculator
{
    private static final int YOUNG_AGE_THEATRE = 25;
    private static final double YOUNG_AGE_DISCOUNT_THEATRE = 0.5;
    private static final int OLD_AGE_THEATRE = 65;
    private static final double OLD_AGE_DISCOUNT_THEATRE = 0.3;

    @Override
    public double getDiscount(int age)
    {
       if (age <= YOUNG_AGE_THEATRE)
           return YOUNG_AGE_DISCOUNT_THEATRE;

       if (age >= OLD_AGE_THEATRE)
           return OLD_AGE_DISCOUNT_THEATRE;

       return 1;
    }
}
