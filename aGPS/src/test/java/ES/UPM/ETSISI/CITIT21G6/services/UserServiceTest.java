package ES.UPM.ETSISI.CITIT21G6.services;

import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.PhoneNumberAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserRepositoryException.UserAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.UserRepository;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest
{
    @Test
    void registerUser()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);

        assertDoesNotThrow(() -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "123456789"));
        assertDoesNotThrow(() -> repository.findByName("a"));


        assertDoesNotThrow(() -> service.registerUser("b", "12345", LocalDate.now().minusYears(30), "987654321"));
        assertDoesNotThrow(() -> repository.findByName("b"));
    }

    @Test
    void registedDuplicateUser()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);

        assertDoesNotThrow(() -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "123456789"));

        assertThrows(UserAlreadyAddedException.class,
                () -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "123456789"));
    }

    @Test
    void registedDuplicateName()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);

        assertDoesNotThrow(() -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "123456789"));

        assertThrows(UserAlreadyAddedException.class,
                () -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "987654321"));
    }

    @Test
    void registedDuplicatePhone()
    {
        UserRepository repository = new InMemoryUserRepository();
        UserService service = new UserService(repository);

        assertDoesNotThrow(() -> service.registerUser("a", "12345", LocalDate.now().minusYears(30), "123456789"));

        assertThrows(PhoneNumberAlreadyAddedException.class,
                () -> service.registerUser("b", "12345", LocalDate.now().minusYears(30), "123456789"));
    }

}
