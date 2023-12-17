package ES.UPM.ETSISI.CITIT21G6.service;

import ES.UPM.ETSISI.CITIT21G6.model.Activity;
import ES.UPM.ETSISI.CITIT21G6.service.DiscountCalculator.DiscountCalculatorFactory;

public class ActivityPriceCalculator
{
    public static double calculate(Activity activity, int age)
    {
        double discount = DiscountCalculatorFactory.getDiscountCalculator(activity.getType()).getDiscount(age);
        return activity.getPrice() * discount;
    }
}
