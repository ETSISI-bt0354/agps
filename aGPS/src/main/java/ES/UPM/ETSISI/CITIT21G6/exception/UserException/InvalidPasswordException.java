package ES.UPM.ETSISI.CITIT21G6.exception.UserException;

public class InvalidPasswordException extends Exception
{
    private final String password;
    private final int minimumPasswordlength;
    public InvalidPasswordException(String password, int minimumPasswordlength)
    {
        super();
        this.password = password;
        this.minimumPasswordlength = minimumPasswordlength;
    }

    public String getPassword()
    {
        return password;
    }

    public int getMinimumPasswordlength()
    {
        return minimumPasswordlength;
    }
}
