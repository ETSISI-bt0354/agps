package ES.UPM.ETSISI.CITIT21G6.exception.UserException;

public class InvalidAgeException extends Exception
{
    private final InvalidAge reason;
    private final int problemAge;
    private final int currentAge;

    public InvalidAgeException(InvalidAge reason, int problemAge, int currentAge)
    {
        this.reason = reason;
        this.problemAge = problemAge;
	this.currentAge = currentAge;
    }

    public InvalidAge getReason()
    {
        return reason;
    }

    public int getProblemAge()
    {
        return problemAge;
    }

    public int getCurrentAge()
    {
	return currentAge;
    }
}
