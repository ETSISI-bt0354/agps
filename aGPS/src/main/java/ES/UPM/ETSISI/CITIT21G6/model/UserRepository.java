package ES.UPM.ETSISI.CITIT21G6.model;

public interface UserRepository
{
    void save(User user);
    User findByName(String name);
}
