package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.ParticipantNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemorySocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemoryUserRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class SocialPlanControllerTest
{

    private static final String loggedUserName = "test";

    @BeforeAll
    public static void setLoggedUser() throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException
    {
        UserController userController = new UserController(new InMemoryUserRepository(), new UserViewTest());
        userController.registerUser(new String[]{loggedUserName, "1234", LocalDate.now()
                .minusYears(30).toString(), "123456789"});
        userController.loginUser(new String[]{loggedUserName, "1234"});
    }

    @Test
    void createSocialPlan() throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());


        assertEquals("", controller.createSocialPlan(new String[]{"test", LocalDateTime.now()
                .plusDays(15).toString(), "test"}));
        assertDoesNotThrow(() -> repository.fetch(new SocialPlanId(loggedUserName, "test")));

    }

    @Test
    void deleteSocialPlan()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());

        controller.createSocialPlan(new String[]{"test", LocalDateTime.now().plusDays(15).toString(), "test"});

        assertEquals("", controller.deleteSocialPlan(new String[]{"test"}));
        assertThrows(SocialPlanNotFoundException.class, () -> repository.fetch(new SocialPlanId(loggedUserName, "test")));
    }

    @Test
    void addActivity() throws SocialPlanNotFoundException
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());

        controller.createSocialPlan(new String[]{"test", LocalDateTime.now().plusDays(15).toString(), "test"});

        assertEquals("", controller.addActivity(new String[]{"test", "test", "test", "30", "25", "GENERIC"}));
        assertFalse(repository.fetchAllSocialPlans().get(0).getActivities().isEmpty());
    }

    @Test
    void addParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());

        controller.createSocialPlan(new String[]{"test", LocalDateTime.now().plusDays(15).toString(), "test"});

        assertEquals("", controller.addParticipant(new String[]{"test", "test"}));
        assertEquals(loggedUserName, repository.fetchAllSocialPlans().get(0).getParticipants().get(0).getUserName());
    }

    @Test
    void removeNonExistantParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());

        controller.createSocialPlan(new String[]{"test", LocalDateTime.now().plusDays(15).toString(), "test"});

        assertEquals("participantNotFound", controller.removeParticipant(new String[]{"test", "test"}));
    }

    @Test
    void removeExistantParticipant()
    {
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanController controller = new SocialPlanController(repository, new SocialPlanViewTest());

        controller.createSocialPlan(new String[]{"test", LocalDateTime.now().plusDays(15).toString(), "test"});
        controller.addParticipant(new String[]{"test"});

        assertEquals("", controller.removeParticipant(new String[]{"test", "test"}));
    }
}

