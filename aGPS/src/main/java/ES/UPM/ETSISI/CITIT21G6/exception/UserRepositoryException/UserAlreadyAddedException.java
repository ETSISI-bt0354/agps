package ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException;

import ES.UPM.ETSISI.CITIT21G6.model.User;

public class UserAlreadyAddedException extends Exception
{
    private final User user;

    public UserAlreadyAddedException(User user)
    {
        this.user = user;
    }

    public User getUser()
    {
        return user;
    }
}
