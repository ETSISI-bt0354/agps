package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanAlreadyAddedException;
import ES.UPM.ETSISI.CITIT21G6.exception.SocialPlanRepositoryException.SocialPlanNotFoundException;
import ES.UPM.ETSISI.CITIT21G6.model.*;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.time.LocalDate;
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
    private static final int MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH = 5;
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
            return view.insufficentArguments(MINIMUM_CREATION_ARGUMENT_LENGTH);

        User loggedUser = getLoggedUser();

        if(loggedUser == null)
            return view.noLoggedUser();

        SocialPlan newPlan = new SocialPlan(loggedUser.getName(), args[0], LocalDate.parse(args[1]), args[2]);

        if(args.length == MINIMUM_CREATION_ARGUMENT_LENGTH + 1)
        {
            try
            {
                newPlan.setCapacity(OptionalInt.of(Integer.parseInt(args[3])));
            }
            catch (Exception e)
            {
                return e.getMessage();
            }
        }

        try
        {
            repository.save(newPlan);
        }
        catch(SocialPlanAlreadyAddedException e)
        {
            return e.getMessage();
        }
        return  view.create(newPlan);
    }

    public String deleteSocialPlan(String[] args)
    {
        if(args.length < MINIMUM_DELETE_ARGUMENT_LENGTH)
        {
            return view.insufficentArguments(MINIMUM_DELETE_ARGUMENT_LENGTH);
        }

        String result;
        User loggedUser = getLoggedUser();

        if(loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        try
        {
            repository.delete(socialPlanId);
            result = view.delete(socialPlanId);
        }
        catch (SocialPlanNotFoundException e)
        {
            result = e.getMessage();
        }
        return result;
    }

    public String addActivity(String[] args)
    {
        if(args.length < MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH)
            return view.insufficentArguments(MINIMUM_CREATION_ARGUMENT_LENGTH);

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

        try
        {
            Activity activity = new Activity(args[1], args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), ActivityType.parse(args[5]));
            if (args.length > MINIMUM_ADD_ACTIVITY_ARGUMENT_LENGTH + 1)
                activity.setCapacity(OptionalInt.of(Integer.parseInt(args[6])));

            socialPlan.addActivity(activity);

            result = view.addActivity();
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }

        return result;
    }

    public String checkPlanCost(String[] args)
    {
        if(args.length < MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH)
            return view.insufficentArguments(MINIMUM_CHECK_PLAN_COST_ARGUMENT_LENGTH);

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

        double price = socialPlan.getActivities().stream().reduce(0.0, (subtotal, activity) -> subtotal + activity.getPrice(), Double::sum);
        return view.price(price);
    }

    public String removeUser(String[] args)
    {
        if(args.length < MINIMUM_REMOVE_USER_ARGUMENT_LENGTH)
            return view.insufficentArguments(MINIMUM_REMOVE_USER_ARGUMENT_LENGTH);

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

        Ticket ticket = new Ticket(loggedUser.getName());
        socialPlan.removeParticipant(ticket);
        return view.removeUser(ticket);
    }

    public String addUser(String[] args)
    {
        if(args.length < MINIMUM_ADD_USER_ARGUMENT_LENGTH)
            return view.insufficentArguments(MINIMUM_ADD_USER_ARGUMENT_LENGTH);

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

        Ticket ticket = new Ticket(loggedUser.getName());
        try{
            socialPlan.addParticipant(ticket);
        } catch (Exception e){
            return e.getMessage();
        }
        return view.addUser(ticket);
    }

    public String listSocialPlans(String[] args)
    {
        if(args.length < MINIMUM_LIST_PLANS_ARGUMENT_LENGTH)
            return view.insufficentArguments(MINIMUM_LIST_PLANS_ARGUMENT_LENGTH);

        String result;
        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        try
        {
            ListOrder order = ListOrder.parse(args[0]);
            List<SocialPlan> socialPlans = repository.getAllSocialPlans();
            result = view.listPlans(socialPlans, order);
        }
        catch (Exception e)
        {
            result = e.getMessage();
        }

        return result;
    }


}