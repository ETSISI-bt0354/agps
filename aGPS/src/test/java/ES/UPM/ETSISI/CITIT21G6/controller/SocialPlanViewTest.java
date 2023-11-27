package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.Activity;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;
import ES.UPM.ETSISI.CITIT21G6.model.Ticket;

import java.util.List;
import java.util.OptionalInt;

public class SocialPlanViewTest implements ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView {
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
    public String addActivity(Activity activity) {
        if(activity == null)
            return "E";
        return "";
    }

    @Override
    public String insufficientArguments(int requiredArguments) {
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
    public String removeUser(Ticket ticket) {
        return "";
    }

    @Override
    public String addUser(Ticket ticket) {
        return "";
    }

    @Override
    public String socialPlanNotFound(SocialPlanNotFoundException e) {
        return "E";
    }

    @Override
    public String price(double price) {
        return String.valueOf(price);
    }

    @Override
    public String invalidCapacity(InvalidCapacityException e) {
        return "E";
    }

    @Override
    public String socialPlanAlreadyAdded(SocialPlanAlreadyAddedException e) {
        return "E";
    }

    @Override
    public String activityAlreadyInSocialPlan(ActivityAlreadyInSocialPlanException e) {
        return "E";
    }

    @Override
    public String fullSocialPlan(FullSocialPlanException e) {
        return "E";
    }

    @Override
    public String userAlreadyInSocialPlan(UserAlreadyInSocialPlanException e) {
        return "E";
    }

    @Override
    public String wrongLister(ListOrderException e) {
        return "E";
    }

    @Override
    public String ticketNotFound(TicketNotFoundException e) {
        return "E";
    }

    @Override
    public String invalidScore(InvalidScoreException e) {
        return "E";
    }

    @Override
    public String setScore(OptionalInt score) {
        if (score.isEmpty())
            return "E";
        return "";
        //TODO: should I be doing this?
    }
}
