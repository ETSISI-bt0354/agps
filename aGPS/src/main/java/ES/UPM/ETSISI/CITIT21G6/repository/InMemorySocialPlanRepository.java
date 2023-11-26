package ES.UPM.ETSISI.CITIT21G6.repository;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNonExistant;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

import java.util.ArrayList;
import java.util.List;
public class InMemorySocialPlanRepository implements SocialPlanRepository {
    private List<SocialPlan> socialPlans;

public InMemorySocialPlanRepository() {
        socialPlans = new ArrayList<>();
    }

    public void save(SocialPlan socialPlan) throws SocialPlanAlreadyAddedException {
        if(socialPlans.contains(socialPlan))
            throw new SocialPlanAlreadyAddedException(socialPlan);
        socialPlans.add(socialPlan);
    }

    @Override
    public void delete(SocialPlanId id) throws SocialPlanNonExistant {
        if(!socialPlans.contains(id))
            throw new SocialPlanNonExistant(id);
        socialPlans.remove(id);
    }

    @Override
    public SocialPlan fetch(SocialPlanId id) {
        return socialPlans.stream()
                .filter(socialPlan -> socialPlan.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
