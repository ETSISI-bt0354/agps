package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

import java.time.LocalDateTime;

public class PastDateException extends Exception
{
    private final LocalDateTime date;

    public PastDateException(LocalDateTime date)
    {
        this.date = date;
    }

    public LocalDateTime getDate()
    {
        return date;
    }
}
