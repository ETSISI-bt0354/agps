package ES.UPM.ETSISI.CITIT21G6.exception.UserException;

public class InvalidAgeException extends Exception
{
    private final InvalidAge reason;
    private final int problemAge;

    public InvalidAgeException(InvalidAge reason, int problemAge)
    {
        this.reason = reason;
        this.problemAge = problemAge;
    }

    public InvalidAge getReason()
    {
        return reason;
    }

    public int getProblemAge()
    {
        return problemAge;
    }
}
