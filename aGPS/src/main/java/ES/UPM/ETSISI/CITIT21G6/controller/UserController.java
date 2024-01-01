package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import ES.UPM.ETSISI.CITIT21G6.view.UserView;

import java.time.LocalDate;

public class UserController extends SessionController
{
    private static final int MINIMUM_REGISTER_ARGUMENT_LENGTH = 4;
    private static final int MINIMUM_LOGIN_ARGUMENT_LENGTH = 2;
    private UserService service;
    private UserView view;

    public UserController(UserService service, UserView view)
    {
        super();
        this.service = service;
        this.view = view;
    }
    public String registerUser(String[] args)
    {
        if(args.length < MINIMUM_REGISTER_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_REGISTER_ARGUMENT_LENGTH);

        String name = args[0];
        String password = args[1];
        LocalDate birthday = LocalDate.parse(args[2]);
        String phoneNumber = args[3];

        try
        {
            User user = service.registerUser(name, password, birthday, phoneNumber);
            return view.registerUser(user);
        }
        catch (InvalidPasswordException e)
        {
            return view.invalidPassword(e);
        }
        catch (InvalidAgeException e)
        {
            return view.invalidAge(e);
        }
        catch (InvalidPhoneNumberException e)
        {
            return view.invalidPhoneNumber(e);
        }
        catch (UserAlreadyAddedException e)
        {
            return view.userAlreadyAdded(e);
        }
    }
    public String loginUser(String[] args)
    {
        if(args.length < MINIMUM_LOGIN_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_LOGIN_ARGUMENT_LENGTH);

        String name = args[0];
        String password = args[1];

        User user;
        try
        {
            user = service.findByName(name);
        }
        catch (UserNotFoundException e)
        {
            return view.userNotFound(e);
        }

        if (user.getPassword().equals(password))
        {
            setLoggedUser(user);
            return view.loggedInUser(user);
        }
        else
        {
            return view.passwordError();
        }

    }
    public String logoutUser(String[] args)
    {
        if (!isUserLogged())
            return view.noLoggedUser();

        User loggedUser = getLoggedUser();
        setLoggedUser(null);
        return view.loggedOutUser(loggedUser);
    }
}
