package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.controller.ListOrder;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;

import java.util.List;

public interface SocialPlanView
{
    String create(SocialPlan socialPlan);
    String delete(SocialPlanId id);
    String addActivity();
    String insufficientArguments(int requiredArguments);
    String noLoggedUser();
    String listPlans(List<SocialPlan> socialPlans, ListOrder order);
    String removeUser(Ticket ticket);
    String addUser(Ticket ticket);
    String socialPlanNotFound(SocialPlanNotFoundException e);
    String price(double price);
}
