package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Ticket
{
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;
    private final User user;
    private final SocialPlan socialPlan;
    private OptionalInt score;

    public Ticket(User user, SocialPlan socialPlan) throws NullPointerException
    {
        if (user == null)
            throw new NullPointerException("User cannot be null");

        if (socialPlan == null)
            throw new NullPointerException("SocialPlan cannot be null");

        this.user = user;
        this.socialPlan = socialPlan;
        this.score = OptionalInt.empty();
    }

    public void delete()
    {
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
        if (score.isPresent() && (score.getAsInt() < MINIMUM_SCORE || score.getAsInt() > MAXIMUM_SCORE))
        {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("The score must be between");
            errorMessage.append(MINIMUM_SCORE);
            errorMessage.append(" and ");
            errorMessage.append(MAXIMUM_SCORE);
            errorMessage.append(".");

            throw new Exception(errorMessage.toString());
        }

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
