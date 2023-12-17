package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.ParticipantNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.model.ActivityType;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemorySocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;

import ES.UPM.ETSISI.CITIT21G6.service.SocialPlanService;
import ES.UPM.ETSISI.CITIT21G6.service.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;


public class SocialPlanServiceTest
{

    private static final String loggedUserName = "test";
    @BeforeAll
    public static void setLoggedUser() throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException
    {
        UserService userService = new UserService(new InMemoryUserRepository());
        UserController userController = new UserController(userService, null);
        try
        {
            userController.registerUser(new String[]{loggedUserName, "1234", LocalDate.now().minusYears(30).toString(), "123456789"});
            userController.loginUser(new String[]{loggedUserName, "1234"});
        } catch (NullPointerException ignored) {}
    }

    @Test
    void createSocialPlan() throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);


        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));
        assertDoesNotThrow(() -> repository.fetch(new SocialPlanId(loggedUserName, "test")));

    }

    @Test
    void deleteSocialPlan()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);

        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));

        SocialPlanId socialPlanId = new SocialPlanId("test", "test");
        assertDoesNotThrow(() -> service.deleteSocialPlan(socialPlanId));
        assertThrows(SocialPlanNotFoundException.class, () -> repository.fetch(socialPlanId));
    }

    @Test
    void addActivity() throws SocialPlanNotFoundException
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);

        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));

        SocialPlanId socialPlanId = new SocialPlanId("test", "test");
        assertDoesNotThrow(() -> service.addActivity(socialPlanId, "test", "test", 30, 25, ActivityType.GENERIC, OptionalInt.empty()));
        assertFalse(repository.fetchAllSocialPlans().get(0).getActivities().isEmpty());
    }

    @Test
    void addParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);

        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));

        SocialPlanId socialPlanId = new SocialPlanId("test", "test");
        assertDoesNotThrow(() -> service.addParticipant(socialPlanId, "test"));
        assertEquals(loggedUserName, repository.fetchAllSocialPlans().get(0).getParticipants().get(0).getUserName());
    }

    @Test
    void removeNonExistantParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);

        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));

        SocialPlanId socialPlanId = new SocialPlanId("test", "test");
        assertThrows(ParticipantNotFoundException.class,
                () -> service.removeParticipant(socialPlanId, "test"));
    }

    @Test
    void removeExistantParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanService service = new SocialPlanService(repository);

        assertDoesNotThrow(() -> service.createSocialPlan("test", "test", LocalDateTime.now().plusDays(15), "test", OptionalInt.empty()));

        SocialPlanId socialPlanId = new SocialPlanId("test", "test");
        assertDoesNotThrow(() -> service.addParticipant(socialPlanId, "test"));
        assertDoesNotThrow(() -> service.removeParticipant(socialPlanId, "test"));
    }
}

