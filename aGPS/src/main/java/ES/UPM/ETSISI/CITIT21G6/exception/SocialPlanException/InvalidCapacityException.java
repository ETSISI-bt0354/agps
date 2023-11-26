package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

import java.util.OptionalInt;

public class InvalidCapacityException extends Exception
{
    private final InvalidCapacity reason;
    private final OptionalInt capacity;
    private final OptionalInt problemCapacity;

    public InvalidCapacityException(InvalidCapacity reason, OptionalInt capacity, OptionalInt minimumCapacity)
    {
        this.reason = reason;
        this.capacity = capacity;
        this.problemCapacity = minimumCapacity;
    }

    public InvalidCapacity getReason()
    {
        return reason;
    }

    public OptionalInt getCapacity()
    {
        return capacity;
    }

    public OptionalInt getProblemCapacity()
    {
        return problemCapacity;
    }
}
