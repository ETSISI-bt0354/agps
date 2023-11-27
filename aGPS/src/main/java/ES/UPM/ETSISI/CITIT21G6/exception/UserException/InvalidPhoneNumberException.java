package ES.UPM.ETSISI.CITIT21G6.exception.UserException;

public class InvalidPhoneNumberException extends Exception
{
    private final String phoneNumber;

    public InvalidPhoneNumberException(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
