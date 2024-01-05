package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;

public class Password
{
    private static final int MINIMUM_PASSWORD_LENGTH = 3;
    private final String password;

    public Password(String password) throws InvalidPasswordException
    {
        if (password.length() < MINIMUM_PASSWORD_LENGTH)
        {
            StringBuilder message = new StringBuilder();
            message.append("The password must have at least ");
            message.append(MINIMUM_PASSWORD_LENGTH);
            message.append(" characters.");
            throw new InvalidPasswordException(password, MINIMUM_PASSWORD_LENGTH, message.toString());
        }
        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}
