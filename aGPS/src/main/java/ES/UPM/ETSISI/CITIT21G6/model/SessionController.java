package ES.UPM.ETSISI.CITIT21G6.model;

public class SessionController
{
    protected User loggedUser;

    protected SessionController()
    {}
    public User getLoggedUser()
    {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser)
    {
        this.loggedUser = loggedUser;
    }
}
