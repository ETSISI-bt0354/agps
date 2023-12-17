package ES.UPM.ETSISI.CITIT21G6.service.DiscountCalculator;

public class CinemaDiscountCalculator implements DiscountCalculator
{
    private static final int YOUNG_AGE_CINEMA = 21;
    private static final double YOUNG_AGE_DISCOUNT_CINEMA = 0.5;

    @Override
    public double getDiscount(int age)
    {
        if (age <= YOUNG_AGE_CINEMA)
            return YOUNG_AGE_DISCOUNT_CINEMA;

        return 1;
    }
}
