package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNonExistant;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

public interface SocialPlanRepository
{
    void save(SocialPlan socialPlan) throws SocialPlanAlreadyAddedException;
    void delete(SocialPlanId id) throws SocialPlanNonExistant;
    SocialPlan fetch(SocialPlanId id);
}
