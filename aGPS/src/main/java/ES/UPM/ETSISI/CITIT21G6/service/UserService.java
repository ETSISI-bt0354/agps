package ES.UPM.ETSISI.CITIT21G6.service;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.view.UserView;

import java.time.LocalDate;

public class UserService
{
    private UserRepository repo;

    public UserService(UserRepository repo)
    {
        this.repo = repo;
    }

    public User registerUser(String name, String password, LocalDate birthday, String phoneNumber)
            throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException, UserAlreadyAddedException
    {
        User user = new User(name, password, birthday, phoneNumber);
        repo.save(user);
        return user;
    }

    public User findByName(String name) throws UserNotFoundException
    {
        return repo.findByName(name);
    }
}
