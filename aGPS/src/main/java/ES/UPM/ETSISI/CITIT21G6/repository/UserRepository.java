package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

public interface UserRepository
{
    void save(User user) throws UserAlreadyAddedException;
    User findByName(String name) throws UserNotFoundException;
}
