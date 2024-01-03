package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.PhoneNumberAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

import java.time.format.DateTimeParseException;

public interface UserView
{
    String registerUser(User user);
    String loggedInUser(User user);
    String passwordError();
    String loggedOutUser(User user);
    String userNotFound(UserNotFoundException exception);
    String userAlreadyAdded(UserAlreadyAddedException exception);
    String noLoggedUser();
    String invalidPassword(InvalidPasswordException exception);
    String invalidAge(InvalidAgeException exception);
    String invalidPhoneNumber(InvalidPhoneNumberException exception);
    String insufficientArguments(int requiredArguments);
    String invalidLocalDateFormat(DateTimeParseException e);
    String phoneNumberAlreadyAdded(PhoneNumberAlreadyAddedException e);
    String registerUserHelp(boolean justArgs);
    String loginUserHelp(boolean justArgs);
    String logoutUserHelp(boolean justArgs);
}
