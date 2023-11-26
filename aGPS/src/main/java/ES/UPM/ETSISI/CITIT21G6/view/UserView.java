package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
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
    String invalidPassword(InvalidPasswordException exception);
    String invalidAge(InvalidAgeException exception);
    String invalidPhoneNumber(InvalidPhoneNumberException exception);
}
