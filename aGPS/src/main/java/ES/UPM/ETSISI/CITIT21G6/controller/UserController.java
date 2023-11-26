package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.view.UserView;

import java.time.LocalDate;

public class UserController extends SessionController
{
    private UserRepository repository;
    private UserView view;

    public UserController(UserRepository repository, UserView view)
    {
        super();
        this.repository = repository;
        this.view = view;
    }
    public String registerUser(String[] args)
    {
        String result;
        String name = args[0];
        String password = args[1];
        LocalDate birthday = LocalDate.parse(args[2]);
        String phoneNumber = args[3];

        try
        {
            User user = new User(name, password, birthday, phoneNumber);
            repository.save(user);
            result = view.showUser(user);
        }
        catch (UserAlreadyAddedException e)
        {
            result = view.userAlreadyAdded(e);
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }

        return result;
    }
    public String loginUser(String[] args)
    {
        String result;
        String name = args[0];
        String password = args[1];

        try
        {
            User user = repository.findByName(name);
            if (user.getPassword().equals(password))
            {
                result = view.loggedInUser(user);
            }
            else
            {
                result = view.passwordError();
            }
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }
        return result;
    }
    public String logoutUser(String[] args)
    {
        String name = args[0];
        String result;
        try
        {
            User user = repository.findByName(name);
            if (user != null) {
                result = view.loggedOutUser(user);
            } else {
                result = view.noLoggedUser();
            }
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }
        return result;
    }
}
