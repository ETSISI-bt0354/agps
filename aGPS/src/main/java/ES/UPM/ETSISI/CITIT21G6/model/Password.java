package ES.UPM.ETSISI.CITIT21G6.model;

public class Password
{
    private static final int MINIMUM_PASSWORD_LENGTH = 3;
    private final String password;

    public Password(String password) throws Exception
    {
        if (password.length() < MINIMUM_PASSWORD_LENGTH)
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The password must have at least ");
            errorMessage.append(MINIMUM_PASSWORD_LENGTH);
            errorMessage.append(" characters.");

            throw new Exception(errorMessage.toString());
        }

        this.password = password;
    }

    public String getPassword()
    {
        return password;
    }
}
