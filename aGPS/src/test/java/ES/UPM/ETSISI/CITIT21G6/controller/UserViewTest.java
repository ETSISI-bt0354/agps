package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public class UserViewTest implements ES.UPM.ETSISI.CITIT21G6.view.UserView {

    @Override
    public String showUser(User user)
    {
         return "";
    }

    @Override
    public String loggedInUser(User user)
    {
        return "";
    }

    @Override
    public String loggedOutUser(User user)
    {
        return "";
    }

    @Override
    public String passwordError()
    {
        return "passwordError";
    }

    @Override
    public String userNotFound(UserNotFoundException exception)
    {
        return "userNotFound";
    }

    @Override
    public String userAlreadyAdded(UserAlreadyAddedException exception)
    {
        return "userAlreadyAdded";
    }

    @Override
    public String noLoggedUser()
    {
        return "noLoggedUser";
    }

    @Override
    public String invalidPassword(InvalidPasswordException exception)
    {
        return "invalidPassword";
    }

    @Override
    public String invalidAge(InvalidAgeException exception)
    {
        return "invalidAge";
    }

    @Override
    public String invalidPhoneNumber(InvalidPhoneNumberException exception)
    {
        return "invalidPhoneNumber";
    }

    public String insufficientArguments(int requiredArguments) {
        return "insufficientArguments";
    }
}
