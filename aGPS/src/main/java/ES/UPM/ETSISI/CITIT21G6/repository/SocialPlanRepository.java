package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFound;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

public interface SocialPlanRepository
{
    void save(SocialPlan socialPlan) throws SocialPlanAlreadyAddedException;
    void delete(SocialPlanId id) throws SocialPlanNotFound;
    SocialPlan fetch(SocialPlanId id) throws SocialPlanNotFound;
}
