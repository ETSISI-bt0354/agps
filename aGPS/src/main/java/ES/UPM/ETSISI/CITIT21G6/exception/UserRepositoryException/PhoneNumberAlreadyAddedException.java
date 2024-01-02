package ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException;

public class PhoneNumberAlreadyAddedException extends Exception
{
    private final String phoneNumber;

    public PhoneNumberAlreadyAddedException(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }
}
