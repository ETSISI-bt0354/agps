package ES.UPM.ETSISI.CITIT21G6.repository;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

import java.util.ArrayList;
import java.util.List;

public class InMemorySocialPlanRepository implements SocialPlanRepository
{
    private List<SocialPlan> socialPlans;

    public InMemorySocialPlanRepository()
    {
        socialPlans = new ArrayList<>();
    }

    public void save(SocialPlan socialPlan) throws SocialPlanAlreadyAddedException
    {
        if (socialPlans.contains(socialPlan)) throw new SocialPlanAlreadyAddedException(socialPlan);
        socialPlans.add(socialPlan);
    }

    @Override
    public void delete(SocialPlanId id) throws SocialPlanNotFoundException
    {
        SocialPlan socialPlan = socialPlans.stream().filter(plan -> id.equals(plan.getId())).findFirst()
                .orElseThrow(() -> new SocialPlanNotFoundException(id));
        socialPlans.remove(socialPlan);
    }

    public void update(SocialPlan socialPlan) throws SocialPlanNotFoundException
    {
        int index = socialPlans.indexOf(socialPlan);
        if (index == -1) throw new SocialPlanNotFoundException(socialPlan.getId());

        socialPlans.set(index, socialPlan);
    }

    @Override
    public SocialPlan fetch(SocialPlanId id) throws SocialPlanNotFoundException
    {
        return socialPlans.stream().filter(socialPlan -> socialPlan.getId().equals(id)).findFirst()
                .orElseThrow(() -> new SocialPlanNotFoundException(id));
    }

    @Override
    public List<SocialPlan> fetchAllSocialPlans()
    {
        return this.socialPlans;
    }

}
