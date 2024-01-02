package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacityException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidDurationException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidPriceException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.PastDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.OptionalInt;

public class MockData
{
    static SocialPlan generateSocialPlan() throws PastDateException, InvalidCapacityException
    {
        return new SocialPlan("A", "Prueba", LocalDateTime.now().plusDays(15), OptionalInt.empty(), "prueba");
    }

    static User[] generateUsers() throws Exception
    {
        User[] users = new User[3];
        users[0] = new User("A", "aaa", LocalDate.now().minusYears(50), "111111111");
        users[1] = new User("B", "aaa", LocalDate.now().minusYears(40), "222222222");
        users[2] = new User("C", "aaa", LocalDate.now().minusYears(30), "333333333");
        return users;
    }

    static Activity[] generateActivities() throws InvalidCapacityException, InvalidDurationException, InvalidPriceException
    {
        Activity[] activities = new Activity[3];
        activities[0] = new Activity("A", "a", 10, OptionalInt.empty(), 2, null);
        activities[1] = new Activity("B", "b", 20, OptionalInt.empty(), 4, null);
        activities[1].setCapacity(OptionalInt.of(30));
        activities[2] = new Activity("C", "c", 15, OptionalInt.empty(), 3, null);
        activities[2].setCapacity(OptionalInt.of(70));

        return activities;
    }
}
