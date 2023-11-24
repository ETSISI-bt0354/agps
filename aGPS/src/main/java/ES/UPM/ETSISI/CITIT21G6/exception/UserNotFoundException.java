package ES.UPM.ETSISI.CITIT21G6.exception;

public class UserNotFoundException extends Exception
{
    private String userName;

    public UserNotFoundException(String userName)
    {
        super();
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
