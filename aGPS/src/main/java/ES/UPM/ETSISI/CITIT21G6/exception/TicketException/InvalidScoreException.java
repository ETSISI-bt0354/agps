package ES.UPM.ETSISI.CITIT21G6.exception.TicketException;

public class InvalidScoreException extends Exception
{
    private final int score;
    private final int minimumScore;
    private final int maximumScore;

    public InvalidScoreException(int score, int minimumScore, int maximumScore)
    {
        this.score = score;
        this.minimumScore = minimumScore;
        this.maximumScore = maximumScore;
    }

    public int getScore()
    {
        return score;
    }

    public int getMinimumScore()
    {
        return minimumScore;
    }

    public int getMaximumScore()
    {
        return maximumScore;
    }
}
