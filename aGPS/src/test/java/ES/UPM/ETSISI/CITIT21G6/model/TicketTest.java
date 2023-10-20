package ES.UPM.ETSISI.CITIT21G6.model;

import org.junit.jupiter.api.Test;

import java.util.OptionalInt;

import static ES.UPM.ETSISI.CITIT21G6.model.MockData.generateSocialPlan;
import static ES.UPM.ETSISI.CITIT21G6.model.MockData.generateUsers;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketTest
{
    @Test
    void deleteTicket() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        for (User user : users)
        {
            Ticket ticket = new Ticket(user, socialPlan);
            socialPlan.addParticipant(ticket);
            user.addJoinedEvent(ticket);
        }

        Ticket ticket = socialPlan.getParticipants().get(1);
        ticket.delete();

        assertFalse(socialPlan.getParticipants().contains(ticket));
        assertFalse(users[1].getJoinedEvents().contains(ticket));
    }

    @Test
    void setNegativeScore() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        Ticket ticket = new Ticket(users[0], socialPlan);

        assertThrows(Exception.class, () -> ticket.setScore(OptionalInt.of(-5)));
    }

    @Test
    void setScoreGreaterThanMaximum() throws Exception
    {
        SocialPlan socialPlan = generateSocialPlan();
        User[] users = generateUsers();
        Ticket ticket = new Ticket(users[0], socialPlan);

        assertThrows(Exception.class, () -> ticket.setScore(OptionalInt.of(15)));
    }
}