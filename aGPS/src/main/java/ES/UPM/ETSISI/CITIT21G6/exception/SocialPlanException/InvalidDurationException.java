package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

public class InvalidDurationException extends Exception
{
    private final int duration;

    public InvalidDurationException(int duration)
    {
        this.duration = duration;
    }

    public int getDuration()
    {
        return duration;
    }
}
