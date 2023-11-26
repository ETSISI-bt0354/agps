package ES.UPM.ETSISI.CITIT21G6.model;

import java.util.OptionalInt;

public class Ticket
{
    private static final int MINIMUM_SCORE = 0;
    private static final int MAXIMUM_SCORE = 10;
    private final String userName;
    private OptionalInt score;

    public Ticket(String userName)
    {
        this.userName = userName;
        this.score = OptionalInt.empty();
    }

    public String getUserName()
    {
        return userName;
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

        return getUserName().equals(ticket.getUserName());
    }

    @Override
    public int hashCode()
    {
        return getUserName().hashCode();
    }
}
