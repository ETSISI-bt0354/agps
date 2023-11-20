package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.User;

public abstract class SessionController
{
    protected static User loggedUser = null;

    public User getLoggedUser()
    {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser)
    {
        SessionController.loggedUser = loggedUser;
    }
}
