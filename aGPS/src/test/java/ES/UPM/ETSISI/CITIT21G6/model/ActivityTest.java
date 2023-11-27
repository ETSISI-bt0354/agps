package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacity;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacityException;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ActivityTest
{
    @Test
    void setNegativeCapacity()
    {
        Activity activity = new Activity("A", "a", 1, 2, null);
        assertThrows(InvalidCapacityException.class, () -> activity.setCapacity(OptionalInt.of(-5)));
    }

    @Test
    void setZeroCapacity()
    {
        Activity activity = new Activity("A", "a", 1, 2, null);
        assertThrows(InvalidCapacityException.class, () -> activity.setCapacity(OptionalInt.of(0)));
    }
}