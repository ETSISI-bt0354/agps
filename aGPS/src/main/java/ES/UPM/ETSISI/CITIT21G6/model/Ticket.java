package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Ticket
{
    private static final int MINIMUMSCORE = 0;
    private static final int MAXIMUMSCORE = 10;
    private final User user;
    private final SocialPlan socialPlan;
    private OptionalInt score;

    public Ticket(User user, SocialPlan socialPlan)
    {
        this.user = user;
        this.socialPlan = socialPlan;
        this.score = OptionalInt.empty();
    }

    public void delete() {
        user.removeJoinedEvent(this);
        socialPlan.removeParticipant(this);
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

    public void setScore(OptionalInt score) throws Exception
    {
        if (score.isPresent() && (score.getAsInt() < MINIMUMSCORE || score.getAsInt() > MAXIMUMSCORE))
            throw new Exception("The core must be between 0 and 10");

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
