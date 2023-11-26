package ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

public class SocialPlanNonExistant extends Exception {
    private final SocialPlanId id;

    public SocialPlanNonExistant(SocialPlanId id) {
        this.id = id;
    }
}
