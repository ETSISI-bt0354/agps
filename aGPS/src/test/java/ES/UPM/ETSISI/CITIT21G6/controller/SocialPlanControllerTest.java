package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidAgeException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPasswordException;
import ES.UPM.ETSISI.CITIT21G6.exception.UserException.InvalidPhoneNumberException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.User;
import ES.UPM.ETSISI.CITIT21G6.repository.InMemorySocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.OptionalInt;


public class SocialPlanControllerTest {

    @Test
    void createSocialPlan() throws InvalidAgeException, InvalidPhoneNumberException, InvalidPasswordException {
        User loggedUser = new User("test", "test", "test", "test", "test", "test", "test", "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        controller.createSocialPlan(new String[]{"test", "test", "test"});
        String[] args = {"test", "test", "test"};
        assert controller.createSocialPlan(args).isEmpty();

    }

    @Test
    void deleteSocialPlan()
    {
        SocialPlan socialPlan = new SocialPlan("test", "test", LocalDate.now(), "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        String[] args = {"test"};
        assert controller.deleteSocialPlan(args).isEmpty();
    }

    @Test
    void addActivity()
    {
        SocialPlan socialPlan = new SocialPlan("test", "test", LocalDate.now(), "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        String[] args = {"test", "test", "test", "test", "test"};
        assert controller.addActivity(args).isEmpty();
    }

    @Test
    void removeNonExistantUser()
    {
        SocialPlan socialPlan = new SocialPlan("test", "test", LocalDate.now(), "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        String[] args = {"test"};
        assert controller.removeUser(args).equals("E");
    }

    @Test
    void removeExistantUser()
    {
        SocialPlan socialPlan = new SocialPlan("test", "test", LocalDate.now(), "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        controller.addUser(new String[]{"test"});
        String[] args = {"test"};
        assert controller.removeUser(args).isEmpty();
    }

    @Test
    void addUser()
    {
        SocialPlan socialPlan = new SocialPlan("test", "test", LocalDate.now(), "test");
        SocialPlanRepository repository = new InMemorySocialPlanRepository();
        SocialPlanView view = new SocialPlanView();
        SocialPlanController controller = new SocialPlanController(repository, view);
        String[] args = {"test"};
        assert controller.addUser(args).isEmpty();
    }

}

