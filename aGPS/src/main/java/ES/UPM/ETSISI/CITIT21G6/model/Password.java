package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;

public class Password
{
    private static final int MINIMUM_PASSWORD_LENGTH = 3;
    private final String password;

    public Password(String password) throws InvalidPasswordException
    {
        if (password.length() < MINIMUM_PASSWORD_LENGTH)
            throw new InvalidPasswordException(password, MINIMUM_PASSWORD_LENGTH);

        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}
