package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.PhoneNumberAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

import java.time.format.DateTimeParseException;

public class CLIUserView implements UserView
{
    @Override
    public String registerUser(User user)
    {
        StringBuilder message = new StringBuilder();
        message.append("\033[0;32m");
        message.append("The user ");
        message.append(user.getName());
        message.append(" has been registered successfully.");
        message.append("\033[0;0m");
        return message.toString();
    }

    @Override
    public String loggedInUser(User user)
    {
        StringBuilder message = new StringBuilder();
        message.append("The user ");
        message.append(user.getName());
        message.append(" has been logged in successfully.");
        return message.toString();

    }

    @Override
    public String passwordError()
    {
        return "The password is incorrect.";
    }

    @Override
    public String loggedOutUser(User user)
    {
        StringBuilder message = new StringBuilder();
        message.append("Bye bye ");
        message.append(user.getName());
        message.append("!");
        return message.toString();
    }

    @Override
    public String userNotFound(UserNotFoundException exception)
    {
        StringBuilder error = new StringBuilder();
        error.append("The user ");
        error.append(exception.getUserName());
        error.append(" does not exist, please try again.");
        return error.toString();
    }

    @Override
    public String userAlreadyAdded(UserAlreadyAddedException exception)
    {
        StringBuilder error = new StringBuilder();
        error.append("The user ");
        error.append(exception.getUser().getName());
        error.append(" already exists.");
        return error.toString();
    }

    @Override
    public String noLoggedUser()
    {
        return "You must be logged in to perform this action.";
    }

    @Override
    public String invalidPassword(InvalidPasswordException exception)
    {
        StringBuilder error = new StringBuilder();
        error.append(exception.getMessage());
        return error.toString();
    }

    @Override
    public String invalidAge(InvalidAgeException exception)
    {
        StringBuilder error = new StringBuilder();
        error.append("Unfortunately, you are");
        switch (exception.getReason())
        {
            case TOOOLD -> error.append(" too old");
            case TOOYOUNG -> error.append(" very young");
        }
        error.append(" to use our services, you are ");
        switch (exception.getReason())
        {
            case TOOOLD ->
            {
                error.append(exception.getCurrentAge() - exception.getProblemAge());
                error.append(" year too late.");
            }
            case TOOYOUNG ->
            {
                error.append(exception.getProblemAge() - exception.getCurrentAge());
                error.append(" year too early.");
            }
        }
        return error.toString();
    }

    @Override
    public String invalidPhoneNumber(InvalidPhoneNumberException exception)
    {
        return "The phone number is not valid.";
    }

    @Override
    public String insufficientArguments(int requiredArguments)
    {
        StringBuilder error = new StringBuilder();
        error.append("You must provide at least ");
        error.append(requiredArguments);
        error.append(" arguments.");
        return error.toString();
    }

    @Override
    public String invalidLocalDateFormat(DateTimeParseException e)
    {
        StringBuilder message = new StringBuilder();
        message.append(e.getParsedString());
        message.append(" is not a valid date or it is in a wrong format (Format: yyyy-mm-dd example: 2023-04-03).");
        return message.toString();
    }

    @Override
    public String phoneNumberAlreadyAdded(PhoneNumberAlreadyAddedException e)
    {
        StringBuilder error = new StringBuilder();
        error.append("The phone number ");
        error.append(e.getPhoneNumber());
        error.append(" is already in use.");
        return error.toString();
    }

    @Override
    public String registerUserHelp(boolean justArgs)
    {
        StringBuilder message = new StringBuilder();
        if(!justArgs) {
            message.append("\033[0;32m");
            message.append("user register ");
        }
        message.append("\033[0m");
        message.append("name password birthday phone-number");
        if(!justArgs) {
            message.append("\n\tbirthday format: yyyy-mm-dd");
        }
        return message.toString();
    }

    @Override
    public String loginUserHelp(boolean justArgs)
    {
        StringBuilder message = new StringBuilder();
        if(!justArgs) {
            message.append("\033[0;32m");
            message.append("user login ");
        }
        message.append("\033[0m");
        message.append("name password");
        return message.toString();
    }

    @Override
    public String logoutUserHelp(boolean justArgs)
    {
        StringBuilder message = new StringBuilder();
        if(!justArgs) {
            message.append("\033[0;32m");
            message.append("user logout ");
        }
        return message.toString();
    }
}
