package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.Activity;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

import java.util.List;
import java.util.OptionalInt;

public class SocialPlanViewTest implements ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView {
    @Override
    public String create(SocialPlan socialPlan) {
        return "";
    }

    @Override
    public String delete(SocialPlanId id) {
        return "";
    }

    @Override
    public String addActivity(Activity activity) {
        return "";
    }

    @Override
    public String insufficientArguments(int requiredArguments) {
        return "insufficientArguments";
    }

    @Override
    public String noLoggedUser() {
        return "noLoggedUser";
    }

    @Override
    public String listPlans(List<SocialPlan> socialPlans, ListOrder order) {
        return "";
    }

    @Override
    public String removeParticipant(String participantName) {
        return "";
    }

    @Override
    public String addParticipant(String participantName) {
        return "";
    }

    @Override
    public String socialPlanNotFound(SocialPlanNotFoundException e) {
        return "socialPlanNotFound";
    }

    @Override
    public String price(double price) {
        return String.valueOf(price);
    }

    @Override
    public String invalidCapacity(InvalidCapacityException e) {
        return "invalidCapacity";
    }

    @Override
    public String socialPlanAlreadyAdded(SocialPlanAlreadyAddedException e) {
        return "socialPlanAlreadyAdded";
    }

    @Override
    public String activityAlreadyInSocialPlan(ActivityAlreadyInSocialPlanException e) {
        return "activityAlreadyInSocialPlan";
    }

    @Override
    public String fullSocialPlan(FullSocialPlanException e) {
        return "fullSocialPlan";
    }

    @Override
    public String userAlreadyInSocialPlan(UserAlreadyInSocialPlanException e) {
        return "userAlreadyInSocialPlan";
    }

    @Override
    public String wrongListOrder(ListOrderException e) {
        return "wrongListOrder";
    }

    @Override
    public String ticketNotFound(TicketNotFoundException e) {
        return "ticketNotFound";
    }

    @Override
    public String invalidScore(InvalidScoreException e) {
        return "invalidScore";
    }

    @Override
    public String setScore(OptionalInt score) {
        return "";
    }

    public String colisionWithOtherSocialPlan()
    {
        return "colisionWithOtherSocialPlan";
    }

    public String joinPastSocialPlan()
    {
        return "joinPastSocialPlan";
    }


    public String setScoreFutureSocialPlan()
    {
        return "setScoreFutureSocialPlan";
    }

    public String participantNotFound(ParticipantNotFoundException e)
    {
        return "participantNotFound";
    }

    @Override
    public String createSocialPlanPastDate(PastDateException e)
    {
        return "createSocialPlanPastDate";
    }
}
