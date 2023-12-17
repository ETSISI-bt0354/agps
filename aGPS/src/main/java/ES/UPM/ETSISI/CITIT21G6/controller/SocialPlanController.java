package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.FutureSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.PastSocialPlanException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanServiceException.SocialPlanCollisionException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.*;
import ES.UPM.ETSISI.CITIT21G6.service.SocialPlanService;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalInt;

public class SocialPlanController extends SessionController
{
    private static final int MINIMUM_CREATION_ARGUMENT_LENGTH = 3;
    private static final int MINIMUM_DELETE_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_REMOVE_USER_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_ADD_USER_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_LIST_PLANS_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH = 6;
    private static final int MINIMUM_SET_SCORE_ARGUMENT_LENGTH = 2;
    private SocialPlanService service;
    private SocialPlanView view;

    public SocialPlanController(SocialPlanService service, SocialPlanView view)
    {
        super();
        this.service = service;
        this.view = view;
    }

    public String createSocialPlan(String[] args) {
        if(args.length < MINIMUM_CREATION_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CREATION_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if(loggedUser == null)
            return view.noLoggedUser();

        String socialPlanName = args[0];
        LocalDateTime date = LocalDateTime.parse(args[1]);
        String location = args[2];
        OptionalInt capacity = OptionalInt.empty();
        if (args.length > MINIMUM_CREATION_ARGUMENT_LENGTH)
            capacity = OptionalInt.of(Integer.parseInt(args[3]));

        try
        {
            SocialPlan socialPlan = service.createSocialPlan(loggedUser.getName(), socialPlanName, date, location, capacity);
            return view.create(socialPlan);
        }
        catch (PastDateException e)
        {
            return view.createSocialPlanPastDate(e);
        }
        catch (InvalidCapacityException e)
        {
            return view.invalidCapacity(e);
        }
        catch(SocialPlanAlreadyAddedException e)
        {
            return view.socialPlanAlreadyAdded(e);
        }

    }

    public String deleteSocialPlan(String[] args)
    {
        if(args.length < MINIMUM_DELETE_ARGUMENT_LENGTH)
        {
            return view.insufficientArguments(MINIMUM_DELETE_ARGUMENT_LENGTH);
        }

        User loggedUser = getLoggedUser();

        if(loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        try
        {
            service.deleteSoialPlan(socialPlanId);
            return view.delete(socialPlanId);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

    }

    public String addActivity(String[] args)
    {
        if(args.length < MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CREATION_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        String activityName = args[1];
        String description = args[2];
        int duration = Integer.parseInt(args[3]);
        double price = Double.parseDouble(args[4]);
        ActivityType type = ActivityType.parse(args[5]);
        OptionalInt capacity = OptionalInt.empty();
        if (args.length > MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH)
            capacity = OptionalInt.of(Integer.parseInt(args[6]));

        try
        {
            Activity activity = service.addActivity(socialPlanId, activityName, description, duration, price, type, capacity);
            return view.addActivity(activity);
        }
        catch (InvalidCapacityException e)
        {
            return view.invalidCapacity(e);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
        catch (PastSocialPlanException e)
        {
            return view.pastSocialPlan(e);
        }
        catch (ActivityAlreadyInSocialPlanException e)
        {
            return view.activityAlreadyInSocialPlan(e);
        }
    }

    public String checkPlanCost(String[] args)
    {
        if(args.length < MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);

        try
        {
            double price = service.checkPlanCost(socialPlanId, loggedUser.getAge());
            return view.price(price);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String addParticipant(String[] args)
    {
        if(args.length < MINIMUM_ADD_USER_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_ADD_USER_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        String participantName = loggedUser.getName();

        try
        {
            service.addParticipant(socialPlanId, participantName);
            return view.addParticipant(participantName);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
        catch (PastSocialPlanException e)
        {
            return view.pastSocialPlan(e);
        }
        catch (SocialPlanCollisionException e)
        {
            return view.colisionWithOtherSocialPlan(e);
        }
        catch (UserAlreadyInSocialPlanException e){
            return view.userAlreadyInSocialPlan(e);
        }
        catch (FullSocialPlanException e)
        {
            return view.fullSocialPlan(e);
        }
    }

    public String removeParticipant(String[] args)
    {
        if(args.length < MINIMUM_REMOVE_USER_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_REMOVE_USER_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        String participantName = loggedUser.getName();

        try
        {
            service.removeParticipant(socialPlanId, participantName);
            return view.removeParticipant(loggedUser.getName());
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
        catch (PastSocialPlanException e)
        {
            return view.pastSocialPlan(e);
        }
        catch (ParticipantNotFoundException e)
        {
            return view.participantNotFound(e);
        }
    }

    public String listSocialPlans(String[] args)
    {
        if(args.length < MINIMUM_LIST_PLANS_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_LIST_PLANS_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        try
        {
            ListOrder order = ListOrder.parse(args[0]);
            List<SocialPlan> socialPlans = service.listSocialPlan(order);
            return view.listPlans(socialPlans);
        }
        catch (ListOrderException e)
        {
            return view.wrongListOrder(e);
        }
    }

    public String listSubscribedSocialPlans(String[] args)
    {
        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        List<SocialPlan> socialPlans = service.listSubscribedSocialPlan(loggedUser.getName());
        return view.listPlans(socialPlans);
    }

    public String setSocialPlanScore(String[] args)
    {
        if (args.length < MINIMUM_SET_SCORE_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SET_SCORE_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        OptionalInt score = OptionalInt.of(Integer.parseInt(args[1]));
        try
        {
            service.setSocialPlanScore(socialPlanId, loggedUser.getName(), score);
            return view.setScore(score);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
        catch (FutureSocialPlanException e)
        {
            return view.setScoreFutureSocialPlan(e);
        }
        catch (ParticipantNotFoundException e)
        {
            return view.participantNotFound(e);
        }
        catch (InvalidScoreException e)
        {
            return view.invalidScore(e);
        }
    }
}