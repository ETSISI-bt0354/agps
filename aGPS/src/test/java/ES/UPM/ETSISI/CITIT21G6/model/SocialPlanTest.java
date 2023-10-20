package ES.UPM.ETSISI.CITIT21G6.model;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

import static ES.UPM.ETSISI.CITIT21G6.model.MockData.generateSocialPlan;
import static ES.UPM.ETSISI.CITIT21G6.model.MockData.generateUsers;
import static ES.UPM.ETSISI.CITIT21G6.model.MockData.generateActivities;

class SocialPlanTest
{

    @Test
    void setNegativeCapacity()
    {
       SocialPlan socialPlan = generateSocialPlan();
       assertThrows(Exception.class, () -> socialPlan.setCapacity(OptionalInt.of(-3)));
    }

    @Test
    void setZeroCapacity()
    {
        SocialPlan socialPlan = generateSocialPlan();
        assertThrows(Exception.class, () -> socialPlan.setCapacity(OptionalInt.of(0)));
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
        for (User user: users)
            socialPlan.addParticipant(new Ticket(user, socialPlan));

        assertThrows(Exception.class, () -> socialPlan.setCapacity(OptionalInt.of(2)));
    }

    @Test
    void setCapacityGreaterThanActivities() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity: activities)
            socialPlan.addActivity(activity);

        assertThrows(Exception.class, () -> socialPlan.setCapacity(OptionalInt.of(55)));
    }

    @Test
    void addActivity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity: activities)
            socialPlan.addActivity(activity);

        assertEquals(activities[2], socialPlan.getActivities().get(2));
        assertEquals(OptionalInt.of(30), socialPlan.getCapacity());
    }

    @Test
    void addRepeatedActivity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity: activities)
            socialPlan.addActivity(activity);

        assertThrows(Exception.class, () -> socialPlan.addActivity(activities[0]));
    }

    @Test
    void addActivityWithoutEnoughCapacity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        Activity[] activities = generateActivities();
        for (Activity activity: activities)
            socialPlan.addActivity(activity);

        User[] users = generateUsers();
        for (User user: users)
            socialPlan.addParticipant(new Ticket(user, socialPlan));

        Activity activity = new Activity("D", "d", 60, 60, null);
        activity.setCapacity(OptionalInt.of(2));

        assertThrows(Exception.class, () -> socialPlan.addActivity(activity));
    }

    @Test
    void addUserExceedingCapacity() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        socialPlan.setCapacity(OptionalInt.of(2));

        User[] users = generateUsers();
        socialPlan.addParticipant(new Ticket(users[0], socialPlan));
        socialPlan.addParticipant(new Ticket(users[1], socialPlan));

        assertThrows(Exception.class, () -> socialPlan.addParticipant(new Ticket(users[2], socialPlan)));
    }

    @Test
    void addRepeatedUser() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        socialPlan.addParticipant(new Ticket(users[0], socialPlan));

        assertThrows(Exception.class, () -> socialPlan.addParticipant(new Ticket(users[0], socialPlan)));
    }
}