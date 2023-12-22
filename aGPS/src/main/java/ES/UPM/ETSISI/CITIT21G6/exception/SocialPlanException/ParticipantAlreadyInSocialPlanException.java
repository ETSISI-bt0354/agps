package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException;

public class ParticipantAlreadyInSocialPlanException extends Exception
{
    private String participantName;

    public ParticipantAlreadyInSocialPlanException(String userName)
    {
        this.participantName = userName;
    }

    public String getParticipantName()
    {
        return participantName;
    }
}
