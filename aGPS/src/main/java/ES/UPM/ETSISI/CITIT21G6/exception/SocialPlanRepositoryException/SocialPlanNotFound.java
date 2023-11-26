package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

public class SocialPlanNotFound extends Exception {
    private final SocialPlanId id;

    public SocialPlanNotFound(SocialPlanId id) {
        this.id = id;
    }

    public SocialPlanId getId()
    {
        return id;
    }
}
