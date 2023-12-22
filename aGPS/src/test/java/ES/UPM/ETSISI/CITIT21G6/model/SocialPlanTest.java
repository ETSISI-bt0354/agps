package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.ActivityAlreadyInSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.FullSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.InvalidCapacityException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.PastDateException;
import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static ES.UPM.ETSISI.CITIT21G6.model.MockData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SocialPlanTest
{

    @Test
    void setNegativeCapacity() throws PastDateException, InvalidCapacityException
    {
        SocialPlan socialPlan = generateSocialPlan();
        assertThrows(InvalidCapacityException.class, () -> socialPlan.setCapacity(OptionalInt.of(-3)));
    }

    @Test
    void setZeroCapacity() throws PastDateException, InvalidCapacityException
    {
        SocialPlan socialPlan = generateSocialPlan();
        assertThrows(InvalidCapacityException.class, () -> socialPlan.setCapacity(OptionalInt.of(0)));
    }

    @Test
    void setCapacityTest() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        socialPlan.setCapacity(OptionalInt.of(55));
        assertEquals(OptionalInt.of(55), socialPlan.getCapacity());
        socialPlan.setCapacity(OptionalInt.empty());
        assertEquals(OptionalInt.empty(), socialPlan.getCapacity());
    }

    @Test
    void setCapacityLowerThanParticipants() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        for (User user : users)
            socialPlan.addParticipant(user.getName());

        assertThrows(InvalidCapacityException.class, () -> socialPlan.setCapacity(OptionalInt.of(2)));
    }

    @Test
    void setCapacityGreaterThanActivities() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity : activities)
            socialPlan.addActivity(activity);

        assertThrows(InvalidCapacityException.class, () -> socialPlan.setCapacity(OptionalInt.of(55)));
    }

    @Test
    void addActivity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity : activities)
            socialPlan.addActivity(activity);

        assertEquals(activities[2], socialPlan.getActivities().get(2));
        assertEquals(OptionalInt.of(30), socialPlan.getCapacity());
    }

    @Test
    void addRepeatedActivity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity : activities)
            socialPlan.addActivity(activity);

        assertThrows(ActivityAlreadyInSocialPlanException.class, () -> socialPlan.addActivity(activities[0]));
    }

    @Test
    void addActivityWithoutEnoughCapacity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity : activities)
            socialPlan.addActivity(activity);

        User[] users = generateUsers();
        for (User user : users)
            socialPlan.addParticipant(user.getName());

        Activity activity = new Activity("D", "d", 60, OptionalInt.of(2), 60, null);

        assertThrows(InvalidCapacityException.class, () -> socialPlan.addActivity(activity));
    }

    @Test
    void addUserExceedingCapacity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        socialPlan.setCapacity(OptionalInt.of(2));

        User[] users = generateUsers();
        socialPlan.addParticipant(users[0].getName());
        socialPlan.addParticipant(users[1].getName());

        assertThrows(FullSocialPlanException.class, () -> socialPlan.addParticipant(users[2].getName()));
    }

    @Test
    void addRepeatedUser() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        socialPlan.addParticipant(users[0].getName());

        assertThrows(Exception.class, () -> socialPlan.addParticipant(users[0].getName()));
    }
}