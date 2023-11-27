package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.ListOrderException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanException.*;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.exception.TicketException.InvalidScoreException;
import ES.UPM.ETSISI.CITIT21G6.model.*;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;
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
    private SocialPlanRepository repository;
    private SocialPlanView view;

    public SocialPlanController(SocialPlanRepository repository, SocialPlanView view)
    {
        super();
        this.repository = repository;
        this.view = view;
    }

    public String createSocialPlan(String[] args) {
        if(args.length < MINIMUM_CREATION_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CREATION_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if(loggedUser == null)
            return view.noLoggedUser();

        SocialPlan newPlan;
        try
        {
             newPlan = new SocialPlan(loggedUser.getName(), args[0], LocalDateTime.parse(args[1]), args[2]);
        }
        catch (PastDateException e)
        {
            return view.createSocialPlanPastDate(e);
        }

        if(args.length > MINIMUM_CREATION_ARGUMENT_LENGTH)
        {
            try
            {
                newPlan.setCapacity(OptionalInt.of(Integer.parseInt(args[3])));
            }
            catch (InvalidCapacityException e)
            {
                return view.invalidCapacity(e);
            }
        }

        try
        {
            repository.save(newPlan);
            return view.create(newPlan);
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
            repository.delete(socialPlanId);
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

        SocialPlan socialPlan;
        try
        {
            socialPlan = repository.fetch(new SocialPlanId(loggedUser.getName(), args[0]));
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

        try
        {
            Activity activity = new Activity(args[1], args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), ActivityType.parse(args[5]));
            try
            {
                if (args.length > MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH)
                    activity.setCapacity(OptionalInt.of(Integer.parseInt(args[6])));
            }
            catch (InvalidCapacityException e)
            {
                return view.invalidCapacity(e);
            }

            socialPlan.addActivity(activity);

            repository.update(socialPlan);

            return view.addActivity(activity);
        }
        catch (InvalidCapacityException e)
        {
            return view.invalidCapacity(e);
        }
        catch (ActivityAlreadyInSocialPlanException e)
        {
            return view.activityAlreadyInSocialPlan(e);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    public String checkPlanCost(String[] args)
    {
        if(args.length < MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH);

        String result;
        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlan socialPlan;
        try
        {
            socialPlan = repository.fetch(new SocialPlanId(loggedUser.getName(), args[0]));
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

        double price = socialPlan.getActivities()
                .stream()
                .reduce(0.0, (subtotal, activity) ->
                        subtotal + ActivityPriceCalculator.calculate(activity, loggedUser.getAge()), Double::sum
                );

        return view.price(price);
    }

    public String addParticipant(String[] args)
    {
        if(args.length < MINIMUM_ADD_USER_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_ADD_USER_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlan socialPlan;
        try
        {
            socialPlan = repository.fetch(new SocialPlanId(loggedUser.getName(), args[0]));
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

        if (socialPlan.getDate().isBefore(LocalDateTime.now()))
            return view.joinPastSocialPlan();

        boolean collision = repository.fetchAllSocialPlans()
                .stream()
                .filter(plan -> plan.getParticipants()
                        .stream()
                        .anyMatch(ticket -> loggedUser.getName().equals(ticket.getUserName())))
                .anyMatch(plan -> socialPlanCollision(socialPlan, plan));

        if (collision)
            return view.colisionWithOtherSocialPlan();

        try{
            socialPlan.addParticipant(loggedUser.getName());
            repository.update(socialPlan);
            return view.addParticipant(loggedUser.getName());
        }
        catch (UserAlreadyInSocialPlanException e){
            return view.userAlreadyInSocialPlan(e);
        }
        catch (FullSocialPlanException e)
        {
            return view.fullSocialPlan(e);
        }
        catch (SocialPlanNotFoundException e) {
            return view.socialPlanNotFound(e);
        }
    }
    public String removeParticipant(String[] args)
    {
        if(args.length < MINIMUM_REMOVE_USER_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_REMOVE_USER_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlan socialPlan;
        try
        {
            socialPlan = repository.fetch(new SocialPlanId(loggedUser.getName(), args[0]));
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

        try
        {
            socialPlan.removeParticipant(loggedUser.getName());
        }
        catch (ParticipantNotFoundException e)
        {
            return view.participantNotFound(e);
        }

        try
        {
            repository.update(socialPlan);
        }
        catch (SocialPlanNotFoundException ignored) {}

        return view.removeParticipant(loggedUser.getName());
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
            List<SocialPlan> socialPlans = repository.fetchAllSocialPlans();

            if (args.length > MINIMUM_LIST_PLANS_ARGUMENT_LENGTH && args[1].equalsIgnoreCase("ONLY-SUBSCRIBED"))
                socialPlans = socialPlans
                        .stream()
                        .filter(plan -> plan
                                .getParticipants()
                                .stream()
                                .anyMatch(ticket -> loggedUser.getName().equals(ticket.getUserName()))
                        )
                        .toList();

            return view.listPlans(socialPlans, order);
        }
        catch (ListOrderException e)
        {
            return view.wrongListOrder(e);
        }
    }

    public String setSocialPlanScore(String[] args)
    {
        if (args.length < MINIMUM_SET_SCORE_ARGUMENT_LENGTH)
            return view.insufficientArguments(MINIMUM_SET_SCORE_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlan socialPlan;
        try
        {
            socialPlan = repository.fetch(new SocialPlanId(loggedUser.getName(), args[0]));
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }

        if (socialPlan.getDate().isAfter(LocalDateTime.now()))
            return view.setScoreFutureSocialPlan();
        try
        {
            Ticket ticket = socialPlan.getParticipants()
                    .stream()
                    .filter(t -> loggedUser.getName().equals(t.getUserName()))
                    .findFirst()
                    .orElseThrow(() -> new TicketNotFoundException(loggedUser.getName()));

            OptionalInt score = OptionalInt.of(Integer.parseInt(args[1]));
            ticket.setScore(score);

            repository.update(socialPlan);

            return view.setScore(score);
        }
        catch (TicketNotFoundException e)
        {
            return view.ticketNotFound(e);
        }
        catch (InvalidScoreException e)
        {
            return view.invalidScore(e);
        }
        catch (SocialPlanNotFoundException e)
        {
            return view.socialPlanNotFound(e);
        }
    }

    private static int calculateSocialPlanDuration(SocialPlan socialPlan)
    {
        int duration = 0;
        if (!socialPlan.getActivities().isEmpty())
        {
            int durationWithoutTrip = socialPlan.getActivities()
                    .stream()
                    .reduce(0, (x, activity) -> x + activity.getDuration(), Integer::sum);

            duration = durationWithoutTrip + 20 * socialPlan.getActivities().size() - 1;
        }

        return duration;
    }

    private static boolean socialPlanCollision(SocialPlan socialPlan1, SocialPlan socialPlan2)
    {
        LocalDateTime socialPlan1StartDate = socialPlan1.getDate();
        LocalDateTime socialPlan1EndDate = socialPlan1.getDate().plusMinutes(calculateSocialPlanDuration(socialPlan1));

        LocalDateTime socialPlan2StartDate = socialPlan2.getDate();
        LocalDateTime socialPlan2EndDate = socialPlan2.getDate().plusMinutes(calculateSocialPlanDuration(socialPlan2));

        return socialPlan1StartDate.isBefore(socialPlan2EndDate)
                && socialPlan1EndDate.isAfter(socialPlan2StartDate);
    }
}