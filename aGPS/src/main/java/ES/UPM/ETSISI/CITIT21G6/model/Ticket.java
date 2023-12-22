package ES.UPM.ETSISI.CITIT21G6.model;

import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;

import java.util.OptionalInt;

public class Ticket
{
    public static final int MINIMUM_SCORE = 0;
    public static final int MAXIMUM_SCORE = 10;
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

    public void setScore(OptionalInt score) throws InvalidScoreException
    {
        if (score.isPresent() && (score.getAsInt() < MINIMUM_SCORE || score.getAsInt() > MAXIMUM_SCORE))
            throw new InvalidScoreException(score.getAsInt(), MINIMUM_SCORE, MAXIMUM_SCORE);

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
