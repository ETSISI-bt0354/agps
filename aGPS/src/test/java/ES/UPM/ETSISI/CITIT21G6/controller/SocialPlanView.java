package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;

import java.util.List;

public class SocialPlanView implements ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView {
    @Override
    public String create(SocialPlan socialPlan) {
        if(socialPlan == null)
            return "E";
        return "";
    }

    @Override
    public String delete(SocialPlanId id) {
        if(id == null)
            return "E";
        return "";
    }

    @Override
    public String addActivity() {
        return null;
    }

    @Override
    public String insufficientArguments(int requieredArguments) {
        return "E";
    }

    @Override
    public String noLoggedUser() {
        return "E";
    }

    @Override
    public String listPlans(List<SocialPlan> socialPlans, ListOrder order) {
        return "";
    }

    @Override
    public String removeUser(Ticket ticket, SocialPlan socialPlan) {
        if(!socialPlan.getParticipants().contains(ticket))
            return "";
        return "E";

    }

    @Override
    public String addUser(Ticket ticket, SocialPlan socialPlan) {
        if(socialPlan.getParticipants().contains(ticket))
            return "";
        return "E";
    }

    @Override
    public String socialPlanNotFound(SocialPlanNotFoundException e) {
        return "E";
    }

    @Override
    public String price(double price) {
        return String.valueOf(price);
    }
}
