package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public class UserView implements ES.UPM.ETSISI.CITIT21G6.view.UserView {

    @Override
    public String showUser(User user)
    {
        return user == null ? "E":"";
    }

    @Override
    public String loggedInUser(User user)
    {
        return user == null ? "E":"";
    }

    @Override
    public String passwordError()
    {
        return "E";
    }

    @Override
    public String loggedOutUser(User user)
    {
        return "E";
    }

    @Override
    public String userNotFound(UserNotFoundException exception)
    {
        return "E";
    }

    @Override
    public String userAlreadyAdded(UserAlreadyAddedException exception)
    {
        return "E";
    }

    @Override
    public String noLoggedUser()
    {
        return "E";
    }

    @Override
    public String invalidPassword(InvalidPasswordException exception)
    {
        return "E";
    }

    @Override
    public String invalidAge(InvalidAgeException exception)
    {
        return "E";
    }

    @Override
    public String invalidPhoneNumber(InvalidPhoneNumberException exception)
    {
        return "E";
    }
}
