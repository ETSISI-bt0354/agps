package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import ES.UPM.ETSISI.CITIT21G6.view.UserView;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserControllerTest
{
    @Test
    void registerUser()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service, new UserViewTest());

        controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});
        assertDoesNotThrow(() -> repository.findByName("a"));


        controller.registerUser(new String[] {"b", "12345", LocalDate.now().minusYears(30).toString(), "987654321"});
        assertDoesNotThrow(() -> repository.findByName("b"));
    }

    @Test
    void registedDuplicateUser()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service, new UserViewTest());

        controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});

        String result = controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});
        assertEquals("userAlreadyAdded", result);
    }

    @Test
    void registedDuplicateName()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service, new UserViewTest());

        controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});

        String result = controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "987654321"});
        assertEquals("userAlreadyAdded", result);
    }

    @Test
    void registedDuplicatePhone()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);
        UserController controller = new UserController(service, new UserViewTest());

        controller.registerUser(new String[] {"a", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});

        String result = controller.registerUser(new String[] {"b", "12345", LocalDate.now().minusYears(30).toString(), "123456789"});
        assertEquals("userAlreadyAdded", result);
    }

}
