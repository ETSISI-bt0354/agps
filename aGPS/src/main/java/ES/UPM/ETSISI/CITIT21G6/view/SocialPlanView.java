package ES.UPM.ETSISI.CITIT21G6.view;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.FutureSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.PastSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.SocialPlanCollisionException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.Activity;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlan;
import ES.UPM.ETSISI.CITIT21G6.model.SocialPlanId;

import java.util.List;
import java.util.OptionalInt;

public interface SocialPlanView
{
    String create(SocialPlan socialPlan);
    String delete(SocialPlanId id);
    String addActivity(Activity activity);
    String insufficientArguments(int requiredArguments);
    String noLoggedUser();
    String listPlans(List<SocialPlan> socialPlans);
    String removeParticipant(String participantName);
    String addParticipant(String participantName);
    String socialPlanNotFound(SocialPlanNotFoundException e);
    String price(double price);
    String invalidCapacity(InvalidCapacityException e);
    String socialPlanAlreadyAdded(SocialPlanAlreadyAddedException e);
    String activityAlreadyInSocialPlan(ActivityAlreadyInSocialPlanException e);
    String fullSocialPlan(FullSocialPlanException e);
    String userAlreadyInSocialPlan(UserAlreadyInSocialPlanException e);
    String wrongListOrder(ListOrderException e);
    String invalidScore(InvalidScoreException e);
    String setScore(OptionalInt score);
    String colisionWithOtherSocialPlan(SocialPlanCollisionException e);
    String pastSocialPlan(PastSocialPlanException e);
    String setScoreFutureSocialPlan(FutureSocialPlanException e);
    String participantNotFound(ParticipantNotFoundException e);
    String createSocialPlanPastDate(PastDateException e);
}
