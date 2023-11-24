package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public interface UserView
{
    String showUser(User user);
    String loggedInUser(User user);
    String passwordError();
    String loggedOutUser(User user);
    String userNotFound(UserNotFoundException exception);
    String userAlreadyAdded(UserAlreadyAddedException exception);
    String noLoggedUser();
}
