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
import ES.UPM.ETSISI.CITIT21G6.service.ListOrder;
import ES.UPM.ETSISI.CITIT21G6.service.SocialPlanService;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.OptionalInt;

public class SocialPlanController extends SessionController
{
    private static final int MINIMUM_CREATION_ARGUMENT_LENGTH = 4;
    private static final int MINIMUM_DELETE_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_REMOVE_PARTICIPANT_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_ADD_PARTICIPANT_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_LIST_PLANS_ARGUMENT_LENGTH = 1;
    private static final int MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH = 6;
    private static final int MINIMUM_SET_SCORE_ARGUMENT_LENGTH = 3;
    private static final int MINIMUM_SHOW_ACTIVITIES_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_SHOW_PARTICIPANTS_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_SHOW_DURATION_ARGUMENT_LENGTH = 2;
    private static final int MINIMUM_SET_CAPACITY_ARGUMENT_LENGTH = 1;
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

        if(!isUserLogged())
            return view.noLoggedUser();

        String socialPlanName = args[0];

        try
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(args[1] + " " + args[2], formatter);
            String location = args[3];
            OptionalInt capacity = OptionalInt.empty();
            if (args.length > MINIMUM_CREATION_ARGUMENT_LENGTH)
                capacity = OptionalInt.of(Integer.parseInt(args[4]));

            SocialPlan socialPlan = service.createSocialPlan(getLoggedUser().getName(), socialPlanName, date, location, capacity);
            return view.create(socialPlan);
        }
        catch (DateTimeParseException e)
        {
            return view.invalidLocalDateTimeFormat(e);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[4]);
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

        if(!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(getLoggedUser().getName(), args[0]);
        try
        {
            service.deleteSocialPlan(socialPlanId);
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

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(getLoggedUser().getName(), args[0]);
        String activityName = args[1];
        String description = args[2];

        int duration;
        try
        {
            duration = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[3]);
        }

        double price;
        try
        {
            price = Double.parseDouble(args[4]);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[4]);
        }

        try
        {

            ActivityType type = ActivityType.parse(args[5]);
            OptionalInt capacity = OptionalInt.empty();
            if (args.length > MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH)
                capacity = OptionalInt.of(Integer.parseInt(args[6]));

            Activity activity = service.addActivity(socialPlanId, activityName, description, duration, price, type, capacity);
            return view.addActivity(activity);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[6]);
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
        catch (InvalidPriceException e)
        {
            return view.invalidActivityPrice(e);
        }
        catch (InvalidDurationException e)
        {
            return view.invalidActivityDuration(e);
        }
    }

    public String checkPlanCost(String[] args)
    {
        if(args.length < MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);

        try
        {
            double price = service.checkPlanCost(socialPlanId, getLoggedUser().getAge());
            return view.price(price);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String joinSocialPlan(String[] args)
    {
        if(args.length < MINIMUM_ADD_PARTICIPANT_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_ADD_PARTICIPANT_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        String participantName = getLoggedUser().getName();

        try
        {
            service.joinSocialPlan(socialPlanId, participantName);
            return view.joinSocialPlan(participantName);
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
            return view.collisionWithOtherSocialPlan(e);
        }
        catch (ParticipantAlreadyInSocialPlanException e){
            return view.ParticipantAlreadyInSocialPlan(e);
        }
        catch (FullSocialPlanException e)
        {
            return view.fullSocialPlan(e);
        }
    }

    public String unjoinSocialPlan(String[] args)
    {
        if(args.length < MINIMUM_REMOVE_PARTICIPANT_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_REMOVE_PARTICIPANT_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        String participantName = getLoggedUser().getName();

        try
        {
            service.unjoinSocialPlan(socialPlanId, participantName);
            return view.unjoinSocialPlan(participantName);
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

        if (!isUserLogged())
            return view.noLoggedUser();

        try
        {
            ListOrder order = ListOrder.parse(args[0]);
            List<SocialPlan> socialPlans = service.listSocialPlans(order);
            return view.listPlans(socialPlans);
        }
        catch (ListOrderException e)
        {
            return view.wrongListOrder(e);
        }
    }

    public String listSubscribedSocialPlans(String[] args)
    {
        if (!isUserLogged())
            return view.noLoggedUser();

        List<SocialPlan> socialPlans = service.listSubscribedSocialPlans(getLoggedUser().getName());
        return view.listPlans(socialPlans);
    }

    public String scoreSocialPlan(String[] args)
    {
        if (args.length < MINIMUM_SET_SCORE_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SET_SCORE_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        String participantName = getLoggedUser().getName();

        try
        {
            OptionalInt score = OptionalInt.of(Integer.parseInt(args[2]));
            service.scoreSocialPlan(socialPlanId, participantName, score);
            return view.setScore(score);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[2]);
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

    public String showSocialPlanActivities(String[] args)
    {
        if (args.length < MINIMUM_SHOW_ACTIVITIES_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SHOW_ACTIVITIES_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        try
        {
            List<Activity> activities = service.getSocialPlanActivities(socialPlanId);
            return view.showActivities(activities);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String showParticipants(String[] args)
    {
        if (args.length < MINIMUM_SHOW_PARTICIPANTS_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SHOW_PARTICIPANTS_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        try
        {
            List<Ticket> participants = service.getParticipants(socialPlanId);
            return view.showParticipants(participants);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String showSocialPlanDuration(String[] args)
    {
        if (args.length < MINIMUM_SHOW_DURATION_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SHOW_DURATION_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(args[0], args[1]);
        try
        {
            int duration = service.getDuration(socialPlanId);
            return view.showDuration(duration);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String setSocialPlanCapacity(String[] args)
    {
        if (args.length < MINIMUM_SET_CAPACITY_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SET_CAPACITY_ARGUMENT_LENGTH);

        if (!isUserLogged())
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(getLoggedUser().getName(), args[0]);
        try
        {
            OptionalInt capacity = OptionalInt.empty();
            if (args.length > MINIMUM_SET_CAPACITY_ARGUMENT_LENGTH)
                capacity = OptionalInt.of(Integer.parseInt(args[1]));

            service.setSocialPlanCapacity(socialPlanId, capacity);
            return view.setSocialPlanCapacity(capacity);
        }
        catch (NumberFormatException e)
        {
            return view.invalidNumber(args[1]);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
        catch (InvalidCapacityException e)
        {
            return view.invalidCapacity(e);
        }
    }
}