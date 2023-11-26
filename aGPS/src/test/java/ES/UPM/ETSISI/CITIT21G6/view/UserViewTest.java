package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public class UserViewTest implements UserView {

    @Override
    public String showUser(User user) {
        return null;
    }

    @Override
    public String loggedInUser(User user) {
        return null;
    }

    @Override
    public String passwordError() {
        return null;
    }

    @Override
    public String loggedOutUser(User user) {
        return null;
    }

    @Override
    public String userNotFound(UserNotFoundException exception) {
        return null;
    }

    @Override
    public String userAlreadyAdded(UserAlreadyAddedException exception) {
        return null;
    }

    @Override
    public String noLoggedUser() {
        return null;
    }

    @Override
    public String invalidPassword(InvalidPasswordException exception) {
        return null;
    }

    @Override
    public String invalidAge(InvalidAgeException exception) {
        return null;
    }

    @Override
    public String invalidPhoneNumber(InvalidPhoneNumberException exception) {
        return null;
    }
}
