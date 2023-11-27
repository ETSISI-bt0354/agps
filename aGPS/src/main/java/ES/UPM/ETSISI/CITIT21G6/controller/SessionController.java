package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.User;

public abstract class SessionController
{
    private static User loggedUser = null;

    protected User getLoggedUser()
    {
        return loggedUser;
    }

    protected void setLoggedUser(User loggedUser)
    {
        SessionController.loggedUser = loggedUser;
    }
}
