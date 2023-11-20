package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.model.User;

public interface UserRepository
{
    void save(User user);
    User findByName(String name);
}
