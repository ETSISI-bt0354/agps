package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

public class UserAlreadyInSocialPlanException extends Exception
{
    private String userName;

    public UserAlreadyInSocialPlanException(String userName)
    {
        this.userName = userName;
    }

    public String getUserName()
    {
        return userName;
    }
}
