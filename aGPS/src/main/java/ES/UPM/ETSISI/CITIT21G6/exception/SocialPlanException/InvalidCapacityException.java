package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

import java.util.OptionalInt;

public class InvalidCapacityException extends Exception
{
    private final InvalidCapacity reason;
    private final OptionalInt capacity;
    private final OptionalInt minimumCapacity;

    public InvalidCapacityException(InvalidCapacity reason, OptionalInt capacity, OptionalInt minimumCapacity)
    {
        this.reason = reason;
        this.capacity = capacity;
        this.minimumCapacity = minimumCapacity;
    }

    public InvalidCapacity getReason()
    {
        return reason;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public OptionalInt getMinimumCapacity()
    {
        return minimumCapacity;
    }
}
