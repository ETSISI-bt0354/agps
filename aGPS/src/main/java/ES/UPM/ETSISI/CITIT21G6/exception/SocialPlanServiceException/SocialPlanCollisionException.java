package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;

public class SocialPlanCollisionException extends Exception
{
    private final SocialPlan socialPlanCollision;

    public SocialPlanCollisionException(SocialPlan socialPlanCollision)
    {
        this.socialPlanCollision = socialPlanCollision;
    }

    public SocialPlan getSocialPlanCollision()
    {
        return socialPlanCollision;
    }
}
