package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;

public interface SocialPlanRepository
{
    void save(SocialPlan socialPlan);
    void delete(SocialPlan socialPlan);
    SocialPlan findByName(String name);
}
