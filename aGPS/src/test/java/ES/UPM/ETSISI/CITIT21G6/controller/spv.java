package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.util.List;

public class spv implements SocialPlanView {
    @Override
    public String create(SocialPlan socialPlan) {
        return null;
    }

    @Override
    public String delete(SocialPlanId id) {
        return null;
    }

    @Override
    public String addActivity() {
        return null;
    }

    @Override
    public String insufficentArguments(int requieredArguments) {
        return null;
    }

    @Override
    public String noLoggedUser() {
        return null;
    }

    @Override
    public String listPlans(List<SocialPlan> socialPlans, ListOrder order) {
        return null;
    }

    @Override
    public String removeUser(Ticket ticket) {
        return null;
    }

    @Override
    public String addUser(Ticket ticket) {
        return null;
    }

    @Override
    public String socialPlanNotFound(SocialPlanNotFoundException e) {
        return null;
    }

    @Override
    public String price(double price) {
        return null;
    }
}
