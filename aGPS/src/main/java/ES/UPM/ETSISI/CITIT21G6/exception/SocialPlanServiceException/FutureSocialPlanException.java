package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;

public class FutureSocialPlanException extends Exception
{
    private final SocialPlan socialPlan;

    public FutureSocialPlanException(SocialPlan socialPlan)
    {
        this.socialPlan = socialPlan;
    }

    public SocialPlan getSocialPlan()
    {
        return socialPlan;
    }
}
