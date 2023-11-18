package ES.UPM.ETSISI.CITIT21G6.model;

public interface UserRepository {
    public void save(User user);
    public User findByName(String name);
}
