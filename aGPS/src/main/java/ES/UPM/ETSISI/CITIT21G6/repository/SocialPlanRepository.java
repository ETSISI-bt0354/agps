package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

import java.util.List;

public interface SocialPlanRepository
{
    void save(SocialPlan socialPlan) throws SocialPlanAlreadyAddedException;

    void delete(SocialPlanId id) throws SocialPlanNotFoundException;

    void update(SocialPlan socialPlan) throws SocialPlanNotFoundException;

    SocialPlan fetch(SocialPlanId id) throws SocialPlanNotFoundException;

    List<SocialPlan> fetchAllSocialPlans();
}
