package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacityException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidDurationException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidPriceException;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ActivityTest
{
    @Test
    void setNegativeCapacity() throws InvalidCapacityException, InvalidDurationException, InvalidPriceException
    {
        assertThrows(InvalidCapacityException.class, () -> new Activity("A", "a", 1, OptionalInt.of(-5), 2, null));
        Activity activity = new Activity("A", "a", 1, OptionalInt.empty(), 2, null);
        assertThrows(InvalidCapacityException.class, () -> activity.setCapacity(OptionalInt.of(-5)));
    }

    @Test
    void setZeroCapacity() throws InvalidCapacityException, InvalidDurationException, InvalidPriceException
    {
        assertThrows(InvalidCapacityException.class, () -> new Activity("A", "a", 1, OptionalInt.of(0), 2, null));
        Activity activity = new Activity("A", "a", 1, OptionalInt.empty(), 2, null);
        assertThrows(InvalidCapacityException.class, () -> activity.setCapacity(OptionalInt.of(0)));
    }
}