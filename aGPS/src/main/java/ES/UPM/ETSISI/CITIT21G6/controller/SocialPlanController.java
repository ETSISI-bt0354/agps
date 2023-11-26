package ES.UPM.ETSISI.CITIT21G6.controller;

import ES.UPM.ETSISI.CITIT21G6.model.*;
import ES.UPM.ETSISI.CITIT21G6.repository.SocialPlanRepository;
import ES.UPM.ETSISI.CITIT21G6.view.SocialPlanView;

import java.time.LocalDate;
import java.util.OptionalInt;

public class SocialPlanController extends SessionController
{
    private static final int MINIMUM_CREATION_ARGUMENT_LENGTH = 3;
    private static final int MINIMUM_DELETE_ARGUMENT_LENGTH = 1;
    private SocialPlanRepository repository;
    private SocialPlanView view;

    public SocialPlanController(SocialPlanRepository repository, SocialPlanView view)
    {
        super();
        this.repository = repository;
        this.view = view;
    }

    public String createSocialPlan(String[] args)
    {
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

        repository.save(newPlan);
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
        catch (Exception e)
        {
            result = e.getMessage();
        }
        return result;
    }

    public String addActivities(String[] args)
    {
        String result;
        User loggedUser = getLoggedUser();

        if (loggedUser == null)
            return view.noLoggedUser();

        SocialPlanId socialPlanId = new SocialPlanId(loggedUser.getName(), args[0]);
        SocialPlan socialPlan = repository.fetch(socialPlanId);
        if (repository.fetch(socialPlanId) == null)
            return "The social plan does not exist";

        try
        {
            Activity activity = new Activity(args[1], args[2], Integer.parseInt(args[3]), Double.parseDouble(args[4]), ActivityType.parse(args[5]));
            if (args.length > 6)
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
}
