package ES.UPM.ETSISI.CITIT21G6.model;

import java.time.LocalDate;

public class UserController {
    private UserRepository repository;
    private UserView view;

    public UserController(UserRepository repository, UserView view) {
        this.repository = repository;
        this.view = view;
    }
    public String registerUser(String[] args){
        String name = args[0];
        String password = args[1];
        LocalDate birthday = LocalDate.parse(args[2]);
        String phoneNumber = args[3];
        try {
            User user = new User(name, password, birthday, phoneNumber);
            repository.save(user);
            return "User registered successfully";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public String loginUser(String[] args){
        String name = args[0];
        String password = args[1];
        try {
            User user = repository.findByName(name);
            if (user.getPassword().equals(password)) {
                return "User logged in successfully";
            } else {
                return "Wrong password";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public String logoutUser(String[] args){
        String name = args[0];
        try {
            User user = repository.findByName(name);
            if (user != null) {
                return "User logged out successfully";
            } else {
                return "User not found";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
