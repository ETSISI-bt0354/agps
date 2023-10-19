package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Ticket
{
    private final User user;
    private final SocialPlan socialPlan;
    private OptionalInt score;

    protected Ticket(User user, SocialPlan socialPlan)
    {
        this.user = user;
        this.socialPlan = socialPlan;
        this.score = OptionalInt.empty();
    }

    public void delete() {
        user.deleteTicket(this);
        socialPlan.deleteTicket(this);
    }

    public User getUser()
    {
        return user;
    }

    public SocialPlan getSocialPlan()
    {
        return socialPlan;
    }

    public OptionalInt getScore()
    {
        return score;
    }

    public void setScore(OptionalInt score)
    {
        this.score = score;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!getUser().equals(ticket.getUser())) return false;
        return getSocialPlan().equals(ticket.getSocialPlan());
    }

    @Override
    public int hashCode()
    {
        int result = getUser().hashCode();
        result = 31 * result + getSocialPlan().hashCode();
        return result;
    }
}
