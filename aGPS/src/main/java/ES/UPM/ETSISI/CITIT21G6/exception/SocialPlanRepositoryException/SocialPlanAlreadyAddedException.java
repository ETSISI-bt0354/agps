package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;

public class SocialPlanAlreadyAddedException extends Exception {
    private final SocialPlan socialPlan;
    public SocialPlanAlreadyAddedException(SocialPlan socialPlan) {
        this.socialPlan = socialPlan;
    }
}
