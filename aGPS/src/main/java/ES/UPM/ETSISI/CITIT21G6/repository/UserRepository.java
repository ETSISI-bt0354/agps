package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.PhoneNumberAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public interface UserRepository
{
    void save(User user) throws UserAlreadyAddedException, PhoneNumberAlreadyAddedException;

    User findByName(String name) throws UserNotFoundException;
}
