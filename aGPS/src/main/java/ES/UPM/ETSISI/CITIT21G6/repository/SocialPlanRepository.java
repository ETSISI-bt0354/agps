package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

public interface SocialPlanRepository
{
    void save(SocialPlan socialPlan);
    void delete(SocialPlanId id);
    SocialPlan fetch(SocialPlanId id);
}
