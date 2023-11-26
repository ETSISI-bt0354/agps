package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.view.UserView;
import org.junit.jupiter.api.Test;


public class UserControllerTest
{
    UserView view = new ES.UPM.ETSISI.CITIT21G6.controller.UserView();
    UserRepository repository;

    UserController controller = new UserController(repository, view);
    @Test
    void registerUser()
    {
        String argUser = "a,12345,2018-12-27,123456789";
        controller.registerUser(argUser.split(","));
        controller.registerUser(argUser.split(","));
    }

}
