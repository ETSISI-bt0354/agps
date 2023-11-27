package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.Activity;

public class ActivityPriceCalculator
{
    private static final int YOUNG_AGE_THEATRE = 25;
    private static final double YOUNG_AGE_DISCOUNT_THEATRE = 0.5;
    private static final int OLD_AGE_THEATRE = 65;
    private static final double OLD_AGE_DISCOUNT_THEATRE = 0.3;
    private static final int YOUNG_AGE_CINEMA = 21;
    private static final double YOUNG_AGE_DISCOUNT_CINEMA = 0.5;

    public static double calculate(Activity activity, int age)
    {
        double discount = 0;
        double price = activity.getPrice();

        switch (activity.getType())
        {
            case CINEMA ->
            {
                if (age <= YOUNG_AGE_CINEMA) discount = YOUNG_AGE_DISCOUNT_CINEMA;
                else discount = 1;
            }
            case THEATRE ->
            {
                if (age <= YOUNG_AGE_THEATRE) discount = YOUNG_AGE_DISCOUNT_THEATRE;
                else if (age >= OLD_AGE_THEATRE) discount = OLD_AGE_DISCOUNT_THEATRE;
                else discount = 1;
            }
            case GENERIC -> discount = 1;
        }

        return activity.getPrice() * discount;
    }
}
