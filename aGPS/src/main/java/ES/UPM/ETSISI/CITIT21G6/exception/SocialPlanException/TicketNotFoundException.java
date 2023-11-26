package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

public class TicketNotFoundException extends Exception
{
    private final String userName;

    public TicketNotFoundException(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
