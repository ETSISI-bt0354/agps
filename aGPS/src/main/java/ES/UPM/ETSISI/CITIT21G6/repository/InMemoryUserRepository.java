package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository
{
    private List<User> users;

    public InMemoryUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public void save(User user) throws UserAlreadyAddedException
    {
        if (users.contains(user))
            throw new UserAlreadyAddedException(user);

        users.add(user);
    }

    @Override
    public User findByName(String name) throws UserNotFoundException
    {
        return users.stream()
                .filter(user -> name.equals(user.getName()))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(name));
    }
}
