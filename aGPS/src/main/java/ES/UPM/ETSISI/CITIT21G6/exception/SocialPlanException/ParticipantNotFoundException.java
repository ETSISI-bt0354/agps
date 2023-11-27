package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

public class ParticipantNotFoundException extends Exception
{
    private final String participantName;

    public ParticipantNotFoundException(String userName)
    {
        super();
        this.participantName = userName;
    }

    public String getParticipantName()
    {
        return participantName;
    }
}
